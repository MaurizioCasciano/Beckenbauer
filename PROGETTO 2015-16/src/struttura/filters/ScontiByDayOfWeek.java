package struttura.filters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import struttura.Partita;
import struttura.Sconti;
import struttura.DAYS_OF_WEEK;

/**
* Classe di call-back per l'interfaccia ScontiFilter per gli Sconti in base al Giorno Prestabilito
* ({@link DAYS_OF_WEEK})
* 
* @author Gaetano Antonucci
*/
public class ScontiByDayOfWeek implements ScontiFilter, Serializable{
	
	/*public ScontoByDayOfWeek(Sconti sconto){
		this.sconto = sconto;
	}*/
	
	/**
	 * Costruisce un filtro sugli sconti
	 * 
	 * @param sconti - l'ArrayList degli Sconti
	 */
	public ScontiByDayOfWeek(ArrayList<Sconti> sconti){
		this.sconti = sconti;
	}

	@Override
	public void updateCurrentSconto(int i) {
		this.sconto = this.sconti.get(i);
		
	}
	
	@Override
	public boolean accept(Partita partitaDiCalcio) {
		DAYS_OF_WEEK giornoSettimanaSconto = this.sconto.getGiornoSettimana();
		boolean result = false;
		int giorno = (partitaDiCalcio.getData().get(Calendar.DAY_OF_WEEK));
		DAYS_OF_WEEK giornoSettimana = DAYS_OF_WEEK.findDay(giorno);
		
		if(giornoSettimanaSconto != null){
			if((giornoSettimana.getValue() == this.sconto.getGiornoSettimana().getValue()) 
					&& !(partitaDiCalcio.getData().before(sconto.getInizioValidita())) 
					&& !(partitaDiCalcio.getData().after(sconto.getFineValidita()))){
				
					result = true;
			}
		}
		
		
		return result;
	}
	
	private Sconti sconto;
	private ArrayList<Sconti> sconti;
	
	private static final long serialVersionUID = 6576677813144320629L;


	public static void main (String[] args){
		System.out.println(Calendar.SUNDAY);
		System.out.println(Calendar.SATURDAY);
		System.out.println(Calendar.FRIDAY);
	}

}
