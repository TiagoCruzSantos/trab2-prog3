package sisPPGI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe base para representação de Veiculo.
 *
 * @author Tiago da Cruz Santos.
 * @author Atílio Antônio Dadalto.
 */
public abstract class Veiculo implements Serializable {
    protected String sigla;
    protected String nome;
    protected double impacto;
    protected HashMap<Integer, Qualis> qualis;
    protected ArrayList<Publicacao> publicacoes;

    /**
     * Construtor de um veículo para classes herdadas.
     *
     * @param sigla   Sigla do veículo.
     * @param nome    Nome do veículo.
     * @param impacto Impacto em {@code double}.
     */
    public Veiculo(String sigla, String nome, double impacto) {
        this.sigla = sigla;
        this.nome = nome;
        this.impacto = impacto;
        this.publicacoes = new ArrayList<Publicacao>();
        this.qualis = new HashMap<Integer, Qualis>();
    }

    /**
     * @return Sigla do nome do veículo.
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * @param sigla Sigla do nome do veículo.
     */
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    /**
     * @return Nome do veículo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Nome do veículo.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Impacto do qualis.
     */
    public double getImpacto() {
        return impacto;
    }

    /**
     * @param impacto Impacto do qualis.
     */
    public void setImpacto(double impacto) {
        this.impacto = impacto;
    }

    /**
     * @return Lista de publicações do veículo.
     */
    public ArrayList<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    /**
     * @param publicacoes Lista de publicações do veículo.
     */
    public void setPublicacoes(ArrayList<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    /**
     * @param pub Uma publicação.
     */
    public void adicionarPublicacao(Publicacao pub) {
        this.publicacoes.add(pub);
    }

    /**
     * @param qualis Uma qualis.
     */
    public void adicionarQualis(Qualis qualis) {
        this.qualis.put(qualis.getAno(), qualis);
    }

    public HashMap<Integer, Qualis> getQualis() {
        return this.qualis;
    }

    public Qualis getQualisAno(int ano) {
        Qualis qualisAno = this.getQualis().get(ano);
        if (qualisAno == null) {
            for (Qualis itQualis : this.getQualis().values()) {
                if (itQualis.getAno() < ano) {
                    if (qualisAno == null) {
                        qualisAno = itQualis;
                    } else if (qualisAno.getAno() < itQualis.getAno()) {
                        qualisAno = itQualis;
                    }
                }
            }
        }
        return qualisAno;
    }
}
