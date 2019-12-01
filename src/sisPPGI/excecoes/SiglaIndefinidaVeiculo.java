package sisPPGI.excecoes;

/**
 * Exceção para quando a sigla de um veículo especificada para uma qualificação
 * não foi definida na planilha de veículos.
 *
 * @author Tiago da Cruz Santos
 * @author Atílio Antônio Dadalto
 */
public class SiglaIndefinidaVeiculo extends Exception {
    public SiglaIndefinidaVeiculo(int ano, String sigla, Throwable cause) {
        super("Sigla de veículo não definida usada na qualfiicação do ano " + ano + ".", cause);
    }

    public SiglaIndefinidaVeiculo(int ano, String sigla) {
        super("Sigla de veículo não definida usada na qualfiicação do ano " + ano + ": " + sigla);
    }
}
