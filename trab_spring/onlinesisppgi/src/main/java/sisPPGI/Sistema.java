package sisPPGI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import sisPPGI.excecoes.CodigoDocenteIndefinido;
import sisPPGI.excecoes.CodigoRepetidoDocente;
import sisPPGI.excecoes.SiglaVeiculoRepetido;

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

    private enum Classificacoes {
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
     */
    public void carregaArquivoArquivoDocentes(Scanner infile) throws CodigoRepetidoDocente {
        infile.nextLine();
        infile.useDelimiter(";");
        long codigo;
        String dataNas;
        String nome;
        String dataIng;
        boolean boolCoord;

        while (infile.hasNext()) {

            codigo = infile.nextLong();
            // System.out.println(codigo);

            // try {
            // codigo = Long.parseLong(codigo2);
            // System.exit(1);
            // } catch (NumberFormatException | NullPointerException nfe) {
            // }

            // System.out.print("[" + codigo + "]");
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
     * @throws SiglaVeiculoRepetido Erro gerado quando veículo a ser inserido já
     *                              tiver sigla existente.
     */
    public void carregaArquivoVeiculos(Scanner infile) throws SiglaVeiculoRepetido {
        infile.nextLine();
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
                throw new SiglaVeiculoRepetido(sigla);
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
                // erro ava e memo Eb neezer
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
     */
    public void carregaArquivoPublicacao(Scanner infile) throws CodigoDocenteIndefinido{
        infile.useDelimiter(";");
        infile.nextLine();
        int ano;
        String veiculo;
        String nome;
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
            veiculo = infile.next();
            veiculo = veiculo.trim();
            nome = infile.next();
            nome = nome.trim();
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
                if(doc == null){
                    throw new CodigoDocenteIndefinido(nome, cod);
                }
                autoresObj.put(cod, doc);
            }

            Veiculo veic = this.veiculos.get(veiculo);
            Publicacao pub = new Publicacao(ano, nome, numero, volume, local, paginaIni, paginaFim, autoresObj, veic);
            for (String itAutores : autores) {
                long cod = Long.parseLong(itAutores.replaceAll("\\s+", ""));
                Docente doc = this.docentesCadastrados.get(cod);
                doc.adicionarPublicacao(pub);
            }
            veic.adicionarPublicacao(pub);
            this.publicacoes.add(pub);
        }
    }

    /**
     * Carrega arquivo de qualis e aplica a pontuação nos veículos.
     *
     * @param infile Arquivo aberto de qualis.
     */
    public void carregaArquivoQualis(Scanner infile) {
        infile.useDelimiter(";");
        infile.nextLine();
        int ano;
        String veiculo;
        String nivel;

        while (infile.hasNext()) {
            ano = infile.nextInt();
            veiculo = infile.next();
            veiculo = veiculo.trim();
            nivel = infile.nextLine().split(";")[1];

            //System.out.println("[" + ano + "][" + veiculo + "][" + nivel + "]");
            Veiculo veic = this.veiculos.get(veiculo);
            Qualis qualis = new Qualis(ano, nivel);
            veic.adicionarQualis(qualis);
        }
    }

    /**
     * Carrega as regras de pontuação no sistema.
     *
     * @param infile Arquivo de regras aberto.
     */
    public void carregaArquivoRegra(Scanner infile) {
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

            /*
            System.out.print("[" + dataIni + "][" + dataFim + "][");
            
            for (String nivel : niveis) { System.out.print(nivel + "]["); }
            
            for (String ponto : pontos) { System.out.print(Integer.parseInt(ponto) +
            "]["); }
            System.out.println(multiplicador + "][" + anos + "][" + pontoMinimo + "]");
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
            //System.out.println(regraLida.getAno());
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
            ArrayList<Publicacao> arrayAtual = pubsPorQualis
                    .get(itPub.getVeiculo().getQualisAno(itPub.getAno()).getNivel());
            arrayAtual.add(itPub);
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
            //System.out.println("aqui?");
            for(int i = ano; i >= ano - range; i--){
                Qualis qualis = itVeiculo.getQualisAno(i);
                //System.out.println(this.regras.get(ano).getAno() + ";" + qualis.getNivel() + ";" + itVeiculo.getNome() + ";" + qualisDoAno.get(qualis.getNivel()).getPontuacao());
                if(qualis != null){
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
            } else if (ano - itDocente.getAnoIngresso() < 3) {
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
