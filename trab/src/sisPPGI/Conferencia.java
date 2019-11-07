package sisPPGI;

/**
 * Classe de representação de uma conferência
 * 
 * @author Tiago
 * @author Atilio
 */
public class Conferencia extends Veiculo {

	public Conferencia(String sigla, String nome, double impacto) {
		super(sigla, nome, impacto);
	}

	@Override
	public String toString() {
		return "Conferencia [sigla=" + sigla + ", nome=" + nome + ", impacto=" + impacto + ", qualis=" + qualis
				+ ", publicacoes=" + publicacoes + "]";
	}
	
}
