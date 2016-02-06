package struttura;

import java.util.Calendar;

/**
 * Enum per i giorni della settimana-
 * 
 * @author Gaetano Antonucci
 * @author Maurizio Casciano
 *
 */
public enum DaysOfWeek {  // DA RINOMINARE
	
	LUNEDI(Calendar.MONDAY), MARTEDI(Calendar.TUESDAY), MERCOLEDI(Calendar.WEDNESDAY), 
	GIOVEDI(Calendar.THURSDAY), VENERDI(Calendar.FRIDAY), SABATO(Calendar.SATURDAY), 
	DOMENICA(Calendar.SUNDAY);
	
	DaysOfWeek(int i){
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
	public static DaysOfWeek findDay(int valore){  // Metodo probabilmente mai usato.
		switch(valore){
		case Calendar.MONDAY:
			return DaysOfWeek.LUNEDI;
		case Calendar.TUESDAY:
			return DaysOfWeek.MARTEDI;
		case Calendar.WEDNESDAY:
			return DaysOfWeek.MERCOLEDI;
		case Calendar.THURSDAY:
			return DaysOfWeek.GIOVEDI;
		case Calendar.FRIDAY:
			return DaysOfWeek.VENERDI;
		case Calendar.SATURDAY:
			return DaysOfWeek.SABATO;
		case Calendar.SUNDAY:
			return DaysOfWeek.DOMENICA;
		default: return findDay((valore % 7) + 1);	
		}
	}
	
	private final int value;
	
	public static void main(String[] args) {
		System.out.println(DaysOfWeek.findDay(16));
		System.out.println(DaysOfWeek.findDay(33));
	}
}
