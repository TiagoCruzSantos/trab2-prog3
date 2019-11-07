package meupacote;

import java.util.ArrayList;
import java.io.*;
/**
 * Classe principal do PPGI
 * 
 * @author Tiago
 * @author Atilio
 *
 */
public class Sistema {
	private ArrayList<Docente> docentesCadastrados;
	private ArrayList<Publicacao> publicacoes;
	private ArrayList<Veiculo> veiculos;
	private ArrayList<Regra> regras;
	private ArrayList<Qualis> qualificacoes;
	
	public Sistema() {
		this.docentesCadastrados = new ArrayList<Docente>();
		this.publicacoes = new ArrayList<Publicacao>();
		this.veiculos = new ArrayList<Veiculo>();
		this.regras = new ArrayList<Regra>();
		this.qualificacoes = new ArrayList<Qualis>();
	}
	/**
	 * Cadastra um docente no sistema
	 * 
	 * @param docente Docente pre-existente
	 */
	public void cadastrarDocente(Docente docente) {
		this.docentesCadastrados.add(docente);
	}
	@Override
	public String toString() {
		return "Sistema [docentesCadastrados=" + docentesCadastrados + ", publicacoes=" + publicacoes + ", veiculos="
				+ veiculos + ", regras=" + regras + ", qualificacoes=" + qualificacoes + "]";
	}
}
