package meupacote;

import java.util.ArrayList;
import java.util.Date;
/**
 * Classe de representação de docente
 * 
 * @author Tiago
 * @author Atilio
 */
public class Docente {

	private int codigo;
	private String nome;
	private Date dataNascimento;
	private Date dataIngresso;
	private boolean coordenador;
	private ArrayList<Publicacao> publicacoes;
	
	public Docente(int codigo, String nome, Date dataNascimento, Date dataIngresso, boolean coordenador) {
		this.codigo = codigo;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.dataIngresso = dataIngresso;
		this.coordenador = coordenador;
		this.publicacoes = new ArrayList<Publicacao>();
	}

	/**
	 * @return Codigo do docente
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * @param codigo Codigo inteiro para armazenar
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Nome do docente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome Nome do docente para armazenar
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Data de nascimento do docente
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento Data de nascimente para armazenar
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return Data de ingresso do docente no sistema
	 */
	public Date getDataIngresso() {
		return dataIngresso;
	}

	/**
	 * @param dataIngresso Data de ingresso do docente no sistema para armazenar
	 */
	public void setDataIngresso(Date dataIngresso) {
		this.dataIngresso = dataIngresso;
	}

	/**
	 * @return Verdadeiro se for coordenador ou falso se não for
	 */
	public boolean isCoordenador() {
		return coordenador;
	}

	/**
	 * @param coordenador Valor boleano para ver se docente é coordenador ou não
	 */
	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}

	/**
	 * @return Lista de publicações do docente
	 */
	public ArrayList<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	/**
	 * @param publicacoes Lista de publicações do docente
	 */
	public void setPublicacoes(ArrayList<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}
	
	@Override
	public String toString() {
		return "Docente [codigo=" + codigo + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", dataIngresso="
				+ dataIngresso + ", coordenador=" + coordenador + ", publicacoes=" + publicacoes + "]";
	}
	
}
