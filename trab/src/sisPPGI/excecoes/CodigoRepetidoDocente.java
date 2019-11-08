package sisPPGI.excecoes;

/**
 * Excecao para quando o mesmo código for usado para dois docentes diferentes. 
 * 
 * @author Tiago
 * @author Atilio
 *
 */
public class CodigoRepetidoDocente extends Exception {

	public CodigoRepetidoDocente(long cod, Throwable cause) {
		super("Código repetido para docente: " + cod, cause);
	}
	
	public CodigoRepetidoDocente(long cod) {
		super("Código repetido para docente: " + cod);
	}
}
