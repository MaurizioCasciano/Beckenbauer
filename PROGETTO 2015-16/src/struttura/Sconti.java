/**
 * Program: Sconti.java
 * Purpose: Classe che implementa le politiche di sconto
 * @author Gaetano Antonucci
 * @version 2.0 
 * Last Modified: 13/01/2016
 */
package struttura;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Sconti implements Serializable {

	public Sconti(TIPO_SCONTO nomeSconto, double percetualeSconto, GregorianCalendar inizioValidità, 
			GregorianCalendar fineValidità, Stadio stadio) {
		this.scontoScelto = nomeSconto;
		this.percetualeSconto = (percetualeSconto / 100);
		this.inizioValidità = inizioValidità;
		this.fineValidità = fineValidità;
		this.stadio = stadio;
		this.partita = null;
		this.giornoSettimana = null;
	}
	
	public Sconti(TIPO_SCONTO nomeSconto, double percetualeSconto, GregorianCalendar inizioValidità, 
			GregorianCalendar fineValidità, Partita partita) {
		this.scontoScelto = nomeSconto;
		this.percetualeSconto = (percetualeSconto / 100);
		this.inizioValidità = inizioValidità;
		this.fineValidità = fineValidità;
		this.stadio = null;
		this.partita = partita;
		this.giornoSettimana = null;
	}
	
	public Sconti(TIPO_SCONTO nomeSconto, double percetualeSconto, GregorianCalendar inizioValidità, 
			GregorianCalendar fineValidità, DAYS_OF_WEEK giorno) {
		this.scontoScelto = nomeSconto;
		this.percetualeSconto = (percetualeSconto / 100);
		this.inizioValidità = inizioValidità;
		this.fineValidità = fineValidità;
		this.stadio = null;
		this.partita = null;
		this.giornoSettimana = giorno;
	}
	

	/**
	 * @return the scontoScelto
	 */
	public TIPO_SCONTO getScontoScelto() {
		return scontoScelto;
	}

	/**
	 * @return the stadio
	 */
	public Stadio getStadio() {
		return stadio;
	}

	/**
	 * @return the partita
	 */
	public Partita getPartita() {
		return partita;
	}

	/**
	 * @return the inizioValidità
	 */
	public GregorianCalendar getInizioValidità() {
		return inizioValidità;
	}

	/**
	 * @return the fineValidità
	 */
	public GregorianCalendar getFineValidità() {
		return fineValidità;
	}


	/**
	 * @return the giornoSettimana
	 */
	public DAYS_OF_WEEK getGiornoSettimana() {
		return giornoSettimana;
	}

	public double getPercetualeSconto() {
		return percetualeSconto;
	}

	private static final long serialVersionUID = 8088987206686770452L;
	private TIPO_SCONTO scontoScelto;
	private double percetualeSconto;
	private Stadio stadio;
	private Partita partita;
	private GregorianCalendar inizioValidità;
	private GregorianCalendar fineValidità;
	private DAYS_OF_WEEK giornoSettimana;

}
