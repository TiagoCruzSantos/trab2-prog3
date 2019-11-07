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
		return "Periodico [multiplicador=" + multiplicador + ", issn=" + issn + ", sigla=" + sigla + ", nome=" + nome
				+ ", impacto=" + impacto + ", qualis=" + qualis + ", publicacoes=" + publicacoes + "]";
	}
}
