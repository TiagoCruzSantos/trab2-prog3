package sisPPGI;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe de representação do Qualis
 *
 * @author Tiago da Cruz Santos
 * @author Atílio Antônio Dadalto
 */
public class Qualis implements Serializable {
    private String nivel;
    private int ano;
    private double pontuacao;

    public Qualis(int ano, String nivel) {
        this.nivel = nivel;
        this.ano = ano;
    }

    /**
     * @return Nível da qualis
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel Nível da qualis a armazenar
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * @return Pontuação do nível desta qualis
     */
    public double getPontuacao() {
        return pontuacao;
    }

    /**
     * @param pontuacao Pontuação em {@code double} do nível da qualis a armazenar
     */
    public void setPontuacao(double pontuacao) {
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
}
