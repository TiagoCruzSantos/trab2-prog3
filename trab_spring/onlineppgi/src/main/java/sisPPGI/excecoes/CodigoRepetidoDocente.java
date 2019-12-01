package sisPPGI.excecoes;

/**
 * Exceção para quando o mesmo código for usado para dois docentes diferentes.
 *
 * @author Tiago da Cruz Santos
 * @author Atílio Antônio Dadalto
 */
public class CodigoRepetidoDocente extends Exception {
    public CodigoRepetidoDocente(long cod, Throwable cause) {
        super("Código repetido para docente: " + cod + ".", cause);
    }

    public CodigoRepetidoDocente(long cod) {
        super("Código repetido para docente: " + cod + ".");
    }
}
