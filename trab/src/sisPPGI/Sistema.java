package sisPPGI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.plaf.multi.MultiListUI;

import sisPPGI.excecoes.CodigoRepetidoDocente;
import sisPPGI.excecoes.SiglaVeiculoRepetido;

/**
 * Classe principal do PPGI
 *
 * @author Tiago da Cruz Santos
 * @author Atílio Antônio Dadalto
 *
 */
public class Sistema implements Serializable {
    private HashMap<Long, Docente> docentesCadastrados;
    private ArrayList<Publicacao> publicacoes;
    private HashMap<String, Veiculo> veiculos;
    private ArrayList<Regra> regras;
    private ArrayList<Qualis> qualificacoes;

    public Sistema() {
        this.docentesCadastrados = new HashMap<Long, Docente>();
        this.publicacoes = new ArrayList<Publicacao>();
        this.veiculos = new HashMap<String, Veiculo>();
        this.regras = new ArrayList<Regra>();
        this.qualificacoes = new ArrayList<Qualis>();
    }

    /**
     * Cadastra um docente no sistema
     *
     * @param docente Docente pré-existente
     * @param cod     Código do docente para mapear
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
     * Carrega os veiculos a partir de um arquivo csv
     *
     * @param infile Scanner com o arquivo de veiculos aberto
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
            tipo = infile.next();
            impactoStr = infile.next();
            impacto = Double.parseDouble(impactoStr.replace(',', '.'));

            String issn = null;
            if (tipo.compareTo("P") == 0) {
                issn = infile.nextLine();
                issn = (issn.split(";"))[1];
                this.cadastrarVeiculo(new Periodico(sigla, nome, impacto, issn));
            } else {
                infile.nextLine();
                this.cadastrarVeiculo(new Conferencia(sigla, nome, impacto));
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

            Publicacao pub = new Publicacao(ano, nome, numero, volume, local, paginaIni, paginaFim, autoresObj);
            for (String aut : autores) {
                long cod = Long.parseLong(aut.replaceAll("\\s+", ""));
                Docente doc = this.docentesCadastrados.get(cod);
                doc.adicionarPublicacao(pub);
            }
            this.veiculos.get(veiculo).adicionarPublicacao(pub);
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

    public void carregaRegra(Scanner infile) {
        infile.useDelimiter(";");
        infile.nextLine();
        String dataIni;
        String dataFim;
        String[] niveis;
        String[] pontos;
        double multiplicador;
        int anos;
        int maxPontos;

        while (infile.hasNext()) {
            dataIni = infile.next();
            dataFim = infile.next();
            niveis = infile.next().split(",");
            pontos = infile.next().split(",");
            multiplicador = Double.parseDouble(infile.next().replace(',', '.'));
            anos = infile.nextInt();
            maxPontos = Integer.parseInt(infile.nextLine().split(";")[1]);
            System.out.print("[" + dataIni + "][" + dataFim + "][");

            for (String nivel : niveis) {
                System.out.print(nivel + "][");
            }

            for (String ponto : pontos) {
                System.out.print(Integer.parseInt(ponto) + "][");
            }
            System.out.println(multiplicador + "][" + anos + "][" + maxPontos + "]");
        }
    }

    @Override
    public String toString() {
        return "Sistema [docentesCadastrados=\n" + this.docentesCadastrados + ", publicacoes=\n" + this.publicacoes
                + ", veiculos=\n" + this.veiculos + ", regras=" + this.regras + ", qualificacoes=" + this.qualificacoes
                + "]";
    }
}
