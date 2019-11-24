package sisPPGI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;

import sisPPGI.excecoes.CodigoDocenteIndefinido;
import sisPPGI.excecoes.CodigoRepetidoDocente;
import sisPPGI.excecoes.SiglaRepetidaVeiculo;
import sisPPGI.excecoes.QualisDesconhecidoRegra;
import sisPPGI.excecoes.QualisDesconhecidoVeiculo;
import sisPPGI.excecoes.SiglaVeiculoPublicacaoIndefinida;
import sisPPGI.excecoes.SiglaRepetidaVeiculo;
import sisPPGI.excecoes.SiglaIndefinidaVeiculo;
import sisPPGI.excecoes.TipoVeiculoDesconhecido;

/**
 * Classe principal do PPGI.
 *
 * @author Tiago da Cruz Santos.
 * @author Atílio Antônio Dadalto.
 */
public class Sistema implements Serializable {
    private HashMap<Long, Docente> docentesCadastrados;
    private ArrayList<Publicacao> publicacoes;
    private HashMap<String, Veiculo> veiculos;
    private HashMap<Integer, Regra> regras;
    private HashMap<String, Qualis> qualificacoes;

    private enum Classificacoes { // Precisa JavaDoczar isso?
        A1("A1"), A2("A2"), B1("B1"), B2("B2"), B3("B3"), B4("B4"), B5("B5"), C("C");

        private String classificacao;

        Classificacoes(String classificacao) {
            this.classificacao = classificacao;
        }

        public String getClassi() {
            return this.classificacao;
        }
    }

    public Sistema() {
        this.docentesCadastrados = new HashMap<Long, Docente>();
        this.publicacoes = new ArrayList<Publicacao>();
        this.veiculos = new HashMap<String, Veiculo>();
        this.regras = new HashMap<Integer, Regra>();
        this.qualificacoes = new HashMap<String, Qualis>();
    }

    /**
     * Cadastra um docente no sistema.
     *
     * @param docente Docente pré-existente.
     */
    public void cadastrarDocente(Docente docente) {
        this.docentesCadastrados.put(docente.getCodigo(), docente);
    }

    /**
     * Carrega os docentes de um arquivo para o sistema.
     *
     * @param infile Scanner com o arquivo de docentes aberto.
     * @throws CodigoRepetidoDocente se um código de um docente lido já estiver
     *                               cadastrado.
     * @throws InputMismatchException se houver erro de formatação
     * @throws ArrayIndexOutOfBoundsException se houver erro de formatação
     *
     */
    public void carregaArquivoArquivoDocentes(Scanner infile) throws CodigoRepetidoDocente, InputMismatchException {
        /* TODO: Detectar erros de formatação:
         * Código: não ser número inteiro (erro 1)
         * Nome: não ser alfabético (erro 2)
         * Data de nascimento: não ser data (erro 3)
         * Data de ingresso: não ser data (erro 4)
         * Coordenador: se preenchido, não ser char 'X' (erro 5)
         */
        infile.nextLine(); // Ignora primeira linha
        infile.useDelimiter(";");

        long codigo;
        String dataNas;
        String dataIng;
        String nome;
        boolean boolCoord;

        while (infile.hasNext()) {
            codigo = infile.nextLong();

            // System.out.print("[" + codigo + "]");

            /* Verificar se isso tudão é válido */
            if (this.docentesCadastrados.containsKey(codigo)) {
                throw new CodigoRepetidoDocente(codigo);
            }
            nome = infile.next();
            // System.out.print("[" + nome + "]");
            dataNas = infile.next();
            // System.out.print("[" + dataNas + "]");
            dataIng = infile.next();
            // System.out.print("[" + dataIng + "]");
            if (infile.hasNext("(X\n)\\w*")) {
                boolCoord = true;
                infile.nextLine();
            } else {
                boolCoord = false;
                infile.nextLine();
            }
            // System.out.println("[" + boolCoord + "]");
            this.cadastrarDocente(new Docente(codigo, nome, dataNas, dataIng, boolCoord));
        }
    }

