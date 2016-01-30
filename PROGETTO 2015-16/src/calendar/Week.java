package calendar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe che rappresenta un oggetto Week. Un oggetto week è dotato di una data
 * indicante l'inizio e una data indicante la fine della settimana.
 * 
 * @author Maurizio
 */
public class Week implements Serializable {

	/**
	 * Crea un oggetto Week rappresentante una settimana.
	 * 
	 * @param start
	 *            La data di inizio della settimana.
	 * @param end
	 *            La data di fine della settimana.
	 * @author Maurizio
	 */
	public Week(GregorianCalendar start, GregorianCalendar end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Restituisce la data di inizio di questa settimana.
	 * 
	 * @return La data di inizio di questa settimana.
	 * @author Maurizio
	 */
	public GregorianCalendar getStart() {
		return this.start;
	}

	/**
	 * Restituisce la data di fine di questa settimana.
	 * 
	 * @return La data di fine di questa settimana.
	 * @author Maurizio
	 */
	public GregorianCalendar getEnd() {
		return this.end;
	}

	/**
	 * Restituisce un ArrayList contenente tutte le settimane dell'anno passato
	 * in input.
	 * 
	 * @param year
	 *            La data (anno) di cui si vogliono ottenere le settimane.
	 * @return L'ArrayList contenente tutte le settimane dell'anno.
	 */
	public static ArrayList<Week> getAllYearWeeks(GregorianCalendar year) {
		ArrayList<Week> allWeeks = new ArrayList<>();

		GregorianCalendar firstDayOfYear = new GregorianCalendar(year.get(Calendar.YEAR), Calendar.JANUARY, 1);
		GregorianCalendar firstDayOfNextYear = (GregorianCalendar) firstDayOfYear.clone();
		firstDayOfNextYear.add(Calendar.YEAR, 1);

		while (firstDayOfYear.before(firstDayOfNextYear)) {
			GregorianCalendar weekStart = WeekBounds.getWeekStart(firstDayOfYear);
			GregorianCalendar weekEnd = WeekBounds.getWeekEnd(firstDayOfYear);

			allWeeks.add(new Week(weekStart, weekEnd));
			firstDayOfYear.add(Calendar.WEEK_OF_YEAR, 1);
		}

		return allWeeks;
	}

	/**
	 * Restituisce un ArrayList contenente tutte le settimane a partire dalla
	 * data attuale arrivando ad un anno di distanza dalla data attuale.
	 * 
	 * @return L'ArrayList delle settimane dei prossimi 365 giorni.
	 */
	public static ArrayList<Week> getNextYearWeeks() {
		ArrayList<Week> allWeeks = new ArrayList<>();

		GregorianCalendar thisWeek = new GregorianCalendar();

		GregorianCalendar thisWeekAfterAnYear = (GregorianCalendar) thisWeek.clone();
		thisWeekAfterAnYear.add(Calendar.YEAR, 1);

		while (thisWeek.before(thisWeekAfterAnYear)) {
			GregorianCalendar weekStart = WeekBounds.getWeekStart(thisWeek);
			GregorianCalendar weekEnd = WeekBounds.getWeekEnd(thisWeek);

			allWeeks.add(new Week(weekStart, weekEnd));
			thisWeek.add(Calendar.WEEK_OF_YEAR, 1);
		}

		return allWeeks;
	}

	@Override
	public String toString() {
		return DATE_FORMAT.format(start.getTime()) + " - " + DATE_FORMAT.format(end.getTime());
	}

	/**
	 * @author Maurizio
	 */
	private static final long serialVersionUID = 3468338459207003613L;
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("E dd/MM/yyyy HH:mm:ss:SS");
	private GregorianCalendar start, end;

	public static void main(String[] args) {
		for (Week w : Week.getAllYearWeeks(new GregorianCalendar())) {
			System.out.println(w.toString());
		}
	}
}
