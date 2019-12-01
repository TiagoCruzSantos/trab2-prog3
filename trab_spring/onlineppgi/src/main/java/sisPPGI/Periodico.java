package sisPPGI;

/**
 * Classe de representação de um veículo periódico.
 *
 * @author Tiago da Cruz Santos
 * @author Atílio Antônio Dadalto
 */
public class Periodico extends Veiculo {
    private double multiplicador;
    private String issn;

    /**
     * Construtor de um veículo periódico.
     *
     * @param sigla   Sigla do veículo.
     * @param nome    Nome do veículo.
     * @param impacto Impacto em {@code double}.
     * @param issn    ISSN do periódico.
     */
    public Periodico(String sigla, String nome, double impacto, String issn) {
        super(sigla, nome, impacto);
        this.issn = issn;
    }

    /**
     * @return Fator de multiplicação do periódico para as publicações.
     */
    public double getMultiplicador() {
        return multiplicador;
    }

    /**
     * @param multiplicador Fator de multiplicação do periódico para as publicações.
     */
    public void setMultiplicador(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    /**
     * @return ISSN do veículo.
     */
    public String getIssn() {
        return issn;
    }

    /**
     * @param issn ISSN do veículo.
     */
    public void setIssn(String issn) {
        this.issn = issn;
    }

    @Override
    public String toString() {
        return "Periodico [multiplicador=" + this.multiplicador + ", issn=" + this.issn + ", sigla=" + this.sigla
                + ", nome=" + this.nome + ", impacto=" + this.impacto + ", qualis=" + this.qualis + ", publicacoes="
                + this.publicacoes + "]\n";
    }
}
