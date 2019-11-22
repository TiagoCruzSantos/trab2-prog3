package sisPPGI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import sisPPGI.excecoes.CodigoRepetidoDocente;
import sisPPGI.excecoes.SiglaVeiculoRepetido;

/**
 * Classe principal do PPGI
 *
 * @author Tiago da Cruz Santos
 * @author Atílio Antônio Dadalto
 */
public class Sistema implements Serializable {
    private HashMap<Long, Docente> docentesCadastrados;
    private ArrayList<Publicacao> publicacoes;
    private HashMap<String, Veiculo> veiculos;
    private HashMap<Integer, Regra> regras;
    private HashMap<String, Qualis> qualificacoes;
    private enum Classificacoes{
        A1("A1"), 
        A2("A2"), 
        B1("B1"), 
        B2("B2"),
        B3("B3"),
        B4("B4"),
        B5("B5"),
        C("C");

        private String classificacao;

        Classificacoes(String classificacao){
            this.classificacao = classificacao;
        }

        public String getClassi(){
            return this.classificacao;
        }
    }

    public Sistema() {
        this.docentesCadastrados = new HashMap<Long, Docente>();
        this.publicacoes = new ArrayList<Publicacao>();
        this.veiculos = new HashMap<String, Veiculo>();
        this.regras = new HashMap<Integer, Regra> ();
        this.qualificacoes = new HashMap<String, Qualis>();
    }

    /**
     * Cadastra um docente no sistema
     *
     * @param docente Docente pré-existente
     */
    public void cadastrarDocente(Docente docente) {
        this.docentesCadastrados.put(docente.getCodigo(), docente);
    }

    /**
     * Carrega os docentes de um arquivo para o sistema
     *
     * @param infile Scanner com o arquivo de docentes aberto
     * @throws CodigoRepetidoDocente se um código de um docente lido já estiver
     *                               cadastrado
     */
    public void carregaDocentes(Scanner infile) throws CodigoRepetidoDocente {
        infile.nextLine();
        infile.useDelimiter(";");
        long codigo;
        String dataNas;
        String nome;
        String dataIng;
        boolean boolCoord;

        while (infile.hasNext()) {
            codigo = infile.nextLong();
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
     * Cadastra um veículo no sistema
     *
     * @param veiculo Veículo pré-existente
     */
    public void cadastrarVeiculo(Veiculo veiculo) {
        this.veiculos.put(veiculo.getSigla(), veiculo);
    }

    /**
     * Carrega os veículos a partir de um arquivo csv
     *
     * @param infile Scanner com o arquivo de veículos aberto
     * @throws SiglaVeiculoRepetido Erro gerado quando veículo a ser inserido já
     *                              tiver sigla existente
     */
    public void carregaVeiculos(Scanner infile) throws SiglaVeiculoRepetido {
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
            } else if (tipo.compareTo("C") == 0){
                infile.nextLine();
                this.cadastrarVeiculo(new Conferencia(sigla, nome, impacto));
            }else {
            	//erro
            }
            // System.out.println("[" + sigla + "][" + nome + "][" + tipo + "][" + impacto +
            // "][" + issn + ']');
        }
    }