    /**
     * Cadastra um veículo no sistema.
     *
     * @param veiculo Veículo pré-existente.
     */
    public void cadastrarVeiculo(Veiculo veiculo) {
        this.veiculos.put(veiculo.getSigla(), veiculo);
    }

    /**
     * Carrega os veículos a partir de um arquivo csv.
     *
     * @param infile Scanner com o arquivo de veículos aberto.
     * @throws SiglaRepetidaVeiculo     se um veículo a ser inserido possuir
     *                                  sigla já utilizada.
     * @throws TipoVeiculoDesconhecido  se um veículo tiver um tipo desconhecido
     * @throws InputMismatchException se houver erro de formatação
     *
     */
    public void carregaArquivoVeiculos(Scanner infile) throws SiglaRepetidaVeiculo, TipoVeiculoDesconhecido, InputMismatchException {
        /* TODO: Detectar erros de formatação:
         * Impacto: não ser número (inteiro ou float) (erro 19)
         * ISSN: não ser um ISSN válido (pelo menos 7 números e 1 char) (erro 20)
         */
        infile.nextLine(); // Ignora primeira linha
        infile.useDelimiter(";");

        String sigla;
        String nome;
        String tipo;
        String impactoStr;
        double impacto;

        while (infile.hasNext()) {
            sigla = infile.next();
            sigla = sigla.trim();
            if (this.veiculos.containsKey(sigla)) {
                throw new SiglaRepetidaVeiculo(sigla);
            }

            nome = infile.next();
            nome = nome.trim();
            tipo = infile.next();
            impactoStr = infile.next();
            impacto = Double.parseDouble(impactoStr.replace(',', '.'));

            String issn = null;
            if (tipo.compareTo("P") == 0) {
                issn = infile.nextLine();
                issn = (issn.split(";"))[1];
                this.cadastrarVeiculo(new Periodico(sigla, nome, impacto, issn));
            } else if (tipo.compareTo("C") == 0) {
                infile.nextLine();
                this.cadastrarVeiculo(new Conferencia(sigla, nome, impacto));
            } else {
                throw new TipoVeiculoDesconhecido(sigla, tipo);
            }
            // System.out.println("[" + sigla + "][" + nome + "][" + tipo + "][" + impacto +
            // "][" + issn + ']');
        }
    }

    /**
     * Carrega as publicações de um arquivo para o sistema e faz as relações entre
     * as publicações, os docentes e os veículos.
     *
     * @param infile Arquivo das publicações carregadas em um Scanner.
     * @throws CodigoDocenteIndefinido se o código de um docente não tiver sido
     * definido na planilha de docentes
     * @throws SiglaVeiculoPublicacaoIndefinida se a sigla de um veículo não tiver sido
     * definida na planilha de veículos
     * @throws InputMismatchException se houver erro de formatação
     *
     */
    public void carregaArquivoPublicacao(Scanner infile) throws CodigoDocenteIndefinido, SiglaVeiculoPublicacaoIndefinida, InputMismatchException {
        /* TODO: Detectar erros de formatação:
         * Ano: não ser número inteiro (erro 6)
         * Autores: não serem números inteiros (erro 7)
         * Número: não ser número inteiro :-O!! (erro 8)
         * Volume: não ser número inteiro (erro 9)
         * Página Inicial: não ser número inteiro (erro 10)
         * Página Final: não ser número inteiro (erro 11)
         */
        infile.nextLine(); // Ignora primeira linha
        infile.useDelimiter(";");

        int ano;
        String siglaVeiculo;
        String titulo;
        String[] autores;
        int numero;
        int volume;
        String local;
        int paginaIni;
        int paginaFim;

        while (infile.hasNext()) {
            HashMap<Long, Docente> autoresObj = new HashMap<Long, Docente>();
            volume = -1;
            ano = infile.nextInt();
            siglaVeiculo = infile.next();
            siglaVeiculo = siglaVeiculo.trim();
            titulo = infile.next();
            titulo = titulo.trim();
            autores = infile.next().split(",");
            numero = infile.nextInt();

            if (infile.hasNextInt()) {
                volume = infile.nextInt();
            } else {
                infile.next();
            }

            local = infile.next();
            paginaIni = infile.nextInt();
            paginaFim = Integer.parseInt(infile.nextLine().split(";")[1]);

            for (String itAutores : autores) {
                long cod = Long.parseLong(itAutores.replaceAll("\\s+", ""));
                Docente doc = this.docentesCadastrados.get(cod);
                if (doc == null) {
                    throw new CodigoDocenteIndefinido(titulo, cod);
                }
                autoresObj.put(cod, doc);
            }

            Veiculo veiculo = this.veiculos.get(siglaVeiculo);
            if (veiculo == null) {
                throw new SiglaVeiculoPublicacaoIndefinida(titulo, siglaVeiculo);
            }
            Publicacao pub = new Publicacao(ano, titulo, numero, volume, local, paginaIni, paginaFim, autoresObj, veiculo);
            for (String itAutores : autores) {
                long cod = Long.parseLong(itAutores.replaceAll("\\s+", ""));
                Docente doc = this.docentesCadastrados.get(cod);
                doc.adicionarPublicacao(pub);
            }
            veiculo.adicionarPublicacao(pub);
            this.publicacoes.add(pub);
        }
    }

