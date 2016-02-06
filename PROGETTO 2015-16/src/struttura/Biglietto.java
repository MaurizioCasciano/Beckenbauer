package struttura;

import java.io.Serializable;
import user.Cliente;

/**
 * Classe che modella un biglietto di una partita di calcio.
 * 
 * @author Gaetano Antonucci
 *
 */
public class Biglietto implements Serializable {

	/**
	 * Costruisce un biglietto a partire dai parametri impostati.
	 * 
	 * @param stru
	 *            la {@link StrutturaSportiva} sulla quale si sta operando
	 * @param cliente
	 *            il {@link Cliente} che acquista o prenota il biglietto
	 * @param partita
	 *            la {@link Partita} per la quale viene emesso il biglietto
	 * @param settore
	 *            il {@link Settore} dello stadio
	 * @param fila
	 *            la fila del settore dello stadio
	 * @param posto
	 *            il {@link Posto} scelto dal cliente.
	 * 
	 * @author Gaetano Antonucci
	 */
	public Biglietto(StrutturaSportiva stru, Cliente cliente, Partita partita, Settore settore, int fila, int posto) {
		this.cliente = cliente;
		this.partita = partita;
		this.settore = settore;
		this.fila = fila;
		this.posto = new Posto(this.partita.getStadio(), settore, fila, posto);
		this.IDBiglietto = ++IDCounter;
		this.strutturaSelezionata = stru;
		this.prezzo = this.strutturaSelezionata.getBestAvailablePrice(this.partita);
	}

	/**
	 * Restituisce l'ID del biglietto
	 * 
	 * @return IDBiglietto
	 * @author Gaetano Antonucci
	 */
	public int getIDBiglietto() {
		return IDBiglietto;
	}

	/**
	 * Restituisce il cliente che ha prenotato/acquistato il biglietto
	 * 
	 * @return cliente il cliente relativo al biglietto.
	 * @author Gaetano Antonucci
	 * 
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Restituisce la partita a cui il biglietto fa riferimento.
	 * 
	 * @return partita la partita a cui il biglietto fa riferimento.
	 * @author Gaetano Antonucci
	 */
	public Partita getPartita() {
		return partita;
	}

	/**
	 * Restituisce il settore dello stadio in cui e' locato il posto.
	 * 
	 * @return settore il settore dello stadio in cui e' locato il posto.
	 * @author Gaetano Antonucci
	 */
	public Settore getSettore() {
		return this.settore;
	}

	/**
	 * Restituisce la fila in cui e' locato il posto.
	 * 
	 * @return fila la fila in cui e' locato il posto.
	 * @author Gaetano Antonucci
	 */
	public int getFila() {
		return this.fila;
	}

	/**
	 * Restituisce il prezzo del biglietto
	 * 
	 * @return the prezzo il prezzo del biglietto.
	 * @author Gaetano Antonucci
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Imposta il {@link Posto} come prenotatato
	 */
	public void setPrenotato() {
		this.posto.setStato(SeatStatus.PRENOTATO);
	}

	/**
	 * Imposta il {@link Posto} come venduto, quindi pagato
	 */
	public void setVenduto() {
		this.posto.setStato(SeatStatus.VENDUTO);
	}

	/**
	 * Restituisce il {@link Posto} prenotato/acquistato dal cliente
	 * 
	 * @return posto il posto relativo al biglietto.
	 * @author Gaetano Antonucci
	 */
	public Posto getPosto() {
		return this.posto;
	}

	/**
	 * Resituisce le informazioni del biglietto
	 * 
	 * @author Gaetano Antonucci
	 */
	@Override
	public String toString() {
		return this.getClass().getName() + " [IDBiglietto=" + IDBiglietto + ", cliente=" + cliente + ", partita="
				+ partita + ", settore=" + settore + ", fila=" + fila + ", posto=" + posto + ", prezzo=" + prezzo
				+ ", strutturaSelezionata=" + strutturaSelezionata + "]";
	}

	/**
	 * Verifica se l'oggetto corrente e' uguale all'oggetto passato come
	 * parametro
	 * 
	 * @param obj
	 *            - l'oggetto su cui effettuare la verifica
	 * @return {@code true} se quest'oggetto e' uguale all'oggetto passato come
	 *         parametro, {@code false} altrimenti
	 * @author Gaetano Antonucci
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Biglietto other = (Biglietto) obj;

		if ((this.cliente.equals(other.cliente)) && (this.partita.equals(other.partita))) {
			result = true;
		}

		return result;
	}

	private int IDBiglietto;
	private Cliente cliente;
	private Partita partita;
	private Settore settore;
	private int fila;
	private Posto posto;

	private double prezzo;

	private StrutturaSportiva strutturaSelezionata;

	// Iteratore
	private static int IDCounter = 0;

	private static final long serialVersionUID = -6677866736549225712L;
}
