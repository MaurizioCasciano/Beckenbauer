package struttura;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe che modella le politiche di sconto 
 * 
 * @author Gaetano Antonucci
 */
public class Sconto implements Serializable {

	/**
	 * Costruisce uno sconto che &egrave attivato su tutte le partite giocate in un determinato stadio.
	 * 
	 * @param tipoSconto - il tipo di sconto scelto ({@link TipoSconto#TUTTE_LE_PARTITE_DELLO_STADIO})
	 * @param percetualeSconto - la percetuale scelta per lo sconto
	 * @param inizioValidita - la data in cui lo sconto inizia a valere
	 * @param fineValidita - l'ultima data in cui lo sconto &egrave valido
	 * @param stadio - lo {@link Stadio} su cui &egrave attivo lo sconto
	 * 
	 * @author Gaetano Antonucci
	 */
	public Sconto(TipoSconto tipoSconto, double percetualeSconto, GregorianCalendar inizioValidita, 
			GregorianCalendar fineValidita, Stadio stadio) {
		this.tipoSconto = tipoSconto;
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
	 * @param nomeSconto - il tipo di sconto scelto ({@link TipoSconto#PARTITA_CORRENTE})
	 * @param percetualeSconto - la percetuale scelta per lo sconto
	 * @param inizioValidita - la data in cui lo sconto inizia a valere
	 * @param fineValidita - l'ultima data in cui lo sconto &egrave valido
	 * @param partita - la {@link Partita} su cui &egrave attivo lo sconto
	 * 
	 * @author Gaetano Antonucci
	 */
	public Sconto(TipoSconto nomeSconto, double percetualeSconto, GregorianCalendar inizioValidita, 
			GregorianCalendar fineValidita, Partita partita) {
		this.tipoSconto = nomeSconto;
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
	 @param nomeSconto - il tipo di sconto scelto ({@link TipoSconto#GIORNO_PRESTABILITO})
	 * @param percetualeSconto - la percetuale scelta per lo sconto
	 * @param inizioValidita - la data in cui lo sconto inizia a valere
	 * @param fineValidita - l'ultima data in cui lo sconto &egrave valido
	 * @param giorno - il giorno ({@link DaysOfWeek}) il cui lo sconto è valido
	 * 
	 * @author Gaetano Antonucci
	 */
	public Sconto(TipoSconto nomeSconto, double percetualeSconto, GregorianCalendar inizioValidita, 
			GregorianCalendar fineValidita, DaysOfWeek giorno) {
		this.tipoSconto = nomeSconto;
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
	public TipoSconto getScontoScelto() {
		return tipoSconto;
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
	 * Restituisce il giorno della settimana ({@link DaysOfWeek}) in cui lo sconto è valido, 
	 * se si tratta di uno sconto sul giorno della settimana, altrimenti restituisce {@code null}
	 * 
	 * @return the giornoSettimana
	 * @author Gaetano Antonucci
	 */
	public DaysOfWeek getGiornoSettimana() {
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
		
		return (" " + String.valueOf(this.tipoSconto) + " Perc.: " + this.percetualeSconto + "\n Stadio: " + this.stadio  
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
		
		Sconto other = (Sconto) obj;
		
		switch(other.tipoSconto){
		case PARTITA_CORRENTE:
			if(this.tipoSconto == other.tipoSconto){
				if(this.partita.equals(other.partita) && this.inizioValidita.equals(other.inizioValidita) 
						&& this.fineValidita.equals(other.fineValidita)){
					result = true;
				}
			} else {
				result = false;
			}
			break;
			
		case TUTTE_LE_PARTITE_DELLO_STADIO:
			if(this.tipoSconto == other.tipoSconto){
				if(this.stadio.equals(other.stadio) && this.inizioValidita.equals(other.inizioValidita) 
						&& this.fineValidita.equals(other.fineValidita)){
					result = true;
				}
			} else {
				result = false;
			}
			break;
		
		case GIORNO_PRESTABILITO:
			if(this.tipoSconto == other.tipoSconto){
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
	private TipoSconto tipoSconto;
	private double percetualeSconto;
	private Stadio stadio;
	private Partita partita;
	private GregorianCalendar inizioValidita;
	private GregorianCalendar fineValidita;
	private DaysOfWeek giornoSettimana;

}