    /**
     * Carrega arquivo de Qualis e aplica a pontuação nos veículos.
     *
     * @param infile Arquivo aberto de qualis.
     * @throws QualisDesconhecidoVeiculo se houver Qualis desconhecida para um veículo
     * @throws SiglaIndefinidaVeiculo se houver sigla de veículo não definida na planilha de veículos
     * @throws InputMismatchException se houver erro de formatação
     *
     */
    public void carregaArquivoQualis(Scanner infile) throws SiglaIndefinidaVeiculo, QualisDesconhecidoVeiculo, InputMismatchException {
        /* TODO: Detectar erro de inconsistência: SiglaIndefinidaVeiculo:
         * Exceção para quando a sigla de um veículo especificada para uma qualificação
         * não foi definida na planilha de veículos.
         * REVIEW: na verdade acho que eu corrigi ali embaixo. Plot tuiste.
         *
         * TODO: Detectar erros de formatação:
         * Ano: não ser número inteiro (erro 12)
         */
        infile.useDelimiter(";");
        infile.nextLine();
        int ano;
        String siglaVeiculo;
        String nivel;

        while (infile.hasNext()) {
            ano = infile.nextInt();
            siglaVeiculo = infile.next();
            siglaVeiculo = siglaVeiculo.trim();
            nivel = infile.nextLine().split(";")[1];

            try {
            	Classificacoes.valueOf(nivel);
            } catch(Exception e) {
            	throw new QualisDesconhecidoVeiculo(siglaVeiculo, ano, nivel, e);
            }

            // System.out.println("[" + ano + "][" + siglaVeiculo + "][" + nivel + "]");
            Veiculo veiculo = this.veiculos.get(siglaVeiculo);
            if (veiculo == null) {
                throw new SiglaIndefinidaVeiculo(ano, siglaVeiculo);
            }
            Qualis qualis = new Qualis(ano, nivel);
            veiculo.adicionarQualis(qualis);
        }
    }

