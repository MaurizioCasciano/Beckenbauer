/**
 * Program: Biglietto.java
 * Purpose: Classe che modella un biglietto di una partita
 * @author Gaetano Antonucci
 * @version 1.0 
 * Last Modified: 11/01/2016
 */
package struttura;

import java.io.Serializable;
import user.Cliente;

public class Biglietto implements Serializable {

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
	 */
	public int getIDBiglietto() {
		return IDBiglietto;
	}

	/**
	 * Restituisce il cliente che ha prenotato/acquistato il biglietto
	 * 
	 * @return cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Restituisce la partita a cui il biglietto fa riferimento
	 * 
	 * @return partita
	 */
	public Partita getPartita() {
		return partita;
	}

	/**
	 * Restituisce il settore dello stadio in cui e' locato il posto
	 * 
	 * @return settore
	 */
	public Settore getSettore() {
		return this.settore;
	}

	/**
	 * Restituisce la fila in cui e' locato il posto
	 * 
	 * @return fila
	 */
	public int getFila() {
		return this.fila;
	}

	/**
	 * @return the prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * @param prenotato
	 */
	public void setPrenotato(boolean prenotato) {
		this.posto.setPrenotato(prenotato);
	}

	/**
	 * @param pagato
	 */
	public void setPagato(boolean pagato) {
		this.posto.setVenduto(pagato);
	}

	public Posto getPosto() {
		return this.posto;
	}

	@Override
	public String toString() {
		return ("IDBiglietto: " + this.IDBiglietto + " " + this.partita
				+ " \n" /*
						 * + "Settore: " + this.settore + " Fila: " + this.fila
						 * + " Posto: " + this.posto
						 */);
	}

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
	// private String settore;
	private int fila;
	// private int posto;
	private Posto posto;

	private double prezzo;
	// private boolean prenotato;
	// private boolean pagato;

	private StrutturaSportiva strutturaSelezionata;

	// Iteratore
	private static int IDCounter = 0;

	private static final long serialVersionUID = -6677866736549225712L;
}
