import random
import string
import pandas
from faker import Faker

fake = Faker('pt_BR')
fakezada = Faker('en_US')
path = "entradas_autorais/python"


def gera_usuarios(nUsuarios):
    tipos = ['A', 'P', 'U']
    with open(f"{path}/usuarios_python.csv", "w+", encoding="utf8") as file:
        file.write("Código;Tipo;Nome\n")
        for i in range(1, nUsuarios + 1):
            file.write(f"{i};{random.choice(tipos)};{fake.name()}\n")
    pass


def gera_generos(nGeneros):
    with open(f"{path}/genres.txt", "r", encoding="utf8") as fGM:
        genresM = [line.strip().capitalize() for line in fGM]
    with open(f"{path}/podcast_genres.txt", "r", encoding="utf8") as fGP:
        genresP = fGP.readlines()

    siglasUtilizadas = list()
    with open(f"{path}/generos_python.csv", "w+", encoding="utf8") as file:
        file.write("Sigla;Nome\n")

        for generoP in genresP:
            if len(generoP.split()) > 1:
                siglaP = f"{generoP.split()[0][0]}{generoP.split()[1][0]}".upper()
            else:
                siglaP = f"{generoP[0]}{generoP[1]}".upper()
            siglasUtilizadas.append((siglaP, generoP))

            file.write(f"{siglaP};{generoP}")

        nGenerosP = len(siglasUtilizadas)

        while len(siglasUtilizadas) != nGeneros:
            genero = random.choice(genresM)
            genresM.remove(genero)
            if len(genero.split()) > 1:
                siglaM = f"{genero.split()[0][0]}{genero.split()[1][0]}".upper()
            else:
                siglaM = f"{genero[0]}{genero[1]}".upper()
            if siglaM not in list(map(lambda x: x[0], siglasUtilizadas)):
                siglasUtilizadas.append((siglaM, genero))

        for i in range(nGenerosP, nGeneros):
            file.write(f"{siglasUtilizadas[i][0]};{siglasUtilizadas[i][1]}\n")
    pass


def gera_midias(nMidias, arquivoGeneros, arquivoUsuarios):
    with open(f"{path}/midias_python.csv", "w+", encoding="utf8") as file:
        file.write("Código;Nome;Tipo;Produtores;Duração;Gênero;Temporada;Álbum;Código do Álbum;Publicação\n")

        generoCSV = pandas.read_csv(f"{arquivoGeneros}", sep=';')
        generoCSV = generoCSV["Sigla"].tolist()

        usuariosCSV = pandas.read_csv(f"{arquivoUsuarios}", sep=";")
        tipoUsuario = usuariosCSV["Tipo"].tolist()

        albuns = list()
        rangeMidias = range(1, nMidias + 1)
        for i in rangeMidias:
            file.write(f"{i};")
            nomeMidia = fake.sentence(nb_words=random.randint(1, 7),
                                      variable_nb_words=True, ext_word_list=None).translate(str.maketrans('', '', string.punctuation))
            publicacaoMidia = random.randint(1990, 2077)
            tipoMidia = random.choice(['M', 'P'])

            produtores = list()

            if tipoMidia == 'M':
                duracaoMidia = f"{random.randint(1, 10)},{random.randint(0,99)}"
                temporadaMidia = ''

                albumMidia = fakezada.sentence(
                    nb_words=random.randint(1, 7),
                    variable_nb_words=True, ext_word_list=None).translate(str.maketrans('', '', string.punctuation))
                albuns.append(albumMidia)
                nAlbuns = len(set(albuns))

                while len(produtores) < random.randint(1, 3):
                    randProd = random.randint(1, nUsuarios - 1)
                    if tipoUsuario[randProd] == "A" and randProd + 1 not in produtores:
                        produtores.append(randProd + 1)
            else:
                duracaoMidia = duracaoMidia = f"{random.randint(1, 120)},{random.randint(0,99)}"
                temporadaMidia = random.randint(1, 20)
                albumMidia = ''
                while len(produtores) < random.randint(1, 3):
                    randProd = random.randint(1, nUsuarios - 1)
                    if tipoUsuario[randProd] == "P" and randProd + 1 not in produtores:
                        produtores.append(randProd + 1)

            generosM = list()
            nGenerosM = round(abs(random.gauss(1, 1.5)))
            for i in range(nGenerosM if nGenerosM > 0 else 1):
                genero = random.choice(generoCSV[25:])
                while str(genero) in 'nan' or genero in generosM:
                    genero = random.choice(generoCSV[25:])
                generosM.append(genero)

            generosP = list()
            nGenerosP = round(abs(random.gauss(1, 0.5)))
            for i in range(nGenerosP if nGenerosP > 0 else 1):
                genero = random.choice(generoCSV[:25])
                while str(genero) in 'nan' or genero in generosP:
                    genero = random.choice(generoCSV[:25])
                generosP.append(genero)

            file.write(f"{nomeMidia};{tipoMidia};")
            for produtor in produtores:
                if produtor is not produtores[-1]:
                    file.write(f"{produtor}, ")
                elif produtor is produtores[-1]:
                    file.write(f"{produtor};")

            if tipoMidia == 'M':
                file.write(f"{duracaoMidia};")
                for genero in generosM:
                    if genero is not generosM[-1]:
                        file.write(f"{genero}, ")
                    else:
                        file.write(f"{genero};")
                file.write(f"{temporadaMidia};{albumMidia};{nAlbuns};{publicacaoMidia}")
            else:
                file.write(f"{duracaoMidia};")
                for genero in generosP:
                    if genero is not generosP[-1]:
                        file.write(f"{genero}, ")
                    else:
                        file.write(f"{genero};")

                file.write(f"{temporadaMidia};{albumMidia};;{publicacaoMidia}")

            if i != rangeMidias[-1]:
                file.write("\n")
    pass


def gera_favoritos(arquivoUsuarios, nMidias):
    usuariosCSV = pandas.read_csv(f"{arquivoUsuarios}", sep=';')
    usuarios = usuariosCSV[(usuariosCSV['Tipo'] == 'U')]

    with open(f"{path}/favoritos_python.csv", "w+", encoding="utf8") as file:
        file.write("Código;Mídias Favoritas\n")
        for i in [x for x in usuariosCSV["Código"].tolist()]:
            if usuariosCSV["Tipo"].tolist()[i - 1] == 'U':
                file.write(f"{i};")

                rangeMidias = range(round(abs(random.gauss(15, 8))))
                for f in rangeMidias:
                    if f != rangeMidias[-1]:
                        file.write(f"{random.randrange(1, nMidias + 1)},")
                    else:
                        file.write(f"{random.randrange(1, nMidias + 1)}")
                file.write("\n")
    pass


# Editar valores aqui
nUsuarios = 1000
nFavoritos = nUsuarios
nMidias = 5000
nGeneros = 300

gera_usuarios(nUsuarios)
gera_favoritos(f"{path}/usuarios_python.csv", nMidias)
gera_generos(nGeneros)
gera_midias(nMidias, f"{path}/generos_python.csv", f"{path}/usuarios_python.csv")
