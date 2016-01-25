package struttura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import struttura.filters.ScontiPerStadio;
import struttura.filters.ScontiByDayOfWeek;
import struttura.filters.ScontoPerPartita;
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
	 * @param stru - la {@link StrutturaSportiva} sulla quale si sta operando
	 * @param cliente - il {@link Cliente} che acquista o prenota il biglietto
	 * @param partita - la {@link Partita} per la quale viene emesso il biglietto
	 * @param settore - il {@link Settore} dello stadio 
	 * @param fila - la fila del settore dello stadio
	 * @param posto -  il {@link Posto} scelto dal cliente
	 * 
	 * @author Gaetano Antonucci
	 */
	public Biglietto(StrutturaSportiva stru, Cliente cliente, Partita partita, Settore settore, int fila, int posto) {
		this.cliente = cliente;
		this.partita = partita;
		this.settore = settore;
		this.fila = fila;
		// this.posto = posto;
		this.posto = new Posto(this.partita.getStadio(), settore, fila, posto);

		this.IDBiglietto = ++IDCounter;

		this.strutturaSelezionata = stru;
		this.calcolaPrezzo();
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
	 * @return cliente
	 * @author Gaetano Antonucci
	 * 
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Restituisce la partita a cui il biglietto fa riferimento
	 * 
	 * @return partita
	 * @author Gaetano Antonucci
	 */
	public Partita getPartita() {
		return partita;
	}

	/**
	 * Restituisce il settore dello stadio in cui e' locato il posto
	 * 
	 * @return settore
	 * @author Gaetano Antonucci
	 */
	public Settore getSettore() {
		return this.settore;
	}

	/**
	 * Restituisce la fila in cui e' locato il posto
	 * 
	 * @return fila
	 * @author Gaetano Antonucci
	 */
	public int getFila() {
		return this.fila;
	}

	/**
	 * Calcola il prezzo del biglietto in base alla migliore politica di sconto attiva.
	 * 
	 * @author Gaetano Antonucci
	 */
	public void calcolaPrezzo() {
		double prezzoDiPartenza = this.partita.getStadio().getPrezzoPerPartita();

		ArrayList<Sconti> perPartita = this.strutturaSelezionata
				.getScontiApplicabili(new ScontoPerPartita(this.strutturaSelezionata.getSconti()), this.partita);
		ArrayList<Sconti> perStadio = this.strutturaSelezionata
				.getScontiApplicabili(new ScontiPerStadio(this.strutturaSelezionata.getSconti()), this.partita);
		ArrayList<Sconti> perGiorno = this.strutturaSelezionata
				.getScontiApplicabili(new ScontiByDayOfWeek(this.strutturaSelezionata.getSconti()), this.partita);

		double maxScontoPartita = 0.00;
		double maxScontoStadio = 0.00;
		double maxScontoGiorno = 0.00;

		for (Sconti s1 : perPartita) {
			if (maxScontoPartita <= s1.getPercetualeSconto()) {
				maxScontoPartita = s1.getPercetualeSconto();
			}
		}

		for (Sconti s2 : perStadio) {
			if (maxScontoStadio <= s2.getPercetualeSconto()) {
				maxScontoStadio = s2.getPercetualeSconto();
			}
		}

		for (Sconti s3 : perGiorno) {
			if (maxScontoGiorno <= s3.getPercetualeSconto()) {
				maxScontoGiorno = s3.getPercetualeSconto();
			}
		}

		double[] scontiMassimi = { maxScontoPartita, maxScontoGiorno, maxScontoStadio };
		Arrays.sort(scontiMassimi);
		double maxSconto = scontiMassimi[scontiMassimi.length - 1];

		/*
		 * System.out.println("Verifica Sconto su Biglietto");
		 * System.out.println("maxScontoPartita " + maxScontoPartita);
		 * System.out.println("maxScontoStadio " + maxScontoStadio);
		 * System.out.println("maxScontoGiorno " + maxScontoGiorno);
		 * 
		 * /*double maxSconto = 0; if(maxScontoPartita <= maxScontoStadio)
		 * maxSconto = maxScontoStadio; else maxSconto = maxScontoPartita;
		 * 
		 * if(maxSconto <= maxScontoGiorno) maxSconto = maxScontoGiorno;
		 * 
		 * System.out.println("maxSconto " + maxSconto); System.out.println(
		 * "Fine Verifica Biglietto");
		 */
		double prezzoFinale = prezzoDiPartenza - (prezzoDiPartenza * maxSconto);

		this.prezzo = prezzoFinale;

	}

	/**
	 * Restituisce il prezzo del biglietto
	 * @return the prezzo
	 * @author Gaetano Antonucci
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/*/**
	 * Restituisce lo stato biglietto in riferimento alle prenotazioni
	 * 
	 * @return true se il biglietto e' stato prenotato, false se il biglietto e'      DA ELIMINARE
	 *         stato acquistato direttamente
	 */
	/*
	 * public boolean isPrenotato() { return prenotato; }
	 */

	/*/**
	 * Restituisce lo stato del biglietto in riferimento alle vendite
	 * 
	 * @return true se il biglietto è stato comprato, quindi pagato, false se        DA ELIMINARE
	 *         il biglietto è stato solo prenotato
	 */
	/*
	 * public boolean isPagato() { return pagato; }
	 */

	/**
	 * Imposta il {@link Posto} come prenotatato
	 * 
	 * @param prenotato
	 * @author Maurizio Casciano
	 */
	public void setPrenotato(boolean prenotato) {
		this.posto.setPrenotato(prenotato);
	}

	/**
	 * Imposta il {@link Posto} come venduto, quindi pagato
	 * 
	 * @param pagato
	 * @author Maurizio Casciano
	 */
	public void setPagato(boolean pagato) {
		this.posto.setVenduto(pagato);
	}

	/**
	 * Restituisce il {@link Posto} prenotato/acquistato dal cliente
	 * 
	 * @return posto
	 * @authir Gaetano Antonucci
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
		return ("IDBiglietto: " + this.IDBiglietto + " " + this.partita
				+ " \n" /*
						 * + "Settore: " + this.settore + " Fila: " + this.fila
						 * + " Posto: " + this.posto
						 */);
	}

	/**
	 * Verifica se l'oggetto corrente è uguale all'oggetto passato come parametro
	 * 
	 * @param obj - l'oggetto su cui effettuare la verifica
	 * @return {@code true} se quest'oggetto è uguale all'oggetto passato come parametro, {@code false} altrimenti
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
	// private int posto;
	private Posto posto;

	private double prezzo;

	private StrutturaSportiva strutturaSelezionata;

	// Iteratore
	private static int IDCounter = 0;

	private static final long serialVersionUID = -6677866736549225712L;
}
