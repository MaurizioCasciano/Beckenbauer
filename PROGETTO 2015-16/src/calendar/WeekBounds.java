package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class WeekBounds {

	/**
	 * Restituisce l'oggetto GregorianCalendar corrispondente all'istante in cui
	 * ha inizio la settimana della data passata in input.
	 * 
	 * @param date
	 *            La data di cui si vuole conoscere l'istante in cui ha inizio
	 *            la sua settimana di appartenenza.
	 * 
	 * @return L'oggetto GregorianCalendar corrispondente all'istante in cui ha
	 *         inizio la settimana della data passata in input.
	 */
	public static GregorianCalendar getWeekStart(GregorianCalendar date) {

		/*
		 * Effettua il clone della data in modo che le modifiche non incidano
		 * direttamente sull'oggetto passato in input.
		 */
		GregorianCalendar weekStart = (GregorianCalendar) date.clone();

		/*
		 * From Java api
		 * https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
		 * 
		 * Note: There are certain possible ambiguities in interpretation of
		 * certain singular times, which are resolved in the following ways:
		 * 
		 * 23:59 is the last minute of the day and 00:00 is the first minute of
		 * the next day. Thus, 23:59 on Dec 31, 1999 < 00:00 on Jan 1, 2000 <
		 * 00:01 on Jan 1, 2000. Although historically not precise, midnight
		 * also belongs to "am", and noon belongs to "pm", so on the same day,
		 * 12:00 am (midnight) < 12:01 am, and 12:00 pm (noon) < 12:01 pm
		 */

		/*
		 * Sottrae un giorno alla data passat in input, ed imposta i vari campi
		 * dell'orario a 0. In questo modo si ottiene la data rappresentante
		 * l'istante in cui ha inizio quel giorno.
		 */
		weekStart.add(Calendar.DAY_OF_MONTH, -1);
		weekStart.set(Calendar.HOUR_OF_DAY, 0);
		weekStart.set(Calendar.MINUTE, 0);
		weekStart.set(Calendar.SECOND, 0);
		weekStart.set(Calendar.MILLISECOND, 0);

		/*
		 * Calcola i giorni trascorsi dall'inizio della settimana.
		 */
		int dayOfThisWeek = weekStart.get(Calendar.DAY_OF_WEEK) - weekStart.getFirstDayOfWeek();

		weekStart.add(Calendar.DAY_OF_WEEK, -dayOfThisWeek);
		return weekStart;
	}

	/**
	 * Returns the GregorianCalendar representing the end of the date's week.
	 * 
	 * @param date
	 *            The date you want to know the end of it's week.
	 * @return The GregorianCalendar representing the end of the date's week.
	 */
	public static GregorianCalendar getWeekEnd(GregorianCalendar date) {
		GregorianCalendar weekEnd = getWeekStart(date);

		weekEnd.set(Calendar.HOUR_OF_DAY, 23);
		weekEnd.set(Calendar.MINUTE, 59);
		weekEnd.set(Calendar.SECOND, 59);
		weekEnd.set(Calendar.MILLISECOND, 999);
		weekEnd.add(Calendar.DAY_OF_WEEK, 6);
		return weekEnd;
	}

	public static void main(String[] args) {
		GregorianCalendar date = new GregorianCalendar();
		date.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));

		date.set(Calendar.MONTH, Calendar.JANUARY);
		date.set(Calendar.DAY_OF_MONTH, 17);
		date.set(Calendar.YEAR, 2016);

		date.set(Calendar.HOUR_OF_DAY, 20);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);

		System.out.println(date.getTime());
		System.out.println("START:\n" + getWeekStart(date).getTime());
		System.out.println("END:\n" + getWeekEnd(date).getTime());

		System.out.println("DATE: " + date.getTime());
	}
}
