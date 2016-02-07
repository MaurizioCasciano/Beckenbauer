package testing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

import struttura.*;
import struttura.filters.*;
import user.AlreadyRegisteredUserException;
import user.Cliente;
import graphics.sconti.*;
import graphics.incasso.VisualizzaIncassoPanel;

@SuppressWarnings("unused")
public class TestGaetano {

	public static void main(String[] args) throws AlreadyRegisteredUserException {

		/**** Struttura Sportiva ****/
		StrutturaSportiva struct = new StrutturaSportiva("Elyas's Match");
		System.out.println("***************************************************");
		System.out.println("******************* " + struct.getNome() + " *****************");
		System.out.println("***************************************************");

		/**** Stadi ****/
		Stadio olimpico = new Stadio("Olimpico", 50000, 20.00);
		struct.addStadio(olimpico);

		Stadio sanSiro = new Stadio("San Siro", 70000, 25.00);
		struct.addStadio(sanSiro);

		Stadio arechi = new Stadio("Arechi", 31300, 10.00);
		struct.addStadio(arechi);

		/**** Squadre ****/
		Squadra roma = new Squadra("Roma");
		Squadra juventus = new Squadra("Juventus");
		Squadra salernitana = new Squadra("Salernitana");
		Squadra avellinese = new Squadra("Avellinese");

		/**** Partite ****/
		Partita romaJuventus = new Partita(roma, juventus, olimpico,
				new GregorianCalendar(2016, Calendar.AUGUST, 19, 19, 00));
		struct.addPartita(romaJuventus);

		Partita juventusRoma = new Partita(juventus, roma, sanSiro,
				new GregorianCalendar(2016, Calendar.AUGUST, 17, 20, 0));
		struct.addPartita(juventusRoma);

		Partita salernitanaAvellinese = new Partita(salernitana, avellinese, arechi,
				new GregorianCalendar(2016, Calendar.AUGUST, 18, 17, 0));
		struct.addPartita(salernitanaAvellinese);

		/**** Sconti ****/
		Sconto scontoOlimpico = new Sconto(TipoSconto.TUTTE_LE_PARTITE_DELLO_STADIO, 10,
				new GregorianCalendar(2016, Calendar.AUGUST, 18, 0, 0), new GregorianCalendar(2016, Calendar.AUGUST, 24, 23, 59),
				olimpico);
		struct.addSconto(scontoOlimpico);

		Sconto scontoRomaJuventus = new Sconto(TipoSconto.PARTITA_CORRENTE, 15,
				new GregorianCalendar(2016, Calendar.AUGUST, 10, 0, 0), new GregorianCalendar(2016, Calendar.AUGUST, 19),
				romaJuventus);
		struct.addSconto(scontoRomaJuventus);

		Sconto scontoMercoledi = new Sconto(TipoSconto.GIORNO_PRESTABILITO, 5,
				new GregorianCalendar(2016, Calendar.AUGUST, 16), new GregorianCalendar(2016, Calendar.AUGUST, 24),
				DaysOfWeek.MERCOLEDI);
		struct.addSconto(scontoMercoledi);

		Sconto scontoGiovedi = new Sconto(TipoSconto.GIORNO_PRESTABILITO, 3,
				new GregorianCalendar(2016, Calendar.AUGUST, 12), new GregorianCalendar(2016, Calendar.AUGUST, 24),
				DaysOfWeek.GIOVEDI);
		struct.addSconto(scontoGiovedi);

		Sconto scontoSalernitanaAvellinese = new Sconto(TipoSconto.PARTITA_CORRENTE, 6,
				new GregorianCalendar(2016, Calendar.AUGUST, 15), new GregorianCalendar(2016, Calendar.AUGUST, 18),
				salernitanaAvellinese);
		struct.addSconto(scontoSalernitanaAvellinese);

		Sconto scontoArechi = new Sconto(TipoSconto.TUTTE_LE_PARTITE_DELLO_STADIO, 4.5,
				new GregorianCalendar(2016, Calendar.AUGUST, 16), new GregorianCalendar(2016, Calendar.AUGUST, 24),
				arechi);
		struct.addSconto(scontoArechi);

		/*** Clienti ****/
		Cliente gaetano = new Cliente("Gaetano", "Antonucci", "gnoanto94", "P@ssw0rd");
		struct.addUtente(gaetano);
		Cliente giovanni = new Cliente("Giovanni", "Bianchi", "giovanni55", "P@ssw0rd");
		struct.addUtente(giovanni);
		Cliente mario = new Cliente("Mario", "Rossi", "mario45", "P@ssw0rd");
		struct.addUtente(mario);

		/**** Settori degli Stadi ****/
		Settore tribunaOlimpico = new Settore(olimpico, "Tribuna", olimpico.getPostiPerSettore(),
				olimpico.getNumeroFilePerSettore());
		Settore curvaSanSiro = new Settore(sanSiro, "Curva", sanSiro.getPostiPerSettore(),
				sanSiro.getNumeroFilePerSettore());
		Settore curvaSudArechi = new Settore(arechi, "Curva Sud", arechi.getPostiPerSettore(),
				arechi.getNumeroFilePerSettore());

		/**** Prenotazioni ****/

		Prenotazione prenRJGaetano = new Prenotazione(struct, gaetano, romaJuventus, tribunaOlimpico, 1, 3);
		prenRJGaetano.getBigliettoPrenotato().setDataBiglietto(new GregorianCalendar(2016, Calendar.AUGUST, 16));
		struct.addPrenotazione(prenRJGaetano);

		Prenotazione prenRJGiovanni = new Prenotazione(struct, giovanni, romaJuventus, tribunaOlimpico, 1, 4);
		prenRJGiovanni.getBigliettoPrenotato().setDataBiglietto(new GregorianCalendar(2016, Calendar.AUGUST, 17));
		struct.addPrenotazione(prenRJGiovanni);

		Prenotazione prenRJMario = new Prenotazione(struct, mario, romaJuventus, tribunaOlimpico, 1, 2);
		prenRJMario.getBigliettoPrenotato().setDataBiglietto(new GregorianCalendar(2016, Calendar.AUGUST, 18, 16, 0));
		struct.addPrenotazione(prenRJMario);

		Prenotazione prenJRGaetano = new Prenotazione(struct, gaetano, juventusRoma, curvaSanSiro, 1, 3);
		prenJRGaetano.getBigliettoPrenotato().setDataBiglietto(new GregorianCalendar(2016, Calendar.AUGUST, 18));
		struct.addPrenotazione(prenJRGaetano);

		Prenotazione prenJRMario = new Prenotazione(struct, mario, juventusRoma, curvaSanSiro, 1, 2);
		prenJRMario.getBigliettoPrenotato().setDataBiglietto(new GregorianCalendar(2016, Calendar.AUGUST, 18));
		struct.addPrenotazione(prenJRMario);

		// Salernitana - Avellinese
		// non ci sono prenotazioni

		
		// Verifica su numero prenotazioni
		System.out.println("\n Prenotazioni Presenti: " + struct.getPrenotazioni().size() + " [Expected: 5]");
		
		/**** Verifica Filtri Prenotazioni ****/
		ArrayList<Prenotazione> perRomaJuventus = struct
				.getPrenotazioniFiltrate(new PrenotationByMatchFilter(romaJuventus));

		System.out.println("\n Prenotazioni Partita : " + romaJuventus.getSquadraInCasa().getNome() + " - " + romaJuventus.getSquadraInTrasferta().getNome() + " \n");
		for (Prenotazione pren : perRomaJuventus) {
			System.out.println(" " + pren);
			System.out.println(" " +pren.getBigliettoPrenotato().getPartita().getSquadraInCasa().getNome() + " - " + pren.getBigliettoPrenotato().getPartita().getSquadraInTrasferta().getNome());
			System.out.println(" Prezzo: " + String.format(" %.2f",pren.getBigliettoPrenotato().getPrezzo()) + " [Exp: 17.00]");
			System.out.println(" --------------------");
		}

		System.out.println("END \n");

		System.out.println("\n Prenotazioni Partita : " + juventusRoma.getSquadraInCasa().getNome() + " - " + juventusRoma.getSquadraInTrasferta().getNome() + " \n");
		ArrayList<Prenotazione> perJuventusRoma = struct
				.getPrenotazioniFiltrate(new PrenotationByMatchFilter(juventusRoma));

		for (Prenotazione pren : perJuventusRoma) {
			System.out.println(" " + pren);
			System.out.println(" " + pren.getBigliettoPrenotato().getPartita().getSquadraInCasa().getNome() + " - " + pren.getBigliettoPrenotato().getPartita().getSquadraInTrasferta().getNome());
			System.out.println(" Prezzo: " + String.format(" %.2f",pren.getBigliettoPrenotato().getPrezzo()) + " [Exp: 23.75]");
			System.out.println(" --------------------");
		}

		System.out.println("END \n");

		// non ci sono prenotazioni quindi non stampa nulla
		System.out.println("\n Prenotazioni Partita : " + salernitanaAvellinese.getSquadraInCasa().getNome() + " - " + salernitanaAvellinese.getSquadraInTrasferta().getNome() + " \n");
		ArrayList<Prenotazione> perSalernitanaAvellinese = struct
				.getPrenotazioniFiltrate(new PrenotationByMatchFilter(salernitanaAvellinese));

		for (Prenotazione pren : perSalernitanaAvellinese) {
			System.out.println(" " + pren);
			System.out.println(" " + pren.getBigliettoPrenotato().getPartita().getSquadraInCasa().getNome() + " - " + pren.getBigliettoPrenotato().getPartita().getSquadraInTrasferta().getNome());
			System.out.println(" Prezzo: " + String.format(" %.2f",pren.getBigliettoPrenotato().getPrezzo()) + " [Exp: 9.40]");
			System.out.println(" ---------------------");
		}

		System.out.println("END \n");

		System.out.println("\n Prenotazioni Cliente : " + gaetano.getCognome() + " " + gaetano.getNome() + " \n");
		ArrayList<Prenotazione> perGaetano = struct.getPrenotazioniFiltrate(new PrenotationByCustomerFilter(gaetano));

		for (Prenotazione pren : perGaetano) {
			System.out.println(pren);
			System.out.println(" Cliente: " + pren.getBigliettoPrenotato().getCliente().getCognome() + " " + pren.getBigliettoPrenotato().getCliente().getNome());
			System.out.println(" " + pren.getBigliettoPrenotato().getPartita().getSquadraInCasa().getNome() + " - " + pren.getBigliettoPrenotato().getPartita().getSquadraInTrasferta().getNome());
			System.out.println(" Prezzo: " +  String.format(" %.2f",pren.getBigliettoPrenotato().getPrezzo()));
			System.out.println(" ---------------------");
		}

		System.out.println("END \n");

		System.out.println("\n Prenotazioni Cliente : " + mario.getCognome() + " " + mario.getNome() + " \n");
		ArrayList<Prenotazione> perMario = struct.getPrenotazioniFiltrate(new PrenotationByCustomerFilter(mario));

		for (Prenotazione pren : perMario) {
			System.out.println(pren);
			System.out.println(" Cliente: " + pren.getBigliettoPrenotato().getCliente().getCognome() + " " + pren.getBigliettoPrenotato().getCliente().getNome());
			System.out.println(" " + pren.getBigliettoPrenotato().getPartita().getSquadraInCasa().getNome() + " - " + pren.getBigliettoPrenotato().getPartita().getSquadraInTrasferta().getNome());
			System.out.println(" Prezzo: " +  String.format(" %.2f",pren.getBigliettoPrenotato().getPrezzo()));
			System.out.println(" ---------------------");
		}

		System.out.println("END \n");

		System.out.println("\n Prenotazioni Stadio : " + olimpico.getNome() + " \n");
		ArrayList<Prenotazione> perOlimpico = struct.getPrenotazioniFiltrate(new PrenotationByStadiumFilter(olimpico));

		for (Prenotazione pren : perOlimpico) {
			System.out.println(pren);
			System.out.println(" Stadio: " + pren.getBigliettoPrenotato().getPartita().getStadio().getNome());
			System.out.println(" " + pren.getBigliettoPrenotato().getPartita().getSquadraInCasa().getNome() + " - " + pren.getBigliettoPrenotato().getPartita().getSquadraInTrasferta().getNome());
			System.out.println(" Prezzo: " + String.format(" %.2f",pren.getBigliettoPrenotato().getPrezzo()));
			System.out.println(" -------------------");
		}

		System.out.println("END \n");

		System.out.println("\n Prenotazioni Stadio : " + sanSiro.getNome() + " \n");
		ArrayList<Prenotazione> perSanSiro = struct.getPrenotazioniFiltrate(new PrenotationByStadiumFilter(sanSiro));

		for (Prenotazione pren : perSanSiro) {
			System.out.println(pren);
			System.out.println(" Stadio: " + pren.getBigliettoPrenotato().getPartita().getStadio().getNome());
			System.out.println(" " + pren.getBigliettoPrenotato().getPartita().getSquadraInCasa().getNome() + " - " + pren.getBigliettoPrenotato().getPartita().getSquadraInTrasferta().getNome());
			System.out.println(" Prezzo: " + String.format(" %.2f",pren.getBigliettoPrenotato().getPrezzo()));
			System.out.println(" -------------------");
		}

		System.out.println("END \n");

		/**** Acquisti ****/

		// Roma - Juventus (da prenotazione)
		Acquisto acqRJGaetano = new Acquisto(prenRJGaetano, struct);
		struct.addAcquisto(acqRJGaetano);
		
		Acquisto acqRJAsdrubale = new Acquisto(prenRJGiovanni, struct);
		struct.addAcquisto(acqRJAsdrubale);

		// Juventus - Roma (da prenotazione)
		Acquisto acqJRGaetano = new Acquisto(prenJRGaetano, struct);
		struct.addAcquisto(acqJRGaetano);
		
		Acquisto acqJRMario = new Acquisto(prenJRMario, struct);
		struct.addAcquisto(acqJRMario);

		// Salernitana - Avellinese (senza prenotazione)
		Acquisto acqSAMario = new Acquisto(mario, salernitanaAvellinese, curvaSudArechi, 1, 3, struct);
		acqSAMario.getBiglietto().setDataBiglietto(new GregorianCalendar(2016, Calendar.AUGUST, 16, 17, 0));
		struct.addAcquisto(acqSAMario);
		
		//System.out.println("Prezzo S - A: " + String.format(" %.2f", acqSAMario.getBiglietto().getPrezzo()) + " [Exp: 9.40]");
		
		Acquisto acqSAAsdrubale = new Acquisto(giovanni, salernitanaAvellinese, curvaSudArechi, 1, 4, struct);
		acqSAAsdrubale.getBiglietto().setDataBiglietto(new GregorianCalendar(2016, Calendar.AUGUST, 15, 14, 0));
		struct.addAcquisto(acqSAAsdrubale);

		/**** Verifica Cancellazione Prenotazioni ****/

		System.out.println("\n Prenotazioni presenti: " + struct.getPrenotazioni().size() + " [Expected: 1] \n");

		for (Prenotazione pren : struct.getPrenotazioni()) { // do nothing
			System.out.println(pren);
		}

		/**** Verifica Prenotazioni Scadute ****/
		System.out.println(" Verifica su prenotazioni scadute ... \n");
		// Le prenotazioni per la partita RomaJuventus scadono alle ore 07:00
		// del 19/01/2016
		// quindi l'ultima prenotazione rimasta deve essere cancellata.

		System.out.println(" Prenotazioni presenti:  \n");
		for (Prenotazione pren : struct.getPrenotazioni()) {
			System.out.println(" IDBiglietto: " + pren.getBigliettoPrenotato().getIDBiglietto() + "\n Cliente: " 
								+ pren.getBigliettoPrenotato().getCliente().getCognome() + 
								" " + pren.getBigliettoPrenotato().getCliente().getNome());
		}

		GregorianCalendar dataControllo = new GregorianCalendar(2016, Calendar.AUGUST, 19, 7, 0, 0);
		dataControllo.set(Calendar.MILLISECOND, 1);
		TestGaetano.cancellaPrenotazioniScadute(struct, dataControllo);

		System.out.println("\n Prenotazioni dopo cancellaPrenotazioniScadute: \n");
		for (Prenotazione pren : struct.getPrenotazioni()) {
			System.out.println(" IDBiglietto: " + pren.getBigliettoPrenotato().getIDBiglietto()+ "\n Cliente:  " 
								+ pren.getBigliettoPrenotato().getCliente().getCognome() + 
								" " + pren.getBigliettoPrenotato().getCliente().getNome());
		}

		System.out.println("\n Prenotazioni presenti dopo verifica: " + struct.getPrenotazioni().size() + " [Expected: 0] \n");

		/**** Verifica Numero Acquisti ****/
		System.out.println("\n Acquisti Presenti: " + struct.getAcquisti().size() + " [Expected: 6] \n");

		for (Acquisto acq : struct.getAcquisti()) {
			System.out.println(
					" Partita:" + acq.getBiglietto().getPartita().getSquadraInCasa().getNome() +
					" - " + acq.getBiglietto().getPartita().getSquadraInTrasferta().getNome()
					+ "\n Prezzo: " + String.format(" %.2f",acq.getBiglietto().getPrezzo()));
		}

		/**** Verifica Filtri Acquisti ****/
		System.out.println("\n Acquisti per Stadio: " + olimpico.getNome() + "\n");

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchaseByStadiumFilter(olimpico))) {
			System.out.println(" IDBiglietto: " + acq.getBiglietto().getIDBiglietto() + " \n Partita: " +
								acq.getBiglietto().getPartita().getSquadraInCasa().getNome() + " - " +
								acq.getBiglietto().getPartita().getSquadraInTrasferta().getNome() + "\n Stadio: " +
								acq.getBiglietto().getPartita().getStadio().getNome());
			System.out.println("---------------------------");
		}

		System.out.println("END");

		System.out.println("\nAcquisti per Stadio: " + sanSiro.getNome());

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchaseByStadiumFilter(sanSiro))) {
			System.out.println(" IDBiglietto: " + acq.getBiglietto().getIDBiglietto() + " \n Partita: " +
					acq.getBiglietto().getPartita().getSquadraInCasa().getNome() + " - " +
					acq.getBiglietto().getPartita().getSquadraInTrasferta().getNome() + "\n Stadio: " +
					acq.getBiglietto().getPartita().getStadio().getNome());
			System.out.println("---------------------------");
		}

		System.out.println("END");

		System.out.println("\nAcquisti per Stadio: " + arechi.getNome());

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchaseByStadiumFilter(arechi))) {
			System.out.println(" IDBiglietto: " + acq.getBiglietto().getIDBiglietto() + " \n Partita: " +
					acq.getBiglietto().getPartita().getSquadraInCasa().getNome() + " - " +
					acq.getBiglietto().getPartita().getSquadraInTrasferta().getNome() + "\n Stadio: " +
					acq.getBiglietto().getPartita().getStadio().getNome());
			System.out.println("---------------------------");
		}

		System.out.println("END");

		System.out.println("\nAcquisti per Partita: " + romaJuventus.getSquadraInCasa().getNome() + " - " +
							romaJuventus.getSquadraInTrasferta().getNome());

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchaseByMatchFilter(romaJuventus))) {
			System.out.println(" IDBiglietto: " + acq.getBiglietto().getIDBiglietto() + " \n Partita: " +
					acq.getBiglietto().getPartita().getSquadraInCasa().getNome() + " - " +
					acq.getBiglietto().getPartita().getSquadraInTrasferta().getNome());
			System.out.println("----------------------------");
		}

		System.out.println("END");

		System.out.println("\nAcquisti per Partita: " + juventusRoma.getSquadraInCasa().getNome() + " - " +
							juventusRoma.getSquadraInTrasferta().getNome());

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchaseByMatchFilter(juventusRoma))) {
			System.out.println(" IDBiglietto: " + acq.getBiglietto().getIDBiglietto() + " \n Partita: " +
					acq.getBiglietto().getPartita().getSquadraInCasa().getNome() + " - " +
					acq.getBiglietto().getPartita().getSquadraInTrasferta().getNome());
			System.out.println("----------------------------");
		}

		System.out.println("END");

		System.out.println("\nAcquisti per Partita: " + salernitanaAvellinese.getSquadraInCasa().getNome() + " - " +
							salernitanaAvellinese.getSquadraInTrasferta().getNome());

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchaseByMatchFilter(salernitanaAvellinese))) {
			System.out.println(" IDBiglietto: " + acq.getBiglietto().getIDBiglietto() + " \n Partita: " +
					acq.getBiglietto().getPartita().getSquadraInCasa().getNome() + " - " +
					acq.getBiglietto().getPartita().getSquadraInTrasferta().getNome());
			System.out.println("----------------------------");
		}

		System.out.println("END");

		System.out.println("\n Acquisti per Cliente: " + gaetano.getCognome() + " " + gaetano.getNome());

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchaseByCustomerFilter(gaetano))) {
			System.out.println(" IDBiglietto: " + acq.getBiglietto().getIDBiglietto() + " \n Partita: " + 
								acq.getBiglietto().getPartita().getSquadraInCasa().getNome() + " - " +
								acq.getBiglietto().getPartita().getSquadraInTrasferta().getNome() + 
								" \n Cliente: " + acq.getBiglietto().getCliente().getCognome() + " " +
								acq.getBiglietto().getCliente().getNome());
			System.out.println("----------------------------");
		}

		System.out.println("END");

		System.out.println("\n Acquisti per Cliente: " + giovanni.getCognome() + " " + giovanni.getNome());

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchaseByCustomerFilter(giovanni))) {
			System.out.println(" IDBiglietto: " + acq.getBiglietto().getIDBiglietto() + " \n Partita: " + 
					acq.getBiglietto().getPartita().getSquadraInCasa().getNome() + " - " +
					acq.getBiglietto().getPartita().getSquadraInTrasferta().getNome() + 
					" \n Cliente: " + acq.getBiglietto().getCliente().getCognome() + " " +
					acq.getBiglietto().getCliente().getNome());
			System.out.println("----------------------------");
		}

		System.out.println("END");

		System.out.println("\n Acquisti per Cliente: " + mario.getCognome() + " " + mario.getNome());

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchaseByCustomerFilter(mario))) {
			System.out.println(" IDBiglietto: " + acq.getBiglietto().getIDBiglietto() + " \n Partita: " + 
					acq.getBiglietto().getPartita().getSquadraInCasa().getNome() + " - " +
					acq.getBiglietto().getPartita().getSquadraInTrasferta().getNome() + 
					" \n Cliente: " + acq.getBiglietto().getCliente().getCognome() + " " +
					acq.getBiglietto().getCliente().getNome());
			System.out.println("----------------------------");
		}

		System.out.println("END");

		/***** Incassi *****/
		System.out.println("\nIncasso totale della Struttura " + struct.getNome() + " : "
				+ String.format(" %.2f",struct.calcolaIncasso(struct.getAcquisti())) + " [Exp: 100.30]");

		System.out.println("\n Incasso per stadio: " + olimpico);
		System.out
				.println(" " + String.format(" %.2f",struct.calcolaIncasso(struct.getAcquistiFiltrati(new PurchaseByStadiumFilter(olimpico))))
						+ " [Exp: 34.00]");

		System.out.println("\n Incasso per stadio: " + sanSiro);
		System.out.println(" " + String.format(" %.2f",struct.calcolaIncasso(struct.getAcquistiFiltrati(new PurchaseByStadiumFilter(sanSiro))))
				+ " [Exp: 47.50]");

		System.out.println("\n Incasso per stadio: " + arechi);
		System.out.println(" " + String.format(" %.2f",struct.calcolaIncasso(struct.getAcquistiFiltrati(new PurchaseByStadiumFilter(arechi))))
				+ " [Exp: 18.80]");

		/******* Grafica Sconti *******/
		// Partita prova = new Partita(roma, juventus, olimpico, new
		// GregorianCalendar(2016, Calendar.JANUARY, 23, 20, 45));
		// struct.addPartita(prova);
		// JFrame frameScontiPartita = new ScontoPartitaFrame(struct);
		// JFrame frameScontiStadio = new ScontoStadioFrame(struct);
		// JFrame frameScontiGiorno = new ScontoGiornoFrame(struct);

		/******* Modifica Dati Stadi ******/
		// JFrame frameStadi = new ModificaDatiStadioFrame(struct);

		/*
		 * Partita prova = new Partita(juventus, roma, olimpico, new
		 * GregorianCalendar()); Acquisto acqProva = new Acquisto(gaetano,
		 * prova, tribunaOlimpico, 3, 2, struct);
		 * 
		 * System.out.println("Prezzo: " + acqProva.getBiglietto().getPrezzo());
		 */

		/*JFrame frame = new JFrame("Test");
		frame.add(new VisualizzaIncassoPanel(struct));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(500, 200);*/
	}
	
	// Metodi di supporto, emulano in tutto e per tutto i metodi omonimi di StrutturaSportiva.
	// Sono qui replicati solo al fine di effettuare i test.
	
	public static boolean verificaValiditaPrenotazione(Prenotazione prenotazione, GregorianCalendar dataControllo) {
		boolean result = false;

		GregorianCalendar dataAttuale = dataControllo;

		long dataAttualeMillis = dataAttuale.getTimeInMillis();
		long dataPartitaMillis = prenotazione.getBigliettoPrenotato().getPartita().getData().getTimeInMillis();

		double dataAttualeOre = (((dataAttualeMillis / 1000) / 60) / 60);
		double dataPartitaOre = (((dataPartitaMillis / 1000) / 60) / 60);

		// uguale (=) eliminato per evitare di sforare con la scadenza.
		if (dataAttualeOre < (dataPartitaOre - 12)) {
			result = true;
		}

		return result;
	}
	
	public static void cancellaPrenotazioniScadute(StrutturaSportiva struct, GregorianCalendar dataControllo) {

		for (int i = (struct.getPrenotazioni().size() - 1); i >= 0; i--) {
			if (!TestGaetano.verificaValiditaPrenotazione(struct.getPrenotazioni().get(i), dataControllo)) {
				// metodo di reset dei posti
				Prenotazione prenotazioneScaduta = struct.getPrenotazioni().get(i);
				Partita partita = prenotazioneScaduta.getPartita();
				partita.resetSeatStatus(prenotazioneScaduta, SeatStatus.LIBERO);
				TestGaetano.cancellaPrenotazione(struct.getPrenotazioni().get(i), struct);
			}
		}
	}
	
	public static void cancellaPrenotazione(Prenotazione prenotazioneDaCancellare, StrutturaSportiva struct) {

		if (prenotazioneDaCancellare != null) {
			for (int i = (struct.getPrenotazioni().size() - 1); i >= 0; i--) {
				if (struct.getPrenotazioni().get(i).equals(prenotazioneDaCancellare)) {
					struct.getPrenotazioni().remove(i);
				}
			}
		}
	}

}
