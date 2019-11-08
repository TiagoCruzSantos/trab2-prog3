package sisPPGI.excecoes;

/**
 * Excecao para quando o mesmo código for usado para dois veiculos diferentes. 
 * 
 * @author Tiago
 * @author Atilio
 *
 */
public class CodigoRepetidoVeiculo extends Exception {
	public CodigoRepetidoVeiculo(long cod, Throwable cause) {
		super("Código repetido para veiculo: " + cod, cause);
	}
	
	public CodigoRepetidoVeiculo(long cod) {
		super("Código repetido para veiculo: " + cod);
	}
}
