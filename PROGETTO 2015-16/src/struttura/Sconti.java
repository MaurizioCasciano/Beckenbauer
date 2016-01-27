package struttura;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe che modella le politiche di sconto 
 * 
 * @author Gaetano Antonucci
 */
public class Sconti implements Serializable {

	/**
	 * Costruisce uno sconto che &egrave attivato su tutte le partite giocate in un determinato stadio.
	 * 
	 * @param nomeSconto - il tipo di sconto scelto ({@link TIPO_SCONTO#TutteLePartiteDelloStadio})
	 * @param percetualeSconto - la percetuale scelta per lo sconto
	 * @param inizioValidita - la data in cui lo sconto inizia a valere
	 * @param fineValidita - l'ultima data in cui lo sconto &egrave valido
	 * @param stadio - lo {@link Stadio} su cui &egrave attivo lo sconto
	 * 
	 * @author Gaetano Antonucci
	 */
	public Sconti(TIPO_SCONTO nomeSconto, double percetualeSconto, GregorianCalendar inizioValidita, 
			GregorianCalendar fineValidita, Stadio stadio) {
		this.scontoScelto = nomeSconto;
		this.percetualeSconto = (percetualeSconto / 100);
		this.inizioValidita = inizioValidita;
		this.fineValidita = fineValidita;
		this.stadio = stadio;
		this.partita = null;
		this.giornoSettimana = null;
	}
	
	/**
	 * Costruisce uno sconto che &egrave attivato su una determinata partita
	 * 
	 * @param nomeSconto - il tipo di sconto scelto ({@link TIPO_SCONTO#PartitaCorrente})
	 * @param percetualeSconto - la percetuale scelta per lo sconto
	 * @param inizioValidita - la data in cui lo sconto inizia a valere
	 * @param fineValidita - l'ultima data in cui lo sconto &egrave valido
	 * @param partita - la {@link Partita} su cui &egrave attivo lo sconto
	 * 
	 * @author Gaetano Antonucci
	 */
	public Sconti(TIPO_SCONTO nomeSconto, double percetualeSconto, GregorianCalendar inizioValidita, 
			GregorianCalendar fineValidita, Partita partita) {
		this.scontoScelto = nomeSconto;
		this.percetualeSconto = (percetualeSconto / 100);
		this.inizioValidita = inizioValidita;
		this.fineValidita = fineValidita;
		this.stadio = null;
		this.partita = partita;
		this.giornoSettimana = null;
	}
	
	/**
	 * Costruisce uno sconto che è attivo in un determinato giorno della settimana.
	 * 
	 @param nomeSconto - il tipo di sconto scelto ({@link TIPO_SCONTO#GiornoPrestabilito})
	 * @param percetualeSconto - la percetuale scelta per lo sconto
	 * @param inizioValidita - la data in cui lo sconto inizia a valere
	 * @param fineValidita - l'ultima data in cui lo sconto &egrave valido
	 * @param giorno - il giorno ({@link DAYS_OF_WEEK}) il cui lo sconto è valido
	 * 
	 * @author Gaetano Antonucci
	 */
	public Sconti(TIPO_SCONTO nomeSconto, double percetualeSconto, GregorianCalendar inizioValidita, 
			GregorianCalendar fineValidita, DAYS_OF_WEEK giorno) {
		this.scontoScelto = nomeSconto;
		this.percetualeSconto = (percetualeSconto / 100);
		this.inizioValidita = inizioValidita;
		this.fineValidita = fineValidita;
		this.stadio = null;
		this.partita = null;
		this.giornoSettimana = giorno;
	}
	

	/**
	 * Restituisce la tipologi di sconto dell'oggetto corrente
	 * 
	 * @return scontoScelto
	 * @author Gaetano Antonucci
	 */
	public TIPO_SCONTO getScontoScelto() {
		return scontoScelto;
	}

	/**
	 * Restituisce lo stadio su cui &egrave attivo lo sconto, se si tratta di uno sconto su stadio, 
	 * altrimenti resituisce {@code null}
	 * 
	 * @return stadio
	 * @author Gaetano Antonucci
	 */
	public Stadio getStadio() {
		return stadio;
	}

