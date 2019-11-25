package sisPPGI.excecoes;

/**
 *
 * Exceção para quando o Qualis é desconhecido para a qualificação de um veículo.
 *
 * @author Tiago da Cruz Santos.
 * @author Atílio Antônio Dadalto.
 */
public class QualisDesconhecidoVeiculo extends Exception {
    public QualisDesconhecidoVeiculo(String sigla, int ano, String qualis, Throwable cause) {
        super("Qualis desconhecido para qualificação do veículo " + sigla + " no ano " + ano + ": " + qualis + ".", cause);
    }

    public QualisDesconhecidoVeiculo(String sigla, int ano, String qualis) {
        super("Qualis desconhecido para qualificação do veículo " + sigla + " no ano " + ano + ": " + qualis + ".");
    }
}
