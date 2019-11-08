package sisPPGI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
/**
 * Classe principal do PPGI
 * 
 * @author Tiago
 * @author Atilio
 *
 */
public class Sistema implements Serializable{
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
	/**
	 * Carrega os docentes de um arquivo para o sistema
	 * 
	 * @param infile Scanner com o arquivo de docentes aberto
	 */
	public void carregaDocentes(Scanner infile) {
		infile.nextLine();
		infile.useDelimiter(";");
		long codigo;
		String dataNas;
		String nome;
		String dataIng;
		boolean boolCoord;
		while(infile.hasNext()) {
			codigo = infile.nextLong();
			System.out.print("[" + codigo + "]");
			nome = infile.next();
			System.out.print("[" + nome + "]");
			dataNas = infile.next();
			System.out.print("[" + dataNas + "]");
			dataIng = infile.next();
			System.out.print("[" + dataIng + "]");
			if(infile.hasNext("(X\n)\\w*")) {
				boolCoord = true;
				infile.nextLine();
			}else {
				boolCoord = false;
				infile.nextLine();
			}
			System.out.println("[" + boolCoord + "]");
			this.cadastrarDocente(new Docente(codigo, nome, dataNas, dataIng, boolCoord));
		}
	}
	
	@Override
	public String toString() {
		return "Sistema [docentesCadastrados=" + docentesCadastrados + ", publicacoes=" + publicacoes + ", veiculos="
				+ veiculos + ", regras=" + regras + ", qualificacoes=" + qualificacoes + "]";
	}
}
