package struttura;

import java.io.Serializable;

/**
 * 
 * 
 * @author Maurizio & Gaetano
 */
public class Posto implements Serializable {

	private static final long serialVersionUID = 7526566854555308360L;

	/**
	 * Crea un nuovo {@link Posto} impostando lo stato a
	 * {@link SeatStatus#LIBERO}.
	 * 
	 * @param stadio
	 *            Lo stadio a cui appartiene questo Posto.
	 * @param settore
	 *            Il settore a cui appartiene questo Posto.
	 * @param numeroFila
	 *            Il numero della fila in cui è situato questo Posto.
	 * @param numeroPosto
	 *            Il numero di questo Posto.
	 * @author Maurizio
	 * @author Gaetano
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
	 *            Il numero della fila in cui è situato questo Posto.
	 * @param numeroPosto
	 *            Il numero di questo Posto.
	 * @param stato
	 *            Lo stato di questo Posto.
	 * @author Maurizio
	 * @author Gaetano
	 */
	public Posto(Stadio stadio, Settore settore, int numeroFila, int numeroPosto, SeatStatus stato) {
		this.stadio = stadio;
		this.settore = settore;
		this.numeroFila = numeroFila;
		this.numeroPosto = numeroPosto;
		this.stato = stato;
	}

	public int getNumeroFila() {
		return numeroFila;
	}

	public int getNumeroPosto() {
		return numeroPosto;
	}

	public SeatStatus getStato() {
		return this.stato;
	}

	public void setStato(SeatStatus seatStatus) {
		this.stato = seatStatus;
	}

	/**
	 * @return the prenotato
	 */
	public boolean isPrenotato() {
		return this.stato == SeatStatus.PRENOTATO;
	}

	/**
	 * @param prenotato
	 *            the prenotato to set
	 */
	public void setPrenotato(boolean prenotato) {
		this.stato = SeatStatus.PRENOTATO;
	}

	/**
	 * @return the venduto
	 */
	public boolean isVenduto() {
		return this.stato == SeatStatus.VENDUTO;
	}

	/**
	 * @param venduto
	 *            the venduto to set
	 */
	public void setVenduto(boolean venduto) {
		this.stato = SeatStatus.VENDUTO;
	}

	/**
	 * @return the stadio
	 */
	public Stadio getStadio() {
		return stadio;
	}

	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @return the fila
	 */
	public int getFila() {
		return this.numeroFila;
	}

	/**
	 * @return the posto
	 */
	public int getPosto() {
		return this.numeroPosto;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [stadio=" + stadio + ", settore=" + settore + ", fila=" + numeroFila
				+ ", posto=" + numeroPosto + ", stato=" + stato + "]";
	}

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

	private Stadio stadio;
	private Settore settore;
	private int numeroFila;
	private int numeroPosto;

	private SeatStatus stato;
}
