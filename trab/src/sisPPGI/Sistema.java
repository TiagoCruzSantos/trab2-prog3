package sisPPGI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import sisPPGI.excecoes.CodigoRepetidoDocente;
/**
 * Classe principal do PPGI
 * 
 * @author Tiago
 * @author Atilio
 *
 */
public class Sistema implements Serializable{
	//private ArrayList<Docente> docentesCadastrados;
	private HashMap<Long, Docente> docentesCadastrados;
	private ArrayList<Publicacao> publicacoes;
	private ArrayList<Veiculo> veiculos;
	private ArrayList<Regra> regras;
	private ArrayList<Qualis> qualificacoes;
	
	public Sistema() {
		this.docentesCadastrados = new HashMap<Long, Docente>();
		this.publicacoes = new ArrayList<Publicacao>();
		this.veiculos = new ArrayList<Veiculo>();
		this.regras = new ArrayList<Regra>();
		this.qualificacoes = new ArrayList<Qualis>();
	}
	/**
	 * Cadastra um docente no sistema
	 * 
	 * @param docente Docente pre-existente
	 * @param cod Codigo do docente para mapear
	 */
	public void cadastrarDocente(Long cod, Docente docente) {
		this.docentesCadastrados.put(cod, docente);
	}
	/**
	 * Carrega os docentes de um arquivo para o sistema
	 * 
	 * @param infile Scanner com o arquivo de docentes aberto
	 * @throws CodigoRepetidoDocente 
	 */
	public void carregaDocentes(Scanner infile) throws CodigoRepetidoDocente {
		infile.nextLine();
		infile.useDelimiter(";");
		long codigo;
		String dataNas;
		String nome;
		String dataIng;
		boolean boolCoord;
		while(infile.hasNext()) {
			codigo = infile.nextLong();
			//System.out.print("[" + codigo + "]");
			if(this.docentesCadastrados.containsKey(codigo)) {
				throw new CodigoRepetidoDocente(codigo);
			}
			nome = infile.next();
			//System.out.print("[" + nome + "]");
			dataNas = infile.next();
			//System.out.print("[" + dataNas + "]");
			dataIng = infile.next();
			//System.out.print("[" + dataIng + "]");
			if(infile.hasNext("(X\n)\\w*")) {
				boolCoord = true;
				infile.nextLine();
			}else {
				boolCoord = false;
				infile.nextLine();
			}
			//System.out.println("[" + boolCoord + "]");
			this.cadastrarDocente(codigo, new Docente(codigo, nome, dataNas, dataIng, boolCoord));
		}
	}
	
	@Override
	public String toString() {
		return "Sistema [docentesCadastrados=" + this.docentesCadastrados + ", publicacoes=" + this.publicacoes + ", veiculos="
				+ veiculos + ", regras=" + this.regras + ", qualificacoes=" + this.qualificacoes + "]";
	}
}
