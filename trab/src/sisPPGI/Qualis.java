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

    public Qualis(String nivel, double ponto) {
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
    public double getPontuacao() {
        return pontuacao;
    }

    /**
     * @param pontuacao Pontuação em {@code double} do nível do qualis a armazenar
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

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "[Nivel = " + this.nivel + ":Pontuação = "+ this.pontuacao+"]";
    }
}
