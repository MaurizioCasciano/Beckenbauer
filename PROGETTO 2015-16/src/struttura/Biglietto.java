/**
 * Program: Biglietto.java
 * Purpose: Classe che modella un biglietto di una partita
 * @author Gaetano Antonucci
 * @version 1.0 
 * Last Modified: 29/12/2015
 */
package struttura;

import user.Cliente;

public class Biglietto {
	
	public Biglietto(Cliente cliente, PartitaDiCalcio partita, String settore, int fila, int posto, double prezzo) {
		this.cliente = cliente;
		this.partita = partita;
		this.settore = settore;
		this.fila = fila;
		this.posto = posto;
		this.prezzo = prezzo;
		
		this.IDBiglietto = ++IDCounter;
	}

	/**
	 * Restituisce l'ID del biglietto
	 * @return IDBiglietto
	 */
	public int getIDBiglietto() {
		return IDBiglietto;
	}

	/**
	 * Restituisce il cliente che ha prenotato/acquistato il biglietto
	 * @return cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Restituisce la partita a cui il biglieto fa riferimento
	 * @return partita
	 */
	public PartitaDiCalcio getPartita() {
		return partita;
	}

	/**
	 * Restituisce il settore dello stadio in cui è locato il posto 
	 * @return settore
	 */
	public String getSettore() {
		return settore;
	}

	/**
	 * Restituisce la fila in cui è locato il posto
	 * @return fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * Restituisce il posto prenotato/acquistato dal cliente
	 * @return posto
	 */
	public int getPosto() {
		return posto;
	}

	/**
	 * @return the prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Restituisce lo stato biglietto in riferimento alle prenotazioni
	 * @return true se il biglietto è stato prenotato, false se il biglietto è stato
	 * 		   acquistato direttamente
	 */
	public boolean isPrenotato() {
		return prenotato;
	}

	/**
	 * Restituisce lo stato del biglietto in riferimento alle vendite
	 * @return true se il biglietto è stato comprato, quindi pagato, false se il
	 * 		   biglietto è stato solo prenotato
	 */
	public boolean isPagato() {
		return pagato;
	}

	/**
	 * @param prenotato
	 */
	public void setPrenotato(boolean prenotato) {
		this.prenotato = prenotato;
	}

	/**
	 * @param pagato
	 */
	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}




	private int IDBiglietto;
	private Cliente cliente;
	private PartitaDiCalcio partita;
	private String settore;
	private int fila;
	private int posto;
	private double prezzo;	// (GA) Da calcolare automaticamente
	private boolean prenotato;
	private boolean pagato;
	
	// Iteratore
	private static int IDCounter = 1;
}
