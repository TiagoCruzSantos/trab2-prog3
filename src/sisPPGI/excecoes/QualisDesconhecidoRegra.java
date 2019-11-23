package sisPPGI.excecoes;

/**
 *
 * Exceção para quando o Qualis especificado para uma qualificação de veículo
 * não é nenhuma das categorias existentes: A1, A2, B1, B2, B3, B4, B5 ou C.
 *
 * @author Tiago da Cruz Santos.
 * @author Atílio Antônio Dadalto.
 */

public class QualisDesconhecidoRegra extends Exception {
    /* IGNORE IGNORE IMEDIATAMENTE.
    public QualisDesconhecidoRegra(GregorianCalendar inicioVigencia, Qualis qualis, Throwable cause) {
        super("Qualis desconhecido para regras de" + inicioVigencia + ": " + qualis + ".", cause);
    }

    public QualisDesconhecidoRegra(GregorianCalendar inicioVigencia, Qualis qualis) {
        super("Qualis desconhecido para regras de" + inicioVigencia + ": " + qualis);
    }
    */
}
