package sisPPGI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import sisPPGI.excecoes.CodigoRepetidoDocente;
import sisPPGI.excecoes.SiglaVeiculoRepetido;
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
	//private ArrayList<Veiculo> veiculos;
	private HashMap<String, Veiculo> veiculos;
	private ArrayList<Regra> regras;
	private ArrayList<Qualis> qualificacoes;
	
	public Sistema() {
		this.docentesCadastrados = new HashMap<Long, Docente>();
		this.publicacoes = new ArrayList<Publicacao>();
		this.veiculos = new HashMap<String,Veiculo>();
		this.regras = new ArrayList<Regra>();
		this.qualificacoes = new ArrayList<Qualis>();
	}
	/**
	 * Cadastra um docente no sistema
	 * 
	 * @param docente Docente pre-existente
	 * @param cod Codigo do docente para mapear
	 */
	public void cadastrarDocente(Docente docente) {
		this.docentesCadastrados.put(docente.getCodigo(), docente);
	}
	/**
	 * Carrega os docentes de um arquivo para o sistema
	 * 
	 * @param infile Scanner com o arquivo de docentes aberto
	 * @throws CodigoRepetidoDocente se um codigo de um docente lido ja estiver cadastrado
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
			this.cadastrarDocente(new Docente(codigo, nome, dataNas, dataIng, boolCoord));
		}
	}
	
	/**
	 * Cadastra u, veiculo no sistema
	 * 
	 * @param veiculo Veículo pré-existente
	 */
	public void cadastrarVeiculo(Veiculo veiculo) {
		this.veiculos.put(veiculo.getSigla(),veiculo);
	}
	
	/**
	 * Carrega os veiculos a partir de um arquivo csv
	 * 
	 * @param infile Scanner com o arquivo de veiculos aberto
	 * @throws SiglaVeiculoRepetido Erro gerado quando veiculo a ser inserido ja tiver sigla existente
	 */
	public void carregaVeiculos(Scanner infile) throws SiglaVeiculoRepetido {
		infile.nextLine();
		infile.useDelimiter(";");
		String sigla;
		String nome;
		String tipo;
		String impactoStr;
		double impacto;
		while(infile.hasNext()) {
			sigla = infile.next();
			if(this.veiculos.containsKey(sigla)) {
				throw new SiglaVeiculoRepetido(sigla);
			}
			nome = infile.next();
			tipo = infile.next();
			impactoStr = infile.next();
			impacto = Double.parseDouble(impactoStr.replace(',','.'));
			String issn = null;
			if(tipo.compareTo("P") == 0) {
				issn = infile.nextLine();
				issn = (issn.split(";"))[1];
				this.cadastrarVeiculo(new Periodico(sigla, nome, impacto, issn));
			}else {
				infile.nextLine();
				this.cadastrarVeiculo(new Conferencia(sigla, nome, impacto));
			}
			//System.out.println("[" + sigla + "][" + nome + "][" + tipo + "][" + impacto + "][" + issn + ']');
		}
	}
	
	@Override
	public String toString() {
		return "Sistema [docentesCadastrados=\n" + this.docentesCadastrados + ", publicacoes=\n" + this.publicacoes + ", veiculos=\n"
				+ this.veiculos + ", regras=" + this.regras + ", qualificacoes=" + this.qualificacoes + "]";
	}
}
