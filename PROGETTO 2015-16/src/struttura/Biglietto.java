/**
 * Program: Biglietto.java
 * Purpose: Classe che modella un biglietto di una partita
 * @author Gaetano Antonucci
 * @version 1.0 
 * Last Modified: 11/01/2016
 */
package struttura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import struttura.filters.ScontiPerStadio;
import struttura.filters.ScontiByDayOfWeek;
import struttura.filters.ScontoPerPartita;
import user.Cliente;

public class Biglietto implements Serializable {

	public Biglietto(StrutturaSportiva stru, Cliente cliente, Partita partita, Settore settore, int fila, int posto) {
		this.cliente = cliente;
		this.partita = partita;
		//this.settore = settore;
		//this.fila = fila;
		//this.posto = posto;
		this.posto = new Posto(this.partita.getStadio(), settore, fila, posto);
		
		this.IDBiglietto = ++IDCounter;
		
		this.strutturaSelezionata = stru;
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
	 * Restituisce la partita a cui il biglieto fa riferimento
	 * 
	 * @return partita
	 */
	public Partita getPartita() {
		return partita;
	}

	/**
	 * Restituisce il settore dello stadio in cui è locato il posto
	 * 
	 * @return settore
	 */
	/*public String getSettore() {
		return settore;
	} */

	/**
	 * Restituisce la fila in cui è locato il posto
	 * 
	 * @return fila
	 */
	/*public int getFila() {
		return fila;
	}*/

	/**
	 * Restituisce il posto prenotato/acquistato dal cliente
	 * 
	 * @return posto
	 */
	/*public int getPosto() {
		return posto;
	}*/
	
	public void calcolaPrezzo(){
		double prezzoDiPartenza = this.partita.getStadio().getPrezzoPerPartita();
		
		ArrayList<Sconti> perPartita = this.strutturaSelezionata.getScontiApplicabili(new ScontoPerPartita(this.strutturaSelezionata.getSconti()), this.partita);
		ArrayList<Sconti> perStadio = this.strutturaSelezionata.getScontiApplicabili(new ScontiPerStadio(this.strutturaSelezionata.getSconti()), this.partita);
		ArrayList<Sconti> perGiorno = this.strutturaSelezionata.getScontiApplicabili(new ScontiByDayOfWeek(this.strutturaSelezionata.getSconti()), this.partita);
		
		double maxScontoPartita = 0.00;
		double maxScontoStadio = 0.00;
		double maxScontoGiorno = 0.00;
		
		for(Sconti s1: perPartita){
			if(maxScontoPartita <= s1.getPercetualeSconto()){
				maxScontoPartita = s1.getPercetualeSconto();
			}
		}
		
		for(Sconti s2: perStadio){
			if(maxScontoStadio <= s2.getPercetualeSconto()){
				maxScontoStadio = s2.getPercetualeSconto();
			}
		}
		
		for(Sconti s3: perGiorno){
			if(maxScontoGiorno <= s3.getPercetualeSconto()){
				maxScontoGiorno = s3.getPercetualeSconto();
			}
		}
		
		double[] scontiMassimi = {maxScontoPartita, maxScontoGiorno, maxScontoStadio};
		Arrays.sort(scontiMassimi);
		
		double maxSconto = scontiMassimi[scontiMassimi.length - 1];
		
		double prezzoFinale = prezzoDiPartenza - (prezzoDiPartenza * maxSconto); // maxSconto sarà compreso tra 0,01 e 1,00
		
		this.prezzo = prezzoFinale;
		
	}

	/**
	 * @return the prezzo
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Restituisce lo stato biglietto in riferimento alle prenotazioni
	 * 
	 * @return true se il biglietto è stato prenotato, false se il biglietto è
	 *         stato acquistato direttamente
	 */
	/*public boolean isPrenotato() {
		return prenotato;
	}*/

	/**
	 * Restituisce lo stato del biglietto in riferimento alle vendite
	 * 
	 * @return true se il biglietto è stato comprato, quindi pagato, false se
	 *         il biglietto è stato solo prenotato
	 */
	/*public boolean isPagato() {
		return pagato;
	} */

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
	
	public Posto getPosto(){
		return this.posto;
	}

	@Override
	public String toString() {
		return("IDBiglietto: " + this.IDBiglietto + " Partita: " + this.partita + " \n" /*+ 
			   "Settore: " + this.settore + " Fila: " + this.fila + " Posto: " + this.posto*/);
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
		
		if((this.cliente.equals(other.cliente)) && (this.partita.equals(other.partita))){
			result = true;
		}
		
		return result;
	}

	private int IDBiglietto;
	private Cliente cliente;
	private Partita partita;
	//private String settore;
	//private int fila;
	//private int posto;
	private Posto posto;
	
	private double prezzo; 
	//private boolean prenotato;
	//private boolean pagato;
	
	private StrutturaSportiva strutturaSelezionata;

	// Iteratore
	private static int IDCounter = 1;
	
	private static final long serialVersionUID = -6677866736549225712L;
}
