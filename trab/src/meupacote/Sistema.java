package meupacote;

import java.util.ArrayList;

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
}
