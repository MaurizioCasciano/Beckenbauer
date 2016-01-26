package struttura;

import java.util.Calendar;

/**
 * Enum per i giorni della settimana-
 * 
 * @author Gaetano Antonucci
 * @author Maurizio Casciano
 *
 */
public enum DAYS_OF_WEEK {  // DA RINOMINARE
	
	Lunedi(Calendar.MONDAY), Martedi(Calendar.TUESDAY), Mercoledi(Calendar.WEDNESDAY), 
	Giovedi(Calendar.THURSDAY), Venerdi(Calendar.FRIDAY), Sabato(Calendar.SATURDAY), 
	Domenica(Calendar.SUNDAY);
	
	DAYS_OF_WEEK(int i){
		this.value = i;
	}
	
	public int getValue(){
		return this.value;
	}
	
	/**
	 * Restituisce il giorno della settimana in base al valore passato in input.
	 * @param valore
	 *        Il valore da ricercare
	 * @return il giorno della settimana trovato
	 * @author Gaetano Antonucci
	 * @author Maurizio Casciano
	 */
	public static DAYS_OF_WEEK findDay(int valore){  // Metodo probabilmente mai usato.
		switch(valore){
		case Calendar.MONDAY:
			return DAYS_OF_WEEK.Lunedi;
		case Calendar.TUESDAY:
			return DAYS_OF_WEEK.Martedi;
		case Calendar.WEDNESDAY:
			return DAYS_OF_WEEK.Mercoledi;
		case Calendar.THURSDAY:
			return DAYS_OF_WEEK.Giovedi;
		case Calendar.FRIDAY:
			return DAYS_OF_WEEK.Venerdi;
		case Calendar.SATURDAY:
			return DAYS_OF_WEEK.Sabato;
		case Calendar.SUNDAY:
			return DAYS_OF_WEEK.Domenica;
		default: return findDay((valore % 7) + 1);	
		}
	}
	
	private final int value;
	
	public static void main(String[] args) {
		System.out.println(DAYS_OF_WEEK.findDay(15));
	}
	
}
