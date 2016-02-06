package testing;

import java.util.Calendar;
import java.util.GregorianCalendar;

import struttura.*;
import user.AlreadyRegisteredUserException;
import user.Cliente;

public class TestStruttura {

	public static void main(String[] args) throws AlreadyRegisteredUserException {
		StrutturaSportiva struct = new StrutturaSportiva("Elya's Sportive Center");
		
		System.out.println("\n" + struct.getNome() + "\n");
		
		// Stadi
		Stadio olimpico = new Stadio("Olimpico", 73261, 25.00);
		Stadio meazza = new Stadio("Giuseppe Meazza", 81277, 30.00);
		Stadio juventusStadium = new Stadio("Juventus Stadium", 41475, 35.00);
		
		struct.addStadio(olimpico);
		struct.addStadio(meazza);
		struct.addStadio(juventusStadium);
		
		//struct.addStadio(olimpico); // solleva eccezione, FUNZIONA
		
		//Squadre
		Squadra juventus = new Squadra("Juventus");
		Squadra roma = new Squadra("Roma");
		Squadra milan = new Squadra("Milan");
		
		//Partite
		Partita romaJuventus = new Partita(roma, juventus, olimpico, new GregorianCalendar(2016, Calendar.FEBRUARY, 11, 20, 30));
		Partita romaMilan = new Partita(roma, milan, olimpico, new GregorianCalendar(2016, Calendar.FEBRUARY, 16, 20, 45));
		Partita juventusMilan = new Partita(juventus, milan, juventusStadium, new GregorianCalendar(2016, Calendar.FEBRUARY, 17, 15, 30));
		
		struct.addPartita(romaJuventus);
		struct.addPartita(romaMilan);
		struct.addPartita(juventusMilan);
		
		//struct.addPartita(romaJuventus); // solleva eccezione, FUNZIONA
		
		//Utenti
		Cliente gaetano = new Cliente("Gaetano", "Antonucci", "gnoanto94", "P@ssw0rd");
		Cliente asdrubale = new Cliente("Asdrubale", "NonSense", "asdru55", "P@ssw0rd");
		Cliente mario = new Cliente("Mario", "Rossi", "mario45", "P@ssw0rd");
		
		struct.addUtente(gaetano);
		struct.addUtente(asdrubale);
		struct.addUtente(mario);
		
		//Sconti
		Sconto scontoGiovedi = new Sconto(TipoSconto.GIORNO_PRESTABILITO, 10.0, new GregorianCalendar(2016, Calendar.FEBRUARY, 8, 7, 00),  new GregorianCalendar(2016, Calendar.FEBRUARY, 21, 23, 59), DaysOfWeek.GIOVEDI);
		Sconto scontoRomaMilan = new Sconto(TipoSconto.PARTITA_CORRENTE, 15.5, new GregorianCalendar(2016, Calendar.FEBRUARY, 12, 7, 00), new GregorianCalendar(2016, Calendar.FEBRUARY, 16, 20, 30), romaMilan);
		Sconto scontoJuventusStadium = new Sconto(TipoSconto.TUTTE_LE_PARTITE_DELLO_STADIO, 12.0, new GregorianCalendar(2016, Calendar.FEBRUARY, 12, 7, 00), new GregorianCalendar(2016, Calendar.FEBRUARY, 21, 23, 59), juventusStadium);
		
		struct.addSconto(scontoGiovedi);
		struct.addSconto(scontoRomaMilan);
		struct.addSconto(scontoJuventusStadium);
		
		//struct.addSconto(scontoRomaMilan); // solleva eccezione, FUNZIONA
		
		//Settore set1 = new Settore()
		
		//Prenotazione prenotazione1 = new Prenotazione(new GreogorianCalendar(2016, Calendar.FEBRUARY, 9), struct, gaetano, romaJuventus, settore, fila, posto)

	}

}
