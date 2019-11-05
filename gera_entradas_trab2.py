import random
import string
import pandas as pd
from faker import Faker


def gera_docentes(nDocentes):
    with open(f"{path}/docentes_python.csv", "w+", encoding="utf8") as file:
        file.write("Código;Nome;Data Nascimento;Data Ingresso;Coodernador?\n")

        cods = list()
        randCoordenador = random.randint(1, nDocentes)

        while len(cods) < nDocentes:
            cod = random.randint(1000000000000000, 9999999999999999)
            while cod in cods:
                cod = random.randint(1000000000000000, 9999999999999999)
            cods.append(cod)

            nome = fake.name()

            dataNascimento = fake.date(pattern="%d/%m/%Y", end_datetime=None)
            dataIngresso = fake.date(pattern="%d/%m/%Y", end_datetime=None)
            while (int(dataIngresso.split("/")[2]) - int(dataNascimento.split("/")[2])) < 20:
                dataNascimento = fake.date(
                    pattern="%d/%m/%Y", end_datetime=None)
                dataIngresso = fake.date(
                    pattern="%d/%m/%Y", end_datetime=None)

            file.write(f"{cod};{nome};{dataNascimento};{dataIngresso};")
            if (len(cods) == randCoordenador):
                file.write("X\n")
            else:
                file.write("\n")


def gera_veiculos(nVeiculos):
    tipos = ['P', 'C']
    with open(f"{path}/veiculos_python.csv", "w+", encoding="utf8") as file:
        file.write("Sigla;Nome;Tipo;Impacto;ISSN\n")

        siglasUtilizadas = list()
        nomesUtilizados = list()
        for i in range(nVeiculos):
            sigla = ''
            nome = fakezada.company()
            while sigla in siglasUtilizadas or nome in siglasUtilizadas or len(sigla) is 0:
                sigla = ''
                nome = fakezada.company()
                for palavra in nome.split(' '):
                    sigla += palavra[0]

            tipo = random.choice(tipos)
            if tipo == 'P':
                issn = f"{fake.bban()[7:11]}-{fake.bban()[6:2:-1]}"
            else:
                issn = ''  # ?

            if random.randint(1, 3) == 1:
                impacto = round(abs(random.gauss(10, 10)), 3)
            else:
                impacto = 0

            file.write(f"{sigla};{nome};{tipo};{impacto};{issn}\n")
            siglasUtilizadas.append(sigla)
            nomesUtilizados.append(nome)


def gera_qualis(arquivoVeiculos):
    with open(f"{path}/qualis_python.csv", "w+", encoding="utf8") as file:
        file.write("Ano;Veículo;Qualis\n")
        veiculosCSV = pd.read_csv(f"{path}/{arquivoVeiculos}", sep=';')
        for veiculo in veiculosCSV["Sigla"].tolist():
            ano = random.randint(2010, 2019)

            sigla = veiculo

            qualisPossiveis = ['A1', 'A2', 'B1', 'B2', 'B3', 'B4', 'B5', 'C']
            qualis = random.choice(qualisPossiveis)
            file.write(f"{ano};{sigla};{qualis}\n")


def gera_regras():
    with open(f"{path}/regras_python.csv", "w+", encoding="utf8") as file:
        file.write(
            "Início Vigência;Fim Vigência;Qualis;Pontos;Multiplicador;Anos;Mínimo Pontos\n")

        inicio = fake.date(pattern="%d/%m/%Y", end_datetime=None)
        fim = fake.date(pattern="%d/%m/%Y", end_datetime=None)
        while (int(inicio.split("/")[2]) < 2010) or (int(fim.split("/")[2]) - int(inicio.split("/")[2]) < 1):
            inicio = fake.date(pattern="%d/%m/%Y", end_datetime=None)
            fim = fake.date(pattern="%d/%m/%Y", end_datetime=None)
        file.write(f"{inicio};{fim};")

        qualisPossivel = ['A1', 'A2', 'B1', 'B2', 'B3', 'B4', 'B5', 'C']
        qualisEscolhidas = sorted(random.sample(
            qualisPossivel, random.randint(1, 6)))
        for qualis in qualisEscolhidas:
            if qualis != qualisEscolhidas[-1]:
                file.write(f"{qualis},")
            else:
                file.write(f"{qualis};")

        pontos = random.sample(range(1, 10), 4)
        multiplicador = random.randint(1, 10)
        anos = random.randint(1, 5)
        minimoPontos = round(abs(random.gauss(30, 10)))

        for ponto in pontos:
            if ponto != pontos[-1]:
                file.write(f"{ponto},")
            else:
                file.write(f"{ponto};")

        file.write(f"{multiplicador},0;{anos};{minimoPontos}")


def gera_publicacoes(arquivoVeiculos, arquivoDocentes, nPublicacoes):
    docentesCSV = pd.read_csv(f"{path}/{arquivoDocentes}", sep=';')
    docentesCSV = docentesCSV["Código"].tolist()
    with open(f"{path}/publicacoes_python.csv", "w+", encoding="utf8") as file:
        file.write(
            "Ano;Veículo;Título;Autores;Número;Volume;Local;Página Inicial;Página Final\n")
        for i in range(nPublicacoes):
            ano = random.randint(2010, 2019)

            veiculosCSV = pd.read_csv(f"{path}/{arquivoVeiculos}", sep=';')
            siglas = veiculosCSV["Sigla"].tolist()
            sigla = random.choice(siglas)

            titulo = fakezada.catch_phrase()

            nAutores = random.randint(1, 3)
            autores = random.sample(docentesCSV, nAutores)

            numero = random.randint(1, 50)

            veiculo = veiculosCSV[veiculosCSV['Sigla'] == sigla]
            tipoVeiculo = veiculo['Tipo'].values

            if tipoVeiculo == 'P':
                volume = random.randint(1, 50)
            else:
                volume = ''

            local = fakezada.city()

            paginaInicial = random.randint(1, 1000)
            paginaFinal = random.randint(1, 1000)
            while paginaFinal < paginaInicial:
                paginaInicial = random.randint(1, 1000)
                paginaFinal = random.randint(1, 1000)

            file.write(f"{ano};{sigla};{titulo};")
            for autor in autores:
                if autor != autores[-1]:
                    file.write(f"{autor}, ")
                else:
                    file.write(f"{autor};")
            file.write(
                f"{numero};{volume};{local};{paginaInicial};{paginaFinal}\n")


if __name__ == "__main__":
    fake = Faker('pt_BR')
    fakezada = Faker('en_US')
    path = "entradas_autorais/python"

    # Editar valores aqui
    nDocentes = 200
    nVeiculos = 100
    nPublicacoes = 500

    gera_docentes(nDocentes)
    gera_veiculos(nVeiculos)
    gera_qualis("veiculos_python.csv")
    gera_regras()
    gera_publicacoes("veiculos_python.csv",
                     "docentes_python.csv", nPublicacoes)
