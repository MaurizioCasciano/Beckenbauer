package testing;

import struttura.*;
import struttura.filters.MatchByWeekFilter;
import struttura.filters.MatchNotYetStartedFilter;
import user.AlreadyRegisteredUser;
import user.Gestore;
import user.UserNotFound;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import password.WeakPasswordException;

public class Testing {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		StrutturaSportiva sS = new StrutturaSportiva("My_Struttura");

		/**
		 * controlla se c'è già un gestore altrimenti lo aggiunge
		 */

		Gestore gestore;

		try {
			gestore = sS.getGestore("usernameGestore");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserNotFound e) {
			try {
				sS.addGestore(new Gestore("NomeGestore", "CognomeGestore", "usernameGestore", "P@ssw0rd"));
			} catch (WeakPasswordException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AlreadyRegisteredUser e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		Stadio stadio1 = new Stadio("Stadio1", 100);
		Stadio stadio2 = new Stadio("Stadio2", 100);
		Stadio stadio3 = new Stadio("Stadio3", 100);

		sS.addStadio(stadio1);
		sS.addStadio(stadio2);
		sS.addStadio(stadio3);

		Squadra sq1 = new Squadra("Squadra1");
		Squadra sq2 = new Squadra("Squadra2");
		Squadra sq3 = new Squadra("Squadra3");

		Partita p1 = new Partita(sq1, sq2, stadio1, new GregorianCalendar(2016, Calendar.JANUARY, 31, 20, 45));
		Partita p2 = new Partita(sq1, sq3, stadio1, new GregorianCalendar(2016, Calendar.JANUARY, 31, 20, 45));
		Partita p3 = new Partita(sq2, sq3, stadio2, new GregorianCalendar(2016, Calendar.JANUARY, 31, 20, 45));

		Partita p4 = new Partita(sq2, sq1, stadio2, new GregorianCalendar(2016, Calendar.FEBRUARY, 7, 20, 45));
		Partita p5 = new Partita(sq3, sq1, stadio3, new GregorianCalendar(2016, Calendar.FEBRUARY, 7, 20, 45));
		Partita p6 = new Partita(sq3, sq2, stadio3, new GregorianCalendar(2016, Calendar.FEBRUARY, 7, 20, 45));

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