    /**
     * Carrega as regras de pontuação no sistema.
     *
     * @param infile Arquivo de regras aberto.
     * @throws QualisDesconhecidoRegra se houver Qualis desconhecida para uma regra
     * @throws InputMismatchException se houver erro de formatação
     * @throws ArrayIndexOutOfBoundsException se houver erro de formatação
     *
     */
    public void carregaArquivoRegra(Scanner infile) throws QualisDesconhecidoRegra, InputMismatchException {
        /* TODO: Detectar erros de formatação:
         * Início de vigência: não ser data (erro 13)
         * Fim de vigência: não ser data (erro 14)
         * Pontos: não ser número inteiros (erro 15)
         * Multiplicador: não ser número (inteiro ou float) (erro 16)
         * Anos: não ser número inteiro (erro 17)
         * Mínimo pontos: não ser número inteiro (erro 18)
         */
        infile.useDelimiter(";");
        infile.nextLine();
        String dataIni;
        String dataFim;
        String[] niveis;
        String[] pontos;
        double multiplicador;
        int anos;
        int pontoMinimo;
        while (infile.hasNext()) {
            HashMap<String, Qualis> qualificacoes = new HashMap<String, Qualis>();
            dataIni = infile.next();
            dataFim = infile.next();
            niveis = infile.next().split(",");
            pontos = infile.next().split(",");
            multiplicador = Double.parseDouble(infile.next().replace(',', '.'));
            anos = infile.nextInt();
            pontoMinimo = Integer.parseInt(infile.nextLine().split(";")[1]);
            for(String nivel: niveis){
            	try {
            		Classificacoes.valueOf(nivel);
            	}catch(Exception e){
            		throw new QualisDesconhecidoRegra(dataIni, nivel, e);
            	}
            }
            /*
             * System.out.print("[" + dataIni + "][" + dataFim + "][");
             *
             * for (String nivel : niveis) { System.out.print(nivel + "]["); }
             *
             * for (String ponto : pontos) { System.out.print(Integer.parseInt(ponto) +
             * "]["); } System.out.println(multiplicador + "][" + anos + "][" + pontoMinimo
             * + "]");
             */
            int count = 0;

            for (Classificacoes itCl : Classificacoes.values()) {
                if (count < niveis.length && itCl.getClassi().compareTo(niveis[count]) == 0) {
                    qualificacoes.put(itCl.getClassi(), new Qualis(itCl.getClassi(), Integer.parseInt(pontos[count])));
                    count++;
                } else {
                    qualificacoes.put(itCl.getClassi(),
                            new Qualis(itCl.getClassi(), Integer.parseInt(pontos[count - 1])));
                }
            }

            Regra regraLida = new Regra(dataIni, dataFim, pontoMinimo, anos, multiplicador, qualificacoes);
            // System.out.println(regraLida.getAno());
            this.regras.put(regraLida.getAno(), regraLida);
        }
    }

    /**
     * Imprime as publicações no arquivo.
     *
     * @throws IOException Quando não consegue abrir um arquivo para escrever.
     */
    public void imprimirPublicacoes() throws IOException {
        FileWriter outfile = new FileWriter("2-publicacoes.csv");
        outfile.write("Ano;Sigla Veículo;Veículo;Qualis;Fator de Impacto;Título;Docentes\n");
        Collections.sort(this.publicacoes);
        for (Publicacao itPub : this.publicacoes) {
            Qualis qualis = itPub.getVeiculo().getQualisAno(itPub.getAno());
            if(qualis != null){
                outfile.write(itPub.getAno() + ";" + itPub.getVeiculo().getSigla() + ";" + itPub.getVeiculo().getNome()
                        + ";" + qualis.getNivel() + ";"
                        + String.format("%.3f", itPub.getVeiculo().getImpacto()).replace('.', ',') + ";" + itPub.getTitulo()
                        + ";");
                int i = 0;
                int size = itPub.getAutores().values().size() - 1;
                for (Docente itAutor : itPub.getAutores().values()) {
                    if (i++ == size) {
                        outfile.write(itAutor.getNome());
                        break;
                    } else {
                        outfile.write(itAutor.getNome() + ",");
                    }
                }
                outfile.write("\n");
            }
        }
        outfile.close();
    }

