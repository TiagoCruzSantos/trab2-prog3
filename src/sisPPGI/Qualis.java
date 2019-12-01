package sisPPGI;

import java.io.Serializable;

/**
 * Classe de representação do Qualis.
 *
 * @author Tiago da Cruz Santos
 * @author Atílio Antônio Dadalto
 */
public class Qualis implements Serializable {
    private String nivel;
    private int ano;
    private int pontuacao;

    /**
     * Cria um novo Qualis.
     *
     * @param ano Ano do Qualis.
     * @param nivel Nível do Qualis.
     */
    public Qualis(int ano, String nivel) {
        this.nivel = nivel;
        this.ano = ano;
    }
    /**
     * Cria um novo Qualis.
     *
     * @param nivel Nível do Qualis.
     * @param ponto Pontuação.
     */
    public Qualis(String nivel, int ponto) {
        this.nivel = nivel;
        this.pontuacao = ponto;
    }

    /**
     * @return Nível do Qualis.
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel Nível do Qualis.
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * @return Pontuação do nível deste Qualis.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * @param pontuacao Pontuação em {@code double} do nível do qualis.
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * @return O ano deste qualis.
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano Ano em {@code int} do qualis.
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "[Nivel = " + this.nivel + ":Pontuação = "+ this.pontuacao+"]";
    }
}
