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
		super("Sigla repetida para veiculo: " + sigla);
		// TODO Auto-generated constructor stub
	}

	public SiglaVeiculoRepetido(String sigla, Throwable cause) {
		super("Sigla repetida para veiculo: " + sigla, cause);
		// TODO Auto-generated constructor stub
	}

}
