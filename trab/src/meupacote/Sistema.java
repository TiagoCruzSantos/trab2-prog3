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
	}
}
