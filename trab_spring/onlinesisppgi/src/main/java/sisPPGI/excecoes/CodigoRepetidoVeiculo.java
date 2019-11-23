package sisPPGI.excecoes;

/**
 * Exceção para quando o mesmo código for usado para dois veículos diferentes.
 *
 * @author Tiago da Cruz Santos.
 * @author Atílio Antônio Dadalto.
 */
public class CodigoRepetidoVeiculo extends Exception {
    public CodigoRepetidoVeiculo(long cod, Throwable cause) {
        super("Código repetido para veículo: " + cod + ".", cause);
    }

    public CodigoRepetidoVeiculo(long cod) {
        super("Código repetido para veículo: " + cod + ".");
    }
}
