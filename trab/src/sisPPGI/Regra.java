package sisPPGI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Classe de representação de uma regra
 *
 * @author Tiago da Cruz Santos
 * @author Atílio Antônio Dadalto
 */
public class Regra implements Serializable {
    private GregorianCalendar dataIni;
    private GregorianCalendar dataFim;
    private int pontoMinimo;
    private int anosConsiderados;
    private double multiplicador;
    private ArrayList<Qualis> qualis;

    public Regra(GregorianCalendar dataIni, GregorianCalendar dataFim, int pontoMinimo, int anosConsiderados,
            double multiplicador, ArrayList<Qualis> qualis) {
        this.dataIni = dataIni;
        this.dataFim = dataFim;
        this.pontoMinimo = pontoMinimo;
        this.anosConsiderados = anosConsiderados;
        this.multiplicador = multiplicador;
        this.qualis = qualis;
    }

    public Regra(String dataIni, String dataFim, int pontoMinimo, int anosConsiderados, double multiplicador,
            ArrayList<Qualis> qualis) {
        String[] dataIniSplit = dataIni.split("/");
        String[] dataFimSplit = dataFim.split("/");
        this.dataIni = new GregorianCalendar(Integer.parseInt(dataIniSplit[2]),
            Integer.parseInt(dataIniSplit[1]) - 1, Integer.parseInt(dataIniSplit[0]));;
        this.dataFim = new GregorianCalendar(Integer.parseInt(dataFimSplit[2]),
            Integer.parseInt(dataFimSplit[1]) - 1, Integer.parseInt(dataFimSplit[0]));;;
        this.pontoMinimo = pontoMinimo;
        this.anosConsiderados = anosConsiderados;
        this.multiplicador = multiplicador;
        this.qualis = qualis;
    }

    /**
     * @return Data de início definida na regra
     */
    public GregorianCalendar getDataIni() {
        return dataIni;
    }

    /**
     * @param dataIni Data de início para definir na regra
     */
    public void setDataIni(GregorianCalendar dataIni) {
        this.dataIni = dataIni;
    }

    /**
     * @return Data de término definida na regra
     */
    public GregorianCalendar getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim Data de término para definir na regra
     */
    public void setDataFim(GregorianCalendar dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return Pontuação mínima que um docente deve alcançar para se manter
     *         credenciado ao programa
     */
    public double getPontoMinimo() {
        return pontoMinimo;
    }

    /**
     * @param pontoMinimo Pontuação mínima que um docente deve alcançar para se
     *                    manter credenciado ao programa
     */
    public void setPontoMinimo(int pontoMinimo) {
        this.pontoMinimo = pontoMinimo;
    }

    /**
     * @return Anos considerados ao analisar as publicações de um docente
     */
    public int getAnosConsiderados() {
        return anosConsiderados;
    }

    /**
     * @param anosConsiderados Anos considerados ao analisar as publicações de um
     *                         docente
     */
    public void setAnosConsiderados(int anosConsiderados) {
        this.anosConsiderados = anosConsiderados;
    }

    /**
     * @return Multiplicador de pontos de periódicos
     */
    public double getMultiplicador() {
        return multiplicador;
    }

    /**
     * @param multiplicador Multiplicador de pontos de periódicos
     */
    public void setMultiplicador(double multiplicador) {
        this.multiplicador = multiplicador;
    }

    /**
     * @return Lista de qualis da regra
     */
    public ArrayList<Qualis> getQualis() {
        return qualis;
    }

    /**
     * @param qualis Lista de qualis da regra
     */
    public void setQualis(ArrayList<Qualis> qualis) {
        this.qualis = qualis;
    }
}