    /**
     * Carrega as publicações de um arquivo para o sistema e faz as relações entre
     * as publicações, os docentes e os veiculos
     *
     * @param infile Arquivo das publicações carregadas em um Scanner
     */
    public void carregaPublicacao(Scanner infile) {
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

            for (String aut : autores) {
                long cod = Long.parseLong(aut.replaceAll("\\s+", ""));
                Docente doc = this.docentesCadastrados.get(cod);
                autoresObj.put(cod, doc);
            }

            Veiculo veic = this.veiculos.get(veiculo);
            Publicacao pub = new Publicacao(ano, nome, numero, volume, local, paginaIni, paginaFim, autoresObj, veic);
            for (String aut : autores) {
                long cod = Long.parseLong(aut.replaceAll("\\s+", ""));
                Docente doc = this.docentesCadastrados.get(cod);
                doc.adicionarPublicacao(pub);
            }
            veic.adicionarPublicacao(pub);
            this.publicacoes.add(pub);
        }
    }

    /**
     * Carrega arquivo de qualis e aplica a pontuação nos veículos
     *
     * @param infile Arquivo aberto de qualis
     */
    public void carregaQualis(Scanner infile) {
        infile.useDelimiter(";");
        infile.nextLine();
        int ano;
        String veiculo;
        String nivel;

        while (infile.hasNext()) {
            ano = infile.nextInt();
            veiculo = infile.next();
            nivel = infile.nextLine().split(";")[1];

            Veiculo veic = this.veiculos.get(veiculo);
            Qualis qualis = new Qualis(ano, nivel);
            veic.adicionarQualis(qualis);
            System.out.println("[" + ano + "][" + veiculo + "][" + nivel + "]");
        }
    }

    /**
     * Carrega as regras de pontuação no sistema
     * 
     * @param infile Arquivo de regras aberto
     */
    public void carregaRegra(Scanner infile) {
        infile.useDelimiter(";");
        infile.nextLine();
        String dataIni;
        String dataFim;
        String[] niveis;
        String[] pontos;
        double multiplicador;
        int anos;
        int pontoMinimo;
        HashMap<String, Qualis> qualificacoes = new HashMap<String, Qualis>();
        while (infile.hasNext()) {
            dataIni = infile.next();
            dataFim = infile.next();
            niveis = infile.next().split(",");
            pontos = infile.next().split(",");
            multiplicador = Double.parseDouble(infile.next().replace(',', '.'));
            anos = infile.nextInt();
            pontoMinimo = Integer.parseInt(infile.nextLine().split(";")[1]);
            System.out.print("[" + dataIni + "][" + dataFim + "][");

            for (String nivel : niveis) {
                System.out.print(nivel + "][");

            }

            for (String ponto : pontos) {
                System.out.print(Integer.parseInt(ponto) + "][");
            }

            int count = 0;

            for (Classificacoes cl : Classificacoes.values()){
                if(cl.getClassi().compareTo(niveis[count]) == 0){
                    qualificacoes.put(cl.getClassi(), new Qualis(cl.getClassi(), Integer.parseInt(pontos[count])));
                    count++;
                }else{
                    qualificacoes.put(cl.getClassi(), new Qualis(cl.getClassi(), Integer.parseInt(pontos[count - 1])));
                }
            }

            System.out.println(multiplicador + "][" + anos + "][" + pontoMinimo + "]");
            Regra regraLida = new Regra(dataIni, dataFim, pontoMinimo, anos, multiplicador, qualificacoes);
            this.regras.put(regraLida.getAno(), regraLida);
        }
    }
    
    /**
     * Imprime as publicações no arquivo
     * 
     * @throws IOException Quando não consegue abrir um arquivo para escrever
     */
    public void imprimirPublicacoes() throws IOException {
    	FileWriter outfile = new FileWriter("2-publicacoes.csv");
    	outfile.write("Ano;Sigla Veículo;Veículo;Qualis;Fator de Impacto;Título;Docentes\n");
    	Collections.sort(this.publicacoes);
    	for(Publicacao pub : this.publicacoes) {
    		Qualis qualis = pub.getVeiculo().getQualisAno(pub.getAno());
    		outfile.write(pub.getAno() + ";" + pub.getVeiculo().getSigla() + ";" + pub.getVeiculo().getNome() + ";" + qualis.getNivel() + ";" + String.format("%.3f", pub.getVeiculo().getImpacto()).replace('.', ',') + ";" + pub.getTitulo() + ";");
            int i = 0;
            int size = pub.getAutores().values().size() - 1;
            for(Docente autor: pub.getAutores().values()) {
                if(i++ == size){
                    outfile.write(autor.getNome());
                    break;
                }else{
                    outfile.write(autor.getNome() + ",");
                }
    		}
    		outfile.write("\n");
    	}
    	outfile.close();
    }
    /**
     * Imprime as estatisticas dos qualis
     * 
     * @throws IOException Quando não for possivel abrir o arquivo para escrita
     */
    public void imprimirEstatisticas() throws IOException {
    	FileWriter outfile = new FileWriter("3-estatisticas.csv");
    	outfile.write("Qualis;Qtd. Artigos;Média Artigos / Docente\n");

    	HashMap<String, ArrayList<Publicacao>> pubsPorQualis = new HashMap<String, ArrayList<Publicacao>>();
    	for(Classificacoes cl : Classificacoes.values()) {
    		pubsPorQualis.put(cl.getClassi(), new ArrayList<Publicacao>());
    	}
    	for(Publicacao pub : this.publicacoes) {
    		ArrayList<Publicacao> arrayAtual = pubsPorQualis.get(pub.getVeiculo().getQualisAno(pub.getAno()).getNivel());
    		arrayAtual.add(pub);
        }
        for(Classificacoes cl : Classificacoes.values()){
            int qtdPubsQualis = pubsPorQualis.get(cl.getClassi()).size();
            double qtdAutores = 0;
            double umSobre = 0;
            for(Publicacao publicacao: pubsPorQualis.get(cl.getClassi())){
                qtdAutores += publicacao.getAutores().size();
                umSobre += (double) 1 / (double) publicacao.getAutores().size();
            }
            // double mediaArtAut = qtdAutores/qtdPubsQualis;
            outfile.write(cl.getClassi() + ";" + qtdPubsQualis + ";" + String.format("%.2f",umSobre).replace('.',',') + "\n");
        }
        outfile.close();
    }
    /**
     * Aplica a regra relacionada ao ano de vigência
     * 
     * @param ano Ano de vigência de uma regra
     */
    public void aplicarRegra(int ano) {
    	HashMap<String, Qualis> qualisDoAno = this.regras.get(ano).getQualis();
    	double multiDaRegra = this.regras.get(ano).getMultiplicador();
    	for(Veiculo veic : this.veiculos.values()) {
    		Qualis qualis = veic.getQualisAno(ano);
    		qualis.setPontuacao(qualisDoAno.get(qualis.getNivel()).getPontuacao());
    		if(veic instanceof Periodico) {
    			((Periodico) veic).setMultiplicador(multiDaRegra);;
    		}
    	}
    }
    
    public void imprimirRecredenciamento(int ano) throws IOException {
    	FileWriter outfile = new FileWriter("1-recredenciamento.csv");
    	outfile.write("Docente;Pontuação;Recredenciado?\n");
    	this.aplicarRegra(ano);
    	outfile.close();
    }
    
    @Override
    public String toString() {
        return "Sistema [docentesCadastrados=\n" + this.docentesCadastrados + ", publicacoes=\n" + this.publicacoes
                + ", veiculos=\n" + this.veiculos + ", regras=" + this.regras + ", qualificacoes=" + this.qualificacoes
                + "]";
    }
}