    /**
     * Imprime as estatísticas dos Qualis.
     *
     * @throws IOException Quando não for possível abrir o arquivo para escrita.
     */
    public void imprimirEstatisticas() throws IOException {
        FileWriter outfile = new FileWriter("3-estatisticas.csv");
        outfile.write("Qualis;Qtd. Artigos;Média Artigos / Docente\n");

        HashMap<String, ArrayList<Publicacao>> pubsPorQualis = new HashMap<String, ArrayList<Publicacao>>();
        for (Classificacoes itCl : Classificacoes.values()) {
            pubsPorQualis.put(itCl.getClassi(), new ArrayList<Publicacao>());
        }
        for (Publicacao itPub : this.publicacoes) {
            if(itPub.getVeiculo().getQualisAno(itPub.getAno()) != null) {
                ArrayList<Publicacao> arrayAtual = pubsPorQualis
                        .get(itPub.getVeiculo().getQualisAno(itPub.getAno()).getNivel());
                arrayAtual.add(itPub);
            }
        }
        for (Classificacoes itCl : Classificacoes.values()) {
            int qtdPubsQualis = pubsPorQualis.get(itCl.getClassi()).size();

            double mediaAutoresArtigo = 0;
            for (Publicacao itPub : pubsPorQualis.get(itCl.getClassi())) {
                mediaAutoresArtigo += (double) 1 / (double) itPub.getAutores().size();
            }

            outfile.write(itCl.getClassi() + ";" + qtdPubsQualis + ";"
                    + String.format("%.2f", mediaAutoresArtigo).replace('.', ',') + "\n");
        }
        outfile.close();
    }

    /**
     * Aplica a regra relacionada ao ano de vigência.
     *
     * @param ano Ano de vigência de uma regra.
     */
    public void aplicarRegra(int ano) {
        HashMap<String, Qualis> qualisDoAno = this.regras.get(ano).getQualis();
        double multiDaRegra = this.regras.get(ano).getMultiplicador();
        int range = this.regras.get(ano).getAnosConsiderados();
        for (Veiculo itVeiculo : this.veiculos.values()) {
            // System.out.println("aqui?");
            for (int i = ano; i >= ano - range; i--) {
                Qualis qualis = itVeiculo.getQualisAno(i);
                // System.out.println(this.regras.get(ano).getAno() + ";" + qualis.getNivel() +
                // ";" + itVeiculo.getNome() + ";" +
                // qualisDoAno.get(qualis.getNivel()).getPontuacao());
                if (qualis != null) {
                    qualis.setPontuacao(qualisDoAno.get(qualis.getNivel()).getPontuacao());
                    if (itVeiculo instanceof Periodico) {
                        ((Periodico) itVeiculo).setMultiplicador(multiDaRegra);
                    }
                }
            }
        }
    }

    /**
     * Faz o recredenciamento de docentes no ano passado como parâmetro.
     *
     * @param ano Ano para recredenciamento.
     */
    public void imprimirRecredenciamento(int ano) throws IOException {
        FileWriter outfile = new FileWriter("1-recredenciamento.csv");
        outfile.write("Docente;Pontuação;Recredenciado?\n");
        this.aplicarRegra(ano);
        ArrayList<Docente> docentes = new ArrayList<Docente>(this.docentesCadastrados.values());
        Collections.sort(docentes);
        for (Docente itDocente : docentes) {
            double ponto = itDocente.calculaPontuacao(ano, this.regras.get(ano).getAnosConsiderados());
            outfile.write(itDocente.getNome() + ';' + String.format("%.1f", ponto).replace('.', ',') + ";");
            if (itDocente.isCoordenador()) {
                outfile.write("Coordenador\n");
            } else if (ano - itDocente.getAnoIngresso() <= 3) {
                outfile.write("PPJ\n");
            } else if (itDocente.getIdade(ano) >= 60) {
                outfile.write("PPS\n");
            } else if (ponto >= this.regras.get(ano).getPontoMinimo()) {
                outfile.write("Sim\n");
            } else {
                outfile.write("Não\n");
            }
        }
        outfile.close();
    }

    @Override
    public String toString() {
        return "Sistema [docentesCadastrados=\n" + this.docentesCadastrados + ", publicacoes=\n" + this.publicacoes
                + ", veiculos=\n" + this.veiculos + ", regras=" + this.regras + ", qualificacoes=" + this.qualificacoes
                + "]";
    }
}
