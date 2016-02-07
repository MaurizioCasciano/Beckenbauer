package testing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import struttura.*;
import struttura.filters.PrenotationByCustomerFilter;
import struttura.filters.PrenotationByMatchFilter;
import user.AlreadyRegisteredUserException;
import user.Cliente;

public class TestStruttura {

	public static void main(String[] args) throws AlreadyRegisteredUserException {
		StrutturaSportiva struct = new StrutturaSportiva("Elya's Sportive Center");
		
		System.out.println("\n" + struct.getNome() + "\n");
		
		// Stadi
		long start = System.currentTimeMillis();
		
		Stadio olimpico = new Stadio("Olimpico", 73261, 25.00);
		Stadio meazza = new Stadio("Giuseppe Meazza", 81277, 30.00);
		Stadio juventusStadium = new Stadio("Juventus Stadium", 41475, 35.00);
		
		long end = System.currentTimeMillis();
		
		long diff = end - start;
		System.out.println("Tempo Impiegato Stadi: " + (diff / 1000.0));
		
		struct.addStadio(olimpico);
		struct.addStadio(meazza);
		struct.addStadio(juventusStadium);
		
		//struct.addStadio(olimpico); // solleva eccezione, FUNZIONA
		
		//Squadre
		Squadra juventus = new Squadra("Juventus");
		Squadra roma = new Squadra("Roma");
		Squadra milan = new Squadra("Milan");
		Squadra lazio = new Squadra("Lazio");
		
		//Partite
		Partita romaJuventus = new Partita(roma, juventus, olimpico, new GregorianCalendar(2016, Calendar.FEBRUARY, 11, 20, 30));
		Partita romaMilan = new Partita(roma, milan, olimpico, new GregorianCalendar(2016, Calendar.FEBRUARY, 16, 20, 45));
		Partita juventusMilan = new Partita(juventus, milan, juventusStadium, new GregorianCalendar(2016, Calendar.FEBRUARY, 17, 15, 30));
		Partita milanLazio = new Partita(milan, lazio, meazza, new GregorianCalendar(2016, Calendar.FEBRUARY, 19, 20, 45));
		
		struct.addPartita(romaJuventus);
		struct.addPartita(romaMilan);
		struct.addPartita(juventusMilan);
		struct.addPartita(milanLazio);
		
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
		
		//Settore settoreOlimpico = olimpico.getCopyOfSettori().get(0);
		Settore settoreMeazza = meazza.getCopyOfSettori().get(0);
		//Settore settoreJuventusStadium = juventusStadium.getCopyOfSettori().get(0);
		
	/*	Prenotazione prenotazione1 = new Prenotazione(new GregorianCalendar(2016, Calendar.FEBRUARY, 9), struct, gaetano, romaJuventus, settoreOlimpico, 1, 1);
		Prenotazione prenotazione2 = new Prenotazione(new GregorianCalendar(2016, Calendar.FEBRUARY, 10), struct, asdrubale, romaMilan, settoreOlimpico, 1, 3);
		Prenotazione prenotazione3 = new Prenotazione(new GregorianCalendar(2016, Calendar.FEBRUARY, 12), struct, mario, juventusMilan, settoreJuventusStadium, 1, 2);
		
		struct.addPrenotazione(prenotazione1);
		struct.addPrenotazione(prenotazione2);
		struct.addPrenotazione(prenotazione3);
		
		System.out.println("Prenotazioni Presenti: " + struct.getPrenotazioni().size() + " [Exp: 3]"); */
		
		//struct.addPrenotazione(prenotazione3); // solleva eccezione, FUNZIONA
		
		//Acquisto acquisto1 = new Acquisto(prenotazione2, struct);
		Acquisto acquisto2 = new Acquisto(mario, milanLazio, settoreMeazza, 1, 5, struct);
		
		//struct.addAcquisto(acquisto1);
		struct.addAcquisto(acquisto2);
		
		//struct.addAcquisto(acquisto2); // solleva eccezione
		
		System.out.println("Partite Presenti: " + struct.getPartiteProgrammate().size() + " [Exp: 4]");
		System.out.println("Stadi Presenti: " + struct.getStadi().size() + " [Exp: 3]");
		System.out.println("Sconti Presenti: " + struct.getSconti().size() + " [Exp: 3]");
		System.out.println("Prenotazioni Presenti: " + struct.getPrenotazioni().size() + " [Exp: 2]");
		System.out.println("Acquisti Presenti: " + struct.getAcquisti().size() + " [Exp: 2]");
		
		ArrayList<Prenotazione> prenotazioniGaetano = struct.getPrenotazioniFiltrate(new PrenotationByCustomerFilter(gaetano));
		
		System.out.println("Prenotazioni di gaetano presenti: " + prenotazioniGaetano.size() + " [Exp: 1]");
		
		for(Prenotazione p: prenotazioniGaetano){
			System.out.println(p);
		}
		
		ArrayList<Prenotazione> prenotazioniMario = struct.getPrenotazioniFiltrate(new PrenotationByCustomerFilter(mario));
		
		System.out.println("Prenotazioni di mario presenti: " + prenotazioniMario.size() + " [Exp: 1]");
		
		for(Prenotazione p: prenotazioniMario){
			System.out.println(p);
		}
		
		ArrayList<Prenotazione> prenotazioniRomaJuventus = struct.getPrenotazioniFiltrate(new PrenotationByMatchFilter(romaJuventus));
		
		System.out.println("Prenotazioni per la partita Roma - Juventus presenti: " + prenotazioniRomaJuventus.size() + " [Exp: 1]");
		
		for(Prenotazione p: prenotazioniRomaJuventus){
			System.out.println(p.getBigliettoPrenotato().getPartita());
		}
		
		ArrayList<Prenotazione> prenotazioniJuventusMilan = struct.getPrenotazioniFiltrate(new PrenotationByMatchFilter(juventusMilan));
		
		System.out.println("Prenotazioni per la partita Juventus - Milan presenti: " + prenotazioniJuventusMilan.size() + " [Exp: 1]");
		
		for(Prenotazione p: prenotazioniJuventusMilan){
			System.out.println(p.getBigliettoPrenotato().getPartita());
		}
		
		
		
		
		
		

	}

}
