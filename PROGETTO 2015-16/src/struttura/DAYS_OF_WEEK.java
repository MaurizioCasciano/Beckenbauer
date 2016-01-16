package struttura;

import java.util.Calendar;

public enum DAYS_OF_WEEK {
	
	Lunedì(Calendar.MONDAY), Martedì(Calendar.TUESDAY), Mercoledì(Calendar.WEDNESDAY), 
	Giovedì(Calendar.THURSDAY), Venerdì(Calendar.FRIDAY), Sabato(Calendar.SATURDAY), 
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
	 */
	public static DAYS_OF_WEEK findDay(int valore){
		switch(valore){
		case Calendar.MONDAY:
			return DAYS_OF_WEEK.Lunedì;
		case Calendar.TUESDAY:
			return DAYS_OF_WEEK.Martedì;
		case Calendar.WEDNESDAY:
			return DAYS_OF_WEEK.Mercoledì;
		case Calendar.THURSDAY:
			return DAYS_OF_WEEK.Giovedì;
		case Calendar.FRIDAY:
			return DAYS_OF_WEEK.Venerdì;
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
