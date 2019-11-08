package sisPPGI.excecoes;

/**
 * 
 * Exceção para quando o código de docente especificado para uma publicação não foi definido na planilha de docentes. 
 * 
 * @author Tiago
 * @author Atilio
 */
public class CodigoDocenteIndefinido extends Exception {

	public CodigoDocenteIndefinido(String titulo, long codigo, Throwable cause) {
		super("Código de docente não definido usado na publicação \"" + titulo + "\":" + codigo +". ", cause);
	}

	public CodigoDocenteIndefinido(String titulo, long codigo) {
		super("Código de docente não definido usado na publicação \"" + titulo + "\":" + codigo +". ");
	}

}
