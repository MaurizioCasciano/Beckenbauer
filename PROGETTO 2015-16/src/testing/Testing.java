package testing;

import struttura.*;
import struttura.filters.MatchByWeekFilter;
import struttura.filters.MatchNotYetStartedFilter;
import user.AlreadyRegisteredUserException;
import user.Gestore;
import user.UserNotFound;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import graphics.Window;
import password.WeakPasswordException;

public class Testing {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		StrutturaSportiva sS = new StrutturaSportiva("My_Struttura");

		new Window("Fuck");
		/**
		 * controlla se c'è già un gestore altrimenti lo aggiunge
		 */

		Gestore gestore;

		try {
			gestore = (Gestore) sS.getUtente("usernameGestore");
		} catch (UserNotFound e) {
			try {
				sS.addUtente(new Gestore("NomeGestore", "CognomeGestore", "usernameGestore", "P@ssw0rd"));
			} catch (WeakPasswordException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AlreadyRegisteredUserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		Stadio sanSiro = new Stadio("San Siro", 81277);
		Stadio stadioOlimpico = new Stadio("Stadio Olimpico", 73261);
		Stadio juventusStadium = new Stadio("Juventus Stadium", 41475);

		sS.addStadio(sanSiro);
		sS.addStadio(stadioOlimpico);
		sS.addStadio(juventusStadium);

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

		sS.addPartita(p1);
		sS.addPartita(p2);
		sS.addPartita(p3);
		sS.addPartita(p4);
		sS.addPartita(p5);
		sS.addPartita(p6);

		// partite non ancora iniziate
		System.out.println("PARTITE NON ANCORA INIZIATE:\n");
		for (Partita p : sS.getPartiteProgrammate(new MatchNotYetStartedFilter())) {
			System.out.println(p);
		}

		// partite programmate in una determinata settimana
		System.out.println("\nPARTITE PROGRAMMATE IN UNA DETERMINATA SETTIMANA:\n");
		for (Partita p : sS
				.getPartiteProgrammate(new MatchByWeekFilter(new GregorianCalendar(2016, Calendar.JANUARY, 25)))) {
			System.out.println(p);
		}
	}
}
