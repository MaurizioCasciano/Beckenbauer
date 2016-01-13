package struttura.filters;

import java.io.Serializable;
import java.util.Calendar;

import struttura.Partita;
import struttura.Sconti;
import struttura.DAYS_OF_WEEK;

public class ScontoByDayOfWeek implements Filter, Serializable{
	
	public ScontoByDayOfWeek(Sconti sconto){
		this.sconto = sconto;
	}

	@Override
	public boolean accept(Partita partitaDiCalcio) {
		boolean result = false;
		int giorno = (partitaDiCalcio.getData().get(Calendar.DAY_OF_WEEK));
		DAYS_OF_WEEK giornoSettimana = DAYS_OF_WEEK.Lunedì;
		
		giornoSettimana.setValue(giorno);
		
		if(giornoSettimana.getValue() == this.sconto.getGiornoSettimana().getValue()){
			result = true;
		}
		
		return result;
	}
	
	private Sconti sconto;
	
	private static final long serialVersionUID = 6576677813144320629L;

	public static void main (String[] args){
		System.out.println(Calendar.MONDAY);
	}

}
