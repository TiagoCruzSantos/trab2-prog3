package sisPPGI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Classe de representação de docente
 * 
 * @author Tiago
 * @author Atilio
 */
public class Docente implements Serializable{

	private long codigo;
	private String nome;
	private GregorianCalendar dataNascimento;
	private GregorianCalendar dataIngresso;
	private boolean coordenador;
	private ArrayList<Publicacao> publicacoes;
	
	/**
	 * Define um novo docente
	 * 
	 * @param codigo Codigo de identificação do docente
	 * @param nome Nome do docente
	 * @param dataNascimento Data de nacimento usando GragorianCalendar
	 * @param dataIngresso Data de ingresso usando GregorianCalendar
	 * @param coordenador Valor boleano para verificar se docente é coordenador ou não
	 * @deprecated Use {@link #Docente(long, String, String, String, boolean)} ao inves desse
	 */
	public Docente(long codigo, String nome, GregorianCalendar dataNascimento, GregorianCalendar dataIngresso, boolean coordenador) {
		this.codigo = codigo;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.dataIngresso = dataIngresso;
		this.coordenador = coordenador;
		this.publicacoes = new ArrayList<Publicacao>();
	}
	
	/**
	 * Define um novo docente
	 * 
	 * @param codigo Codigo de identificação do docente
	 * @param nome Nome do docente
	 * @param dataNascimento Data de nacimento no formato "dd/mm/aaaa"
	 * @param dataIngresso Data de ingresso no formato "dd/mm/aaaa"
	 * @param coordenador Valor boleano para verificar se docente é coordenador ou não
	 */
	public Docente(long codigo, String nome, String dataNascimento, String dataIngresso, boolean coordenador) {
		this.codigo = codigo;
		this.nome = nome;
		String[] dataNascimentoSplit = dataNascimento.split("/");
		String[] dataIngressoSplit = dataIngresso.split("/");
		this.dataNascimento = new GregorianCalendar(Integer.parseInt(dataNascimentoSplit[2]),Integer.parseInt(dataNascimentoSplit[1]),Integer.parseInt(dataNascimentoSplit[0]));
		this.dataIngresso = new GregorianCalendar(Integer.parseInt(dataIngressoSplit[2]),Integer.parseInt(dataIngressoSplit[1]),Integer.parseInt(dataIngressoSplit[0]));;
		this.coordenador = coordenador;
		this.publicacoes = new ArrayList<Publicacao>();
	}
	/**
	 * @return Codigo do docente
	 */
	public long getCodigo() {
		return codigo;
	}
	
	/**
	 * @param codigo Codigo inteiro para armazenar
	 */
	public void setCodigo(long codigo) {
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
	public GregorianCalendar getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento Data de nascimente para armazenar
	 */
	public void setDataNascimento(GregorianCalendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return Data de ingresso do docente no sistema
	 */
	public GregorianCalendar getDataIngresso() {
		return dataIngresso;
	}

	/**
	 * @param dataIngresso Data de ingresso do docente no sistema para armazenar
	 */
	public void setDataIngresso(GregorianCalendar dataIngresso) {
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
		return "Docente [codigo=" + codigo + ", nome=" + nome + ", dataNascimento=" + this.dataNascimento.get(Calendar.DATE) + "/" + this.dataNascimento.get(Calendar.MONTH) + "/" + this.dataNascimento.get(Calendar.YEAR)  + ", dataIngresso="
				+ this.dataIngresso.get(Calendar.DATE) + "/" + this.dataIngresso.get(Calendar.MONTH) + "/" + this.dataIngresso.get(Calendar.YEAR) + ", coordenador=" + coordenador + ", publicacoes=" + publicacoes + "]";
	}
	
}