package sisPPGI.excecoes;

/**
 *
 * Exceção para quando o tipo de um veículo não é nem ‘C’ nem ‘P’.
 *
 * @author Tiago da Cruz Santos.
 * @author Atílio Antônio Dadalto.
 */
public class QualisDesconhecidoVeiculo extends Exception {
    /* IGNORE IGNORE IMEDIATAMENTE.
    public QualisDesconhecidoVeiculo(String sigla, int ano, Qualis qualis, Throwable cause) {
        super("Qualis desconhecido para qualificação do veículo" + sigla + " no ano " + ano + ": " + qualis + ".", cause);
    }

    public QualisDesconhecidoVeiculo(String sigla, int ano, Qualis qualis) {
        super("Qualis desconhecido para qualificação do veículo" + sigla + " no ano " + ano + ": " + qualis + ".");
    }
    */
}
