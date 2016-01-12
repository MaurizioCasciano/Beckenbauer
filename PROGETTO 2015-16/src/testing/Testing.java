package testing;

import struttura.*;
import struttura.filters.MatchByWeekFilter;
import struttura.filters.MatchNotYetStartedFilter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import graphics.Window;

public class Testing {

	public static void main(String[] args) {

		Window window = new Window("Struttura Sportiva");

		StrutturaSportiva strutturaSportiva = window.getStrutturaSportiva();

		Stadio sanSiro = new Stadio("San Siro", 81277);
		Stadio stadioOlimpico = new Stadio("Stadio Olimpico", 73261);
		Stadio juventusStadium = new Stadio("Juventus Stadium", 41475);

		strutturaSportiva.addStadio(sanSiro);
		strutturaSportiva.addStadio(stadioOlimpico);
		strutturaSportiva.addStadio(juventusStadium);

		Squadra milan = new Squadra("Milan");
		Squadra inter = new Squadra("Inter");
		Squadra roma = new Squadra("Roma");
		Squadra lazio = new Squadra("Lazio");
		Squadra juventus = new Squadra("Juventus");
		Squadra torino = new Squadra("Torino");

		Partita p1 = new Partita(milan, inter, sanSiro, new GregorianCalendar(2016, Calendar.JANUARY, 31, 20, 45));
		Partita p2 = new Partita(roma, lazio, stadioOlimpico,
				new GregorianCalendar(2016, Calendar.JANUARY, 31, 20, 45));
		Partita p3 = new Partita(juventus, torino, juventusStadium,
				new GregorianCalendar(2016, Calendar.JANUARY, 31, 20, 45));

		Partita p4 = new Partita(inter, milan, sanSiro, new GregorianCalendar(2016, Calendar.FEBRUARY, 7, 20, 45));
		Partita p5 = new Partita(lazio, roma, stadioOlimpico,
				new GregorianCalendar(2016, Calendar.FEBRUARY, 7, 20, 45));
		Partita p6 = new Partita(torino, juventus, juventusStadium,
				new GregorianCalendar(2016, Calendar.FEBRUARY, 7, 20, 45));

		strutturaSportiva.addPartita(p1);
		strutturaSportiva.addPartita(p2);
		strutturaSportiva.addPartita(p3);
		strutturaSportiva.addPartita(p4);
		strutturaSportiva.addPartita(p5);
		strutturaSportiva.addPartita(p6);

		// partite non ancora iniziate
		System.out.println("PARTITE NON ANCORA INIZIATE:\n");
		for (Partita p : strutturaSportiva.getPartiteProgrammate(new MatchNotYetStartedFilter())) {
			System.out.println(p);
		}

		// partite programmate in una determinata settimana
		System.out.println("\nPARTITE PROGRAMMATE IN UNA DETERMINATA SETTIMANA:\n");
		for (Partita p : strutturaSportiva
				.getPartiteProgrammate(new MatchByWeekFilter(new GregorianCalendar(2016, Calendar.JANUARY, 25)))) {
			System.out.println(p);
		}
	}
}
