package struttura;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe che modella un Settore di uno {@link Stadio}
 * 
 * @author Gaetano Antonucci
 * @author Maurizio Casciano
 *
 */
public class Settore implements Serializable {

	private static final long serialVersionUID = -9113714476267820001L;

	/**
	 * Costruisce un settore a partire dai parametri specificati
	 * 
	 * @param stadio
	 *            - lo {@link Stadio} a cui appartiene il Settore
	 * @param nomeSettore
	 *            - il nome del settore
	 * @param numeroPosti
	 *            - il numero di posti del settore
	 * @param numeroFile
	 *            - il numero di file del settore
	 * 
	 * @author Gaetano Antonucci
	 * @author Maurizio Casciano
	 */
	public Settore(Stadio stadio, String nomeSettore, int numeroPosti, int numeroFile) {
		this.stadio = stadio;
		this.nomeSettore = nomeSettore;
		this.numeroPosti = numeroPosti;
		this.numeroFile = numeroFile;
		this.posti = new ArrayList<>();
	}

	/**
	 * Aggiunge il {@link Posto} passato in input alla lista dei Posti.
	 * 
	 * @param p
	 *            - Il {@link Posto} da aggiungere alla lista.
	 * @author Maurizio Casciano
	 */
	public void addPosto(Posto p) {
		this.posti.add(p);
	}

	/**
	 * Restituisce la lista dei posti del settore
	 * 
	 * @return ArrayList dei posti del Settore
	 */
	public ArrayList<Posto> getPosti() {
		return this.posti;
	}

	/**
	 * Restituisce il posto che ha per numero index + 1.
	 * 
	 * @param index
	 *            L'indice del posto nell'ArrayLyst dei Posti.
	 * @return Il Posto in posizione index.
	 * @author Maurizio Casciano
	 */
	public Posto getPosto(int index) {
		return this.posti.get(index);
	}

	/**
	 * Restituisce lo {@link Stadio} cui il settore appartiene
	 * 
	 * @return lo stadio
	 * @author Gaetano Antonucci
	 */
	public Stadio getStadio() {
		return stadio;
	}

	/**
	 * Restituisce il nome assegnato al Settore
	 * 
	 * @return nomeSettore
	 * @author Gaetano Antonucci
	 */
	public String getNomeSettore() {
		return nomeSettore;
	}

	/**
	 * Restituisce il numero dei posti del Settore
	 * 
	 * @return numeroPosti
	 * @author Gaetano Antonucci
	 */
	public int getNumeroPosti() {
		return numeroPosti;
	}

	/**
	 * Restituisce il numero di file del Settore
	 * 
	 * @return numeroFile
	 * @author Gaetano Antonucci
	 */
	public int getNumeroFile() {
		return numeroFile;
	}

	/**
	 * Restituisce le informazioni del Settore come {@link String}
	 * 
	 * @author Maurizio Casciano
	 */

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [stadio=" + stadio + ", nomeSettore=" + nomeSettore
				+ ", numeroPosti=" + numeroPosti + ", numeroFile=" + numeroFile + ", posti=" + posti + "]";
	}

	/**
	 * Verifica se l'oggetto corrente e' uguale all'oggetto passato come
	 * parametro
	 * 
	 * @param obj
	 *            - l'oggetto su cui effettuare la verifica
	 * @return {@code true} se quest'oggetto e' uguale all'oggetto passato come
	 *         parametro, {@code false} altrimenti
	 * @author Maurizio Casciano
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Settore other = (Settore) obj;

		if (this.stadio.equals(other.stadio) && this.nomeSettore.equals(other.nomeSettore)
				&& this.numeroFile == other.numeroFile && this.numeroPosti == other.numeroPosti) {
			return true;
		}

		return false;
	}

	private Stadio stadio;
	private String nomeSettore;
	private int numeroPosti;
	private int numeroFile;
	private ArrayList<Posto> posti;
}
