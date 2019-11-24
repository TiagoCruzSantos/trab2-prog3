package sisPPGI.excecoes;

/**
 * Exceção para quando a mesma sigla for usado para dois veículos diferentes.
 *
 * @author Tiago da Cruz Santos.
 * @author Atílio Antônio Dadalto.
 */
public class SiglaRepetidaVeiculo extends Exception {
    public SiglaRepetidaVeiculo(String sigla) {
        super("Sigla repetida para veículo: " + sigla + ".");
    }

    public SiglaRepetidaVeiculo(String sigla, Throwable cause) {
        super("Sigla repetida para veículo: " + sigla + ".", cause);
    }
}
