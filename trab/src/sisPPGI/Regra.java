package sisPPGI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/**
 * Classe de representação de uma regra
 * 
 * @author Tiago
 * @author Atilio
 */
public class Regra implements Serializable{
	private Date dataIni;
	private Date dataFim;
	private double pontoMinimo;
	private int anosConsiderados;
	private double multiplicador;
	private ArrayList<Qualis> qualis;
	
	public Regra(Date dataIni, Date dataFim, double pontoMinimo, int anosConsiderados, double multiplicador,
			ArrayList<Qualis> qualis) {
		this.dataIni = dataIni;
		this.dataFim = dataFim;
		this.pontoMinimo = pontoMinimo;
		this.anosConsiderados = anosConsiderados;
		this.multiplicador = multiplicador;
		this.qualis = qualis;
	}
	/**
	 * @return Data de inicio definida na regra
	 */
	public Date getDataIni() {
		return dataIni;
	}
	/**
	 * @param dataIni Data de inicio para definir na regra
	 */
	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}
	/**
	 * @return Data de termino definida na regra
	 */
	public Date getDataFim() {
		return dataFim;
	}
	/**
	 * @param dataFim Data de termino para definir na regra
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	/**
	 * @return Pontuação mínima que um docente deve alcançar para se manter credenciado ao programa
	 */
	public double getPontoMinimo() {
		return pontoMinimo;
	}
	/**
	 * @param pontoMinimo Pontuação mínima que um docente deve alcançar para se manter credenciado ao programa
	 */
	public void setPontoMinimo(double pontoMinimo) {
		this.pontoMinimo = pontoMinimo;
	}
	/**
	 * @return Anos considerados ao analisar as publicações de um docente
	 */
	public int getAnosConsiderados() {
		return anosConsiderados;
	}
	/**
	 * @param anosConsiderados Anos considerados ao analisar as publicações de um docente
	 */
	public void setAnosConsiderados(int anosConsiderados) {
		this.anosConsiderados = anosConsiderados;
	}
	/**
	 * @return Multiplicador
	 */
	public double getMultiplicador() {
		return multiplicador;
	}
	/**
	 * @param multiplicador Multiplicador
	 */
	public void setMultiplicador(double multiplicador) {
		this.multiplicador = multiplicador;
	}
	/**
	 * @return Lista de Qualis da regra
	 */
	public ArrayList<Qualis> getQualis() {
		return qualis;
	}
	/**
	 * @param qualis Lista de Qualis da regra
	 */
	public void setQualis(ArrayList<Qualis> qualis) {
		this.qualis = qualis;
	}
}
