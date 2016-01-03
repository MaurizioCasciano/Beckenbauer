package objectsTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Partita {

	public Partita(String home, String away, String stadium, GregorianCalendar date) {
		this.home = home;
		this.away = away;
		this.stadium = stadium;
		this.date = date;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getAway() {
		return away;
	}

	public void setAway(String away) {
		this.away = away;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public GregorianCalendar getDate() {
		return this.date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public String getFormattedDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(this.getDate().getTime());
	}

	public void setFormattedDate(String formattedDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.date.setTime(dateFormat.parse(formattedDate));
		} catch (ParseException e) {
			e.printStackTrace();
			// la data non viene modificata
		}
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [home=" + home + ", away=" + away + ", stadium=" + stadium
				+ ", date=" + getFormattedDate() + "]";
	}

	private String home, away, stadium;
	private GregorianCalendar date;
}
