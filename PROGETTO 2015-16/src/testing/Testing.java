package testing;

import struttura.*;
import user.AlreadyRegisteredUser;
import user.Gestore;
import user.UserNotFound;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import password.WeakPasswordException;

public class Testing {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		/*
		 * GregorianCalendar gC = new GregorianCalendar();
		 * gC.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
		 * gC.setFirstDayOfWeek(Calendar.MONDAY);
		 * 
		 * System.out.println("DayOfYear: " + gC.get(Calendar.DAY_OF_YEAR));
		 * System.out.println("DayOfMonth: " + gC.get(Calendar.DAY_OF_MONTH));
		 * System.out.println("DayOfWeek: " + gC.get(Calendar.DAY_OF_WEEK));
		 * System.out.println("Month: " + (gC.get(Calendar.MONTH) + 1));
		 * System.out.println("Year: " + gC.get(Calendar.YEAR));
		 * 
		 * System.out.println("WeekOfYear: " + gC.get(Calendar.WEEK_OF_YEAR));
		 * 
		 * String str =
		 * DateFormat.getDateInstance(DateFormat.SHORT).format(gC.getTime());
		 * System.out.println(str);
		 */

		StrutturaSportiva sS = new StrutturaSportiva("MY_Struttura");

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

		Stadio s1 = new Stadio("Stadio1", 100);
		Stadio s2 = new Stadio("Stadio2", 100);
		Stadio s3 = new Stadio("Stadio3", 100);

		sS.addStadio(s1);
		sS.addStadio(s2);
		sS.addStadio(s3);

		Squadra sq1 = new Squadra("Squadra1");
		Squadra sq2 = new Squadra("Squadra2");
		Squadra sq3 = new Squadra("Squadra3");

		PartitaDiCalcio p1 = new PartitaDiCalcio(sq1, sq2, s3, new GregorianCalendar(2015, 11, 30));
		PartitaDiCalcio p2 = new PartitaDiCalcio(sq1, sq3, s2, new GregorianCalendar(2015, 11, 20));
		PartitaDiCalcio p3 = new PartitaDiCalcio(sq2, sq3, s1, new GregorianCalendar(2015, 11, 29));

		sS.addPartita(p1);
		sS.addPartita(p2);
		sS.addPartita(p3);

		/*
		 * tutte le partite programmate in una determinata settimana.
		 */
		ArrayList<PartitaDiCalcio> byWeek = sS.getPartiteProgrammate(new MatchFilter(),
				new GregorianCalendar(2015, 11, 1));
		/*
		 * tutte le partite che si svolgono in un determinato stadio.
		 */
		ArrayList<PartitaDiCalcio> byStadium = sS.getPartiteProgrammate(new MatchFilter(), s2);

		// tutte le partite non ancora iniziate, in ordina cronologico.
		ArrayList<PartitaDiCalcio> stillToPlayByDate = sS.getPartiteProgrammate(new DateComparator());

		/*
		 * tutte le partite non ancora iniziate, in ordina crescente rispetto
		 * all'ID dello stadio.
		 */
		ArrayList<PartitaDiCalcio> stillToPlayByStadiumID = sS.getPartiteProgrammate(new StadiumIDComparator());

		// tutte le partite non ancora iniziate, in ordine lessicografico
		// crescente rispetto al nome delle squadre che si affrontano;
		ArrayList<PartitaDiCalcio> stillToPlayByName = sS.getPartiteProgrammate(new NameComparator());

		for (PartitaDiCalcio p : stillToPlayByDate) {
			// System.out.println(p.toString());
		}
	}
}
