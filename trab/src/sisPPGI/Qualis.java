package sisPPGI;

import java.io.Serializable;

/**
 * Classe de representação do Qualis
 * 
 * @author Tiago
 * @author Atilio
 */
public class Qualis implements Serializable{
	private String nivel;
	private int ano;
	private double pontuacao;
	
	public Qualis(int ano, String nivel) {
		this.nivel = nivel;
		this.setAno(ano);
	}

	/**
	 * @return Nivel
	 */
	public String getNivel() {
		return nivel;
	}
	
	/**
	 * @param nivel Nivel da qualis
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	/**
	 * @return Pontuação do nivel da qualis
	 */
	public double getPontuacao() {
		return pontuacao;
	}
	
	/**
	 * @param pontuacao Pontuação do nivel da qualis
	 */
	public void setPontuacao(double pontuacao) {
		this.pontuacao = pontuacao;
	}

	/**
	 * @return O ano desse qualis
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * @param ano Ano do qualis
	 */
	public void setAno(int ano) {
		this.ano = ano;
	}
}
