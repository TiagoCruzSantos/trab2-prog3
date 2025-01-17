package sisPPGI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe de representação de docente.
 *
 * @author Tiago da Cruz Santos
 * @author Atílio Antônio Dadalto
 */
public class Docente implements Serializable, Comparable<Docente> {

    private long codigo;
    private String nome;
    private GregorianCalendar dataNascimento;
    private GregorianCalendar dataIngresso;
    private boolean coordenador;
    private ArrayList<Publicacao> publicacoes;

    /**
     * Define um novo docente.
     *
     * @param codigo         Código de identificação do docente.
     * @param nome           Nome do docente.
     * @param dataNascimento Data de nascimento usando GragorianCalendar.
     * @param dataIngresso   Data de ingresso usando GregorianCalendar.
     * @param coordenador    Valor booleano para verificar se docente é coordenador
     *                       ou não.
     * @deprecated Use {@link #Docente(long, String, String, String, boolean)} em
     *             vez desse.
     */
    public Docente(long codigo, String nome, GregorianCalendar dataNascimento, GregorianCalendar dataIngresso,
            boolean coordenador) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataIngresso = dataIngresso;
        this.coordenador = coordenador;
        this.publicacoes = new ArrayList<Publicacao>();
    }

    /**
     * Define um novo docente.
     *
     * @param codigo         Código de identificação do docente.
     * @param nome           Nome do docente.
     * @param dataNascimento Data de nascimento no formato "dd/mm/aaaa".
     * @param dataIngresso   Data de ingresso no formato "dd/mm/aaaa".
     * @param coordenador    Valor booleano para verificar se docente é coordenador
     *                       ou não.
     */
    public Docente(long codigo, String nome, String dataNascimento, String dataIngresso, boolean coordenador) {
        this.codigo = codigo;
        this.nome = nome;

        String[] dataNascimentoSplit = dataNascimento.split("/");
        String[] dataIngressoSplit = dataIngresso.split("/");
        this.dataNascimento = new GregorianCalendar(Integer.parseInt(dataNascimentoSplit[2]),
                Integer.parseInt(dataNascimentoSplit[1]) - 1, Integer.parseInt(dataNascimentoSplit[0]));
        this.dataIngresso = new GregorianCalendar(Integer.parseInt(dataIngressoSplit[2]),
                Integer.parseInt(dataIngressoSplit[1]) - 1, Integer.parseInt(dataIngressoSplit[0]));
        this.coordenador = coordenador;
        this.publicacoes = new ArrayList<Publicacao>();
    }

    /**
     * @return Código do docente.
     */
    public long getCodigo() {
        return codigo;
    }

    /**
     * @param codigo Código inteiro.
     */
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    /**
     * @return Nome do docente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Nome do docente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Data de nascimento do docente.
     */
    public GregorianCalendar getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento Data de nascimento.
     */
    public void setDataNascimento(GregorianCalendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return Data de ingresso do docente.
     */
    public GregorianCalendar getDataIngresso() {
        return dataIngresso;
    }

    /**
     * @param dataIngresso Data de ingresso do docente.
     */
    public void setDataIngresso(GregorianCalendar dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    /**
     * @return Verdadeiro se docente for coordenador ou falso se não for.
     */
    public boolean isCoordenador() {
        return coordenador;
    }

    /**
     * @param coordenador Valor booleano que determina se docente é coordenador ou
     *                    não.
     */
    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
    }

    /**
     * @return Lista de publicações do docente.
     */
    public ArrayList<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    /**
     * @param publicacoes Lista de publicações do docente.
     */
    public void setPublicacoes(ArrayList<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    /**
     * @param pub Uma publicação.
     */
    public void adicionarPublicacao(Publicacao pub) {
        this.publicacoes.add(pub);
    }

    /**
     * Calcula a pontuação do docente
     *
     * @param ano Ano da regra
     * @param range Quantidade de anos anteriaores aceitos
     * @return Pontuação do docente
     */
    public double calculaPontuacao(int ano, int range) {
        double pontuacao = 0;
        for (Publicacao itPub : this.publicacoes) {
            if ((itPub.getAno() < ano && itPub.getAno() >= ano - range)) {
                // System.out.println(itPub.getAno());
                Veiculo veicPub = itPub.getVeiculo();
                if(veicPub.getQualisAno(itPub.getAno()) != null){
                    if (veicPub instanceof Periodico) {
                        pontuacao += veicPub.getQualisAno(itPub.getAno()).getPontuacao() * ((Periodico) veicPub).getMultiplicador();
                    } else {
                        pontuacao += veicPub.getQualisAno(itPub.getAno()).getPontuacao();
                    }
                }
            }
        }
        return pontuacao;
    }

    public int getAnoNascimento() {
        return this.dataNascimento.get(Calendar.YEAR);
    }

    public int getIdade(int ano) {
        return ano - this.getAnoNascimento();
    }

    public int getAnoIngresso() {
        return this.dataIngresso.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        return "Docente [codigo=" + this.codigo + ", nome=" + this.nome + ", dataNascimento="
                + this.dataNascimento.getTime() + ", dataIngresso=" + this.dataIngresso.getTime() + ", coordenador="
                + this.coordenador + ", publicacoes=" + this.publicacoes + "]\n";
    }

    @Override
    public int compareTo(Docente o) {
        return this.nome.compareTo(o.getNome());
    }

}
