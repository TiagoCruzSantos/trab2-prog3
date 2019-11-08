package sisPPGI;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Classe base para representação de Veiculo
 * 
 * @author Tiago
 * @author Atilio
 */
public abstract class Veiculo implements Serializable{
	protected String sigla;
	protected String nome;
	protected double impacto;
	protected Qualis qualis;
	protected ArrayList<Publicacao> publicacoes;
	
	
	
	public Veiculo(String sigla, String nome, double impacto) {
		this.sigla = sigla;
		this.nome = nome;
		this.impacto = impacto;
		this.publicacoes = new ArrayList<Publicacao>();
	}
	/**
	 * @return Sigla do nome do veiculo
	 */
	public String getSigla() {
		return sigla;
	}
	/**
	 * @param sigla Sigla do nome do veiculo
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	/**
	 * @return Nome do veiculo
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome Nome do veiculo
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return Impacto do qualis
	 */
	public double getImpacto() {
		return impacto;
	}
	/**
	 * @param impacto Impacto do qualis
	 */
	public void setImpacto(double impacto) {
		this.impacto = impacto;
	}
	/**
	 * @return Lista de publicações do veiculo
	 */
	public ArrayList<Publicacao> getPublicacoes() {
		return publicacoes;
	}
	/**
	 * @param publicacoes Lista de publicações do veiculo
	 */
	public void setPublicacoes(ArrayList<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}
	
}
