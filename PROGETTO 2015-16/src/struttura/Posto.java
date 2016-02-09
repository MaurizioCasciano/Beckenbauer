package struttura;

import java.io.Serializable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Classe che modella un posto in uno {@link Stadio}
 * 
 * @author Maurizio Casciano
 * @author Gaetano Antonucci
 */
public class Posto implements Serializable {

	/**
	 * Crea un nuovo {@link Posto} impostando lo stato a
	 * {@link SeatStatus#LIBERO}.
	 * 
	 * @param stadio
	 *            Lo stadio a cui appartiene questo Posto.
	 * @param settore
	 *            Il settore a cui appartiene questo Posto.
	 * @param numeroFila
	 *            Il numero della fila in cui e' situato questo Posto.
	 * @param numeroPosto
	 *            Il numero di questo Posto.
	 * @author Maurizio Casciano
	 * @author Gaetano Antonucci
	 */
	public Posto(Stadio stadio, Settore settore, int numeroFila, int numeroPosto) {
		this(stadio, settore, numeroFila, numeroPosto, SeatStatus.LIBERO);
	}

	/**
	 * Crea un nuovo {@link Posto}.
	 * 
	 * @param stadio
	 *            Lo stadio a cui appartiene questo Posto.
	 * @param settore
	 *            Il settore a cui appartiene questo Posto.
	 * @param numeroFila
	 *            Il numero della fila in cui e' situato questo Posto.
	 * @param numeroPosto
	 *            Il numero di questo Posto.
	 * @param stato
	 *            Lo stato di questo Posto.
	 * @author Maurizio Casciano
	 * @author Gaetano Antonucci
	 */
	public Posto(Stadio stadio, Settore settore, int numeroFila, int numeroPosto, SeatStatus stato) {
		this.stadio = stadio;
		this.settore = settore;
		this.numeroFila = numeroFila;
		this.numeroPosto = numeroPosto;
		this.stato = stato;
	}

	/**
	 * Aggiunge un listener alla lista degli ascoltatori per il cambiamento
	 * dello stato del posto.
	 * 
	 * @param listener
	 *            il listener a cui notificare il cambiamento dello stato del
	 *            posto.
	 * @author Maurizio
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.PROPERTY_CHANGE_SUPPORT.addPropertyChangeListener(listener);
	}

	/**
	 * Rimuove il listener dalla lista degli ascoltatori per il cambiamento di
	 * stato del posto. Se il listener e' null oppure non e' mai stato aggiunto,
	 * non viene lanciata nessuna eccezione e non viene eseguita nessuna azione.
	 * 
	 * @param listener
	 *            il listener da rimuovere.
	 * @author Maurizio
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.PROPERTY_CHANGE_SUPPORT.removePropertyChangeListener(listener);
	}

	/**
	 * Restituisce il numero di fila del posto
	 * 
	 * @return il numero di fila
	 */
	public int getNumeroFila() {
		return this.numeroFila;
	}

	/**
	 * Restituisce il numero del posto nella fila
	 * 
	 * @return il numero del posto
	 */
	public int getNumeroPosto() {
		return this.numeroPosto;
	}

	/**
	 * Restituisce lo stato ({@link SeatStatus}) del posto
	 * 
	 * @return lo stato del posto
	 * @author Maurizio Casciano
	 */
	public SeatStatus getStato() {
		return this.stato;
	}

	/**
	 * Imposta lo stato del posto.
	 * 
	 * @param newSeatStatus
	 *            il nuovo stato del posto.
	 * @author Maurizio
	 */
	public void setStato(SeatStatus newSeatStatus) {
		SeatStatus oldStatus = this.stato;
		this.stato = newSeatStatus;
		this.PROPERTY_CHANGE_SUPPORT.firePropertyChange(STATUS_PROPERTY_CHANGED, oldStatus, newSeatStatus);
	}

	/**
	 * Restituisce lo {@link Stadio} del posto
	 * 
	 * @return lo stadio
	 * @author Gaetano Antonucci
	 */

	public Stadio getStadio() {
		return this.stadio;
	}

	/**
	 * Restituisce il {@link Settore} dello {@link Stadio} in cui e' situato il
	 * posto.
	 * 
	 * @return il settore il settore in cui e' situato il posto.
	 * @author Gaetano Antonucci
	 */
	public Settore getSettore() {
		return this.settore;
	}

	/**
	 * Restituisce le informazioni del posto
	 * 
	 * @author Maurizio Casciano
	 */
	@Override
	public String toString() {
		return this.getClass().getName() + " [stadio=" + stadio.getNome() + ", settore=" + settore.getNomeSettore() + ", fila="
				+ numeroFila + ", posto=" + numeroPosto + ", stato=" + stato + "]";
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

		Posto other = (Posto) obj;

		if (this.stadio.equals(other.stadio) && this.settore.equals(other.settore)
				&& this.numeroFila == other.numeroFila && this.numeroPosto == other.numeroPosto) {
			return true;
		}

		return false;
	}

	private static final long serialVersionUID = 7526566854555308360L;
	private Stadio stadio;
	private Settore settore;
	private int numeroFila;
	private int numeroPosto;
	private SeatStatus stato;
	private String STATUS_PROPERTY_CHANGED = "stato";
	private final PropertyChangeSupport PROPERTY_CHANGE_SUPPORT = new PropertyChangeSupport(this);;
}
