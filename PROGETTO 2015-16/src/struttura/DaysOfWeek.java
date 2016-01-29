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
	
	Lunedi(Calendar.MONDAY), Martedi(Calendar.TUESDAY), Mercoledi(Calendar.WEDNESDAY), 
	Giovedi(Calendar.THURSDAY), Venerdi(Calendar.FRIDAY), Sabato(Calendar.SATURDAY), 
	Domenica(Calendar.SUNDAY);
	
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
			return DaysOfWeek.Lunedi;
		case Calendar.TUESDAY:
			return DaysOfWeek.Martedi;
		case Calendar.WEDNESDAY:
			return DaysOfWeek.Mercoledi;
		case Calendar.THURSDAY:
			return DaysOfWeek.Giovedi;
		case Calendar.FRIDAY:
			return DaysOfWeek.Venerdi;
		case Calendar.SATURDAY:
			return DaysOfWeek.Sabato;
		case Calendar.SUNDAY:
			return DaysOfWeek.Domenica;
		default: return findDay((valore % 7) + 1);	
		}
	}
	
	private final int value;
	
	public static void main(String[] args) {
		System.out.println(DaysOfWeek.findDay(15));
	}
	
}
