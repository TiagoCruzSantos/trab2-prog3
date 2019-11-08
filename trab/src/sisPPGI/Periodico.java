package sisPPGI;
/**
 * Classe de representação de um veiculo periodico
 * 
 * @author Tiago
 * @author Atilio
 */
public class Periodico extends Veiculo {

	private double multiplicador;
	private String issn;
	
	public Periodico(String sigla, String nome, double impacto, String issn) {
		super(sigla, nome, impacto);
		this.issn = issn;
	}
	
	/**
	 * @return Fator de multiplicação do periodico para as publicações
	 */
	public double getMultiplicador() {
		return multiplicador;
	}

	/**
	 * @param multiplicador Fator de multiplicação do periodico para as publicações
	 */
	public void setMultiplicador(double multiplicador) {
		this.multiplicador = multiplicador;
	}

	/**
	 * @return Issn do veiculo
	 */
	public String getIssn() {
		return issn;
	}

	/**
	 * @param issn Issn do veiculo
	 */
	public void setIssn(String issn) {
		this.issn = issn;
	}

	@Override
	public String toString() {
		return "Periodico [multiplicador=" + this.multiplicador + ", issn=" + this.issn + ", sigla=" + this.sigla + ", nome=" + this.nome
				+ ", impacto=" + this.impacto + ", qualis=" + this.qualis + ", publicacoes=" + this.publicacoes + "]";
	}
}
