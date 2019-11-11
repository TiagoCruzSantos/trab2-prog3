package sisPPGI.excecoes;
/**
 * 
 * Exceção para quando a sigla de veículo especificada para uma publicação não foi definida na planilha de veículos. 
 * 
 * @author Tiago
 * @author Atilio
 *
 */
public class SiglaIndefinida extends Exception {

	public SiglaIndefinida(String titulo, String sigla, Throwable cause) {
		super("Sigla de veículo não definida usada na publicação \"" + titulo + "\":" + sigla +".", cause);
	}
	
	public SiglaIndefinida(String titulo, String sigla) {
		super("Sigla de veículo não definida usada na publicação \"" + titulo + "\":" + sigla +".");
	}
}
