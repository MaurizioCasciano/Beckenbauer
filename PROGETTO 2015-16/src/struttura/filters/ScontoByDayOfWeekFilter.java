package struttura.filters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import struttura.Partita;
import struttura.Sconto;
import struttura.DaysOfWeek;

/**
* Classe di call-back per l'interfaccia ScontoFilter per gli Sconti in base al Giorno Prestabilito.
* ({@link DaysOfWeek})
* 
* @author Gaetano Antonucci
*/
public class ScontoByDayOfWeekFilter implements ScontoFilter, Serializable{
	
	/**
	 * Costruisce un filtro sugli sconti
	 * 
	 * @param sconti - l'ArrayList degli Sconti
	 */
	public ScontoByDayOfWeekFilter(ArrayList<Sconto> sconti){
		this.sconti = sconti;
	}

	@Override
	public void updateCurrentSconto(int i) {
		this.sconto = this.sconti.get(i);
	}
	
	@Override
	public boolean accept(Partita partitaDiCalcio) {
		DaysOfWeek giornoSettimanaSconto = this.sconto.getGiornoSettimana();
		boolean result = false;
		int giorno = (partitaDiCalcio.getData().get(Calendar.DAY_OF_WEEK));
		DaysOfWeek giornoSettimana = DaysOfWeek.findDay(giorno);
		
		if(giornoSettimanaSconto != null){
			if((giornoSettimana.getValue() == this.sconto.getGiornoSettimana().getValue()) 
					&& !(partitaDiCalcio.getData().before(sconto.getInizioValidita())) 
					&& !(partitaDiCalcio.getData().after(sconto.getFineValidita()))){
				
					result = true;
			}
		}
		
		return result;
	}
	
	private Sconto sconto;
	private ArrayList<Sconto> sconti;
	
	private static final long serialVersionUID = 6576677813144320629L;


	public static void main (String[] args){
		System.out.println(Calendar.SUNDAY);
		System.out.println(Calendar.SATURDAY);
		System.out.println(Calendar.FRIDAY);
	}

}
