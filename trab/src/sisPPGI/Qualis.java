package sisPPGI;

import java.io.Serializable;

/**
 * Classe de representação do Qualis
 *
 * @author Tiago da Cruz Santos
 * @author Atílio Antônio Dadalto
 */
public class Qualis implements Serializable {
    private String nivel;
    private int ano;
    private int pontuacao;
    
    /**
     * Cria um novo qualis
     * 
     * @param ano Ano do qualis
     * @param nivel Nivel do qualis
     */
    public Qualis(int ano, String nivel) {
        this.nivel = nivel;
        this.ano = ano;
    }
    /**
     * Cria um novo qualis
     * 
     * @param nivel NIvel do qualis
     * @param ponto Pontuação
     */
    public Qualis(String nivel, int ponto) {
        this.nivel = nivel;
        this.pontuacao = ponto;
    }

    /**
     * @return Nível do qualis
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel Nível do qualis a armazenar
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * @return Pontuação do nível deste qualis
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * @param pontuacao Pontuação em {@code double} do nível do qualis a armazenar
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * @return O ano deste qualis
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano Ano em {@code int} do qualis a armazenar
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "[Nivel = " + this.nivel + ":Pontuação = "+ this.pontuacao+"]";
    }
}
