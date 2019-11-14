package sisPPGI.excecoes;

/**
 * Excecao para quando a mesma sigla for usado para dois veículos diferentes.
 *
 * @author Tiago da Cruz Santos
 * @author Atílio Antônio Dadalto
 */
public class SiglaVeiculoRepetido extends Exception {
    public SiglaVeiculoRepetido(String sigla) {
        super("Sigla repetida para veiculo: " + sigla + ".");
    }

    public SiglaVeiculoRepetido(String sigla, Throwable cause) {
        super("Sigla repetida para veiculo: " + sigla + ".", cause);
    }

}
