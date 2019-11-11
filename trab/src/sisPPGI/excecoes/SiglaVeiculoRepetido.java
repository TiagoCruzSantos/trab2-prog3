package sisPPGI.excecoes;
/**
 * Excecao para quando a mesma sigla for usado para dois veiculos diferentes. 
 * 
 * @author Tiago
 * @author Atílio
 *
 */
public class SiglaVeiculoRepetido extends Exception {


	public SiglaVeiculoRepetido(String sigla) {
		super("Sigla repetida para veiculo: " + sigla + ".");
	}

	public SiglaVeiculoRepetido(String sigla, Throwable cause) {
		super("Sigla repetida para veiculo: " + sigla +".", cause);
	}

}
