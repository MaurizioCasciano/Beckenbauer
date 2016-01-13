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
	
	public void setValue(int newValue){
		this.value = newValue;
	}
	
	private int value;
	
}