	/**
	 * Restituisce la partita su cui &egrave attivo lo sconto, se si tratta di uno sconto su partita, 
	 * altrimenti restituisce {@code null}
	 * 
	 * @return partita
	 * @author Gaetano Antonucci
	 */
	public Partita getPartita() {
		return partita;
	}

	/**
	 * Restituisce la data ({@link GregorianCalendar} in cui lo sconto inizia a valere
	 * 
	 * @return inizioValidita
	 * @author Gaetano Antonucci
	 */
	public GregorianCalendar getInizioValidita() {
		return inizioValidita;
	}

	/**
	 * Restituisce l'ultima data ({@link GregorianCalendar} in cui lo sconto vale, dopo questa data
	 * lo sconto non vale piu'
	 * 
	 * @return fineValidita
	 * @author Gaetano Antonucci
	 */
	public GregorianCalendar getFineValidita() {
		return fineValidita;
	}


	/**
	 * Restituisce il giorno della settimana ({@link DAYS_OF_WEEK}) in cui lo sconto è valido, 
	 * se si tratta di uno sconto sul giorno della settimana, altrimenti restituisce {@code null}
	 * 
	 * @return the giornoSettimana
	 * @author Gaetano Antonucci
	 */
	public DAYS_OF_WEEK getGiornoSettimana() {
		return giornoSettimana;
	}

	/**
	 * Restituisce la percetuale dello sconto
	 * 
	 * @return percetualeSconto
	 * @author Gaetano Antonucci
	 */
	public double getPercetualeSconto() {
		return percetualeSconto;
	}
	
	/**
	 * Restituisce le informazioni relative all'oggeto come {@link String}
	 * 
	 * @author Gaetano Antonucci
	 */
	@Override
	public String toString(){
		String inizioValiditaString = this.inizioValidita.get(Calendar.DAY_OF_MONTH)+"."+ (this.inizioValidita.get(Calendar.MONTH)+1)+"." 
								    + this.inizioValidita.get(Calendar.YEAR);
		
		String fineValiditaString = this.fineValidita.get(Calendar.DAY_OF_MONTH)+"."+ (this.fineValidita.get(Calendar.MONTH)+1)+"."
								    + this.fineValidita.get(Calendar.YEAR);
		
		return (" " + String.valueOf(this.scontoScelto) + " Perc.: " + this.percetualeSconto + "\n Stadio: " + this.stadio  
				+ " Partita: " + this.partita + " GiornoPrest: " + String.valueOf(this.giornoSettimana) 
				+ "\n Iniz.Val.: " + inizioValiditaString + " Fine Val.: " + fineValiditaString);
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

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass())
			return false;
		
		Sconti other = (Sconti) obj;
		
		switch(other.scontoScelto){
		case PartitaCorrente:
			if(this.scontoScelto == other.scontoScelto){
				if(this.partita.equals(other.partita) && this.inizioValidita.equals(other.inizioValidita) 
						&& this.fineValidita.equals(other.fineValidita)){
					result = true;
				}
			} else {
				result = false;
			}
			break;
			
		case TutteLePartiteDelloStadio:
			if(this.scontoScelto == other.scontoScelto){
				if(this.stadio.equals(other.stadio) && this.inizioValidita.equals(other.inizioValidita) 
						&& this.fineValidita.equals(other.fineValidita)){
					result = true;
				}
			} else {
				result = false;
			}
			break;
		
		case GiornoPrestabilito:
			if(this.scontoScelto == other.scontoScelto){
				if(this.giornoSettimana == other.giornoSettimana && this.inizioValidita.equals(other.inizioValidita) 
						&& this.fineValidita.equals(other.fineValidita)){
					result = true;
				}
			} else {
				result = false;
			}
			break;
			
		default: break;	
		}
		
		return result;
	}



	private static final long serialVersionUID = 8088987206686770452L;
	private TIPO_SCONTO scontoScelto;
	private double percetualeSconto;
	private Stadio stadio;
	private Partita partita;
	private GregorianCalendar inizioValidita;
	private GregorianCalendar fineValidita;
	private DAYS_OF_WEEK giornoSettimana;

}
