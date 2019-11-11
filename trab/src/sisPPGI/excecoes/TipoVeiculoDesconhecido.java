package sisPPGI.excecoes;
/**
 * 
 * Exceção para quando o tipo de um veículo não é nem ‘C’ nem ‘P’. 
 * 
 * @author Tiago
 * @author Atilio
 * 
 */
public class TipoVeiculoDesconhecido extends Exception {

	public TipoVeiculoDesconhecido(String sigla, String tipo, Throwable cause) {
		super("Tipo de veículo desconhecido para veículo" + sigla + ": " + tipo + ".", cause);
	}

	public TipoVeiculoDesconhecido(String sigla, String tipo) {
		super("Tipo de veículo desconhecido para veículo" + sigla + ": " + tipo + ".");
	}

}
