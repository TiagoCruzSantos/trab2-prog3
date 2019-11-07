package sisPPGI;

import java.util.ArrayList;

/**
 * Classe de representação de Publicação
 * 
 * @author Tiago
 * @author Atilio
 */
public class Publicacao {
	private int ano;
	private String titulo;
	private int numero;
	private String local;
	private int paginaIni;
	private int paginaFim;
	private ArrayList<Docente> autores;
	
	public Publicacao(int ano, String titulo, int numero, String local, int paginaIni, int paginaFim,
			ArrayList<Docente> autores) {
		this.ano = ano;
		this.titulo = titulo;
		this.numero = numero;
		this.local = local;
		this.paginaIni = paginaIni;
		this.paginaFim = paginaFim;
		this.autores = autores;
	}

	/**
	 * @return Ano de publicação
	 */
	public int getAno() {
		return ano;
	}
	
	/**
	 * @param ano Ano de publicação
	 */
	public void setAno(int ano) {
		this.ano = ano;
	}

	/**
	 * @return Titulo da publicação
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo Titulo da publicação
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return Número da publicação
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero Numero da publicação
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return Local de publicação
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * @param local Local de publicação
	 */
	public void setLocal(String local) {
		this.local = local;
	}

	/**
	 * @return Pagina inicial da publicação
	 */
	public int getPaginaIni() {
		return paginaIni;
	}

	/**
	 * @param paginaIni Pagina inicial da publicação
	 */
	public void setPaginaIni(int paginaIni) {
		this.paginaIni = paginaIni;
	}

	/**
	 * @return Ultima pagina da publicação
	 */
	public int getPaginaFim() {
		return paginaFim;
	}

	/**
	 * @param paginaFim Ultima pagina da publicação
	 */
	public void setPaginaFim(int paginaFim) {
		this.paginaFim = paginaFim;
	}

	/**
	 * @return Lista de autores
	 */
	public ArrayList<Docente> getAutores() {
		return autores;
	}

	/**
	 * @param autores Lista de autores
	 */
	public void setAutores(ArrayList<Docente> autores) {
		this.autores = autores;
	}

	@Override
	public String toString() {
		return "Publicacao [ano=" + ano + ", titulo=" + titulo + ", numero=" + numero + ", local=" + local
				+ ", paginaIni=" + paginaIni + ", paginaFim=" + paginaFim + ", autores=" + autores + "]";
	}
	
}
