package sisPPGI;

/**
 * Classe de representação de uma conferência.
 *
 * @author Tiago da Cruz Santos,.
 * @author Atílio Antônio Dadalto.
 */
public class Conferencia extends Veiculo {

    /**
     * Construtor de uma conferência.
     *
     * @param sigla   Sigla do veículo.
     * @param nome    Nome do veículo.
     * @param impacto Impacto em {@code double}.
     */
    public Conferencia(String sigla, String nome, double impacto) {
        super(sigla, nome, impacto);
    }

    @Override
    public String toString() {
        return "Conferencia [sigla=" + this.sigla + ", nome=" + this.nome + ", impacto=" + this.impacto + ", qualis="
                + this.qualis + ", publicacoes=" + this.publicacoes + "]\n";
    }

}
