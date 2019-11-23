package sisPPGI;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe de representação de Publicação.
 *
 * @author Tiago da Cruz Santos.
 * @author Atílio Antônio Dadalto.
 */
public class Publicacao implements Serializable, Comparable<Publicacao> {
    private int ano;
    private String titulo;
    private int numero;
    private Veiculo veiculo;
    private String local;
    private int paginaIni;
    private int paginaFim;
    private int volume;
    private HashMap<Long, Docente> autores;

    /**
     * Cria uma nova publicação.
     *
     * @param ano       Ano de publicação.
     * @param titulo    Título da publicação.
     * @param numero    Número da publicação.
     * @param volume    Volume da publicação.
     * @param local     Local da publicação.
     * @param paginaIni Página inicial.
     * @param paginaFim Página Final.
     * @param autores   Autores da publicação.
     * @param veiculo   Veiculo em que a publicação foi publicada.
     */
    public Publicacao(int ano, String titulo, int numero, int volume, String local, int paginaIni, int paginaFim,
            HashMap<Long, Docente> autores, Veiculo veiculo) {
        this.ano = ano;
        this.titulo = titulo;
        this.numero = numero;
        this.local = local;
        this.paginaIni = paginaIni;
        this.paginaFim = paginaFim;
        this.autores = autores;
        this.volume = volume;
        this.veiculo = veiculo;
    }

    /**
     * @return Ano de publicação.
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano Ano de publicação.
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * @return Título da publicação.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo Título da publicação.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return Número da publicação.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero Número da publicação.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return Local de publicação.
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local Local de publicação.
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * @return Página inicial da publicação.
     */
    public int getPaginaIni() {
        return paginaIni;
    }

    /**
     * @param paginaIni Página inicial da publicação.
     */
    public void setPaginaIni(int paginaIni) {
        this.paginaIni = paginaIni;
    }

    /**
     * @return Última página da publicação.
     */
    public int getPaginaFim() {
        return paginaFim;
    }

    /**
     * @param paginaFim Última página da publicação.
     */
    public void setPaginaFim(int paginaFim) {
        this.paginaFim = paginaFim;
    }

    /**
     * @return Lista de autores.
     */
    public HashMap<Long, Docente> getAutores() {
        return autores;
    }

    /**
     * @param autores Lista de autores.
     */
    public void setAutores(HashMap<Long, Docente> autores) {
        this.autores = autores;
    }

    /**
     * @return Volume da publicação.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * @param volume Volume da publicação.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * @return O veículo.
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo O veículo.
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public int compareTo(Publicacao pub) {
        // Ordena por nível Qualis do veículo primeiramente
        if (this.veiculo.getQualisAno(this.ano).getNivel()
                .compareTo(pub.veiculo.getQualisAno(pub.getAno()).getNivel()) < 0) {
            return -1;
        } else if (this.veiculo.getQualisAno(this.ano).getNivel()
        .compareTo(pub.veiculo.getQualisAno(pub.getAno()).getNivel()) == 0) {
            // Em caso de empate, ordena por ano de publicação
            if (this.ano > pub.getAno()) {
                return -1;
            } else if (this.ano == pub.getAno()) {
                // Em caso de empate, ordena por sigla do veículo
                if (this.veiculo.getSigla().compareTo(pub.getVeiculo().getSigla()) < 0) {
                    return -1;
                } else if (this.veiculo.getSigla().compareTo(pub.getVeiculo().getSigla()) == 0) {
                    // EM caso de empate, ordena por ordem alfabética de título
                    return this.titulo.compareTo(pub.getTitulo());
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Publicacao [ano=" + this.ano + ", titulo=" + this.titulo + ", numero=" + this.numero + ", local="
                + this.local + ", paginaIni=" + this.paginaIni + ", paginaFim=" + this.paginaFim + ", autores="
                + this.autores + "]\n";
    }
}
