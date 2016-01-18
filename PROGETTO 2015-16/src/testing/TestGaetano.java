package testing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import struttura.*;
import struttura.filters.*;
import user.AlreadyRegisteredUserException;
import user.Cliente;

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
		
		/**** Squadre ****/
		Squadra roma = new Squadra("Roma");
		Squadra juventus = new Squadra("Juventus");
		Squadra salernitana = new Squadra("Salernitana");
		Squadra avellinese = new Squadra("Avellinese");
		
		/**** Partite ****/
		Partita romaJuventus = new Partita(roma, juventus, olimpico, new GregorianCalendar(2016, Calendar.JANUARY, 19, 19, 00));
		struct.addPartita(romaJuventus);
		
		Partita juventusRoma = new Partita(juventus, roma, sanSiro, new GregorianCalendar(2016, Calendar.JANUARY, 20, 20, 0));
		struct.addPartita(juventusRoma);
		
		Partita salernitanaAvellinese = new Partita(salernitana, avellinese, arechi, new GregorianCalendar(2016, Calendar.JANUARY, 21, 17, 0));
		struct.addPartita(salernitanaAvellinese);
		
		/**** Sconti ****/
		Sconti scontoOlimpico = new Sconti(TIPO_SCONTO.TutteLePartiteDelloStadio, 10, new GregorianCalendar(2016, Calendar.JANUARY, 18), new GregorianCalendar(2016, Calendar.JANUARY, 24), olimpico);
		struct.addSconto(scontoOlimpico); 
		
		Sconti scontoRomaJuventus = new Sconti(TIPO_SCONTO.PartitaCorrente, 15, new GregorianCalendar(2016, Calendar.JANUARY, 19), new GregorianCalendar(2016, Calendar.JANUARY, 19), romaJuventus);
		struct.addSconto(scontoRomaJuventus); 
	
		Sconti scontoMercoledi = new Sconti(TIPO_SCONTO.GiornoPrestabilito, 5, new GregorianCalendar(2016, Calendar.JANUARY, 18), new GregorianCalendar(2016, Calendar.JANUARY, 24), DAYS_OF_WEEK.Mercoledi);
		struct.addSconto(scontoMercoledi); 
		
		Sconti scontoGiovedi = new Sconti(TIPO_SCONTO.GiornoPrestabilito, 3, new GregorianCalendar(2016, Calendar.JANUARY, 18), new GregorianCalendar(2016, Calendar.JANUARY, 24), DAYS_OF_WEEK.Giovedi);
		struct.addSconto(scontoGiovedi);
		
		Sconti scontoSalernitanaAvellinese = new Sconti(TIPO_SCONTO.PartitaCorrente, 6, new GregorianCalendar(2016, Calendar.JANUARY, 21), new GregorianCalendar(2016, Calendar.JANUARY, 21), salernitanaAvellinese);
		struct.addSconto(scontoSalernitanaAvellinese);
		
		Sconti scontoArechi = new Sconti(TIPO_SCONTO.TutteLePartiteDelloStadio, 4.5,new GregorianCalendar(2016, Calendar.JANUARY, 18), new GregorianCalendar(2016, Calendar.JANUARY, 24), arechi);
		struct.addSconto(scontoArechi);
		
		/*** Clienti ****/
		Cliente gaetano = new Cliente("Gaetano", "Antonucci", "gnoanto94", "P@ssw0rd");
		struct.addUtente(gaetano);
		Cliente asdrubale = new Cliente("Asdrubale", "NonSense", "asdru55", "P@ssw0rd");
		struct.addUtente(asdrubale);
		Cliente mario = new Cliente("Mario", "Rossi", "mario45", "P@ssw0rd");
		struct.addUtente(mario);
		
		/**** Settori degli Stadi ****/
		Settore tribunaOlimpico = new Settore(olimpico, "Tribuna", olimpico.getPostiPerSettore(), olimpico.getFilePerSettore());
		Settore curvaSanSiro = new Settore(sanSiro, "Curva", sanSiro.getPostiPerSettore(), sanSiro.getFilePerSettore());
		Settore curvaSudArechi = new Settore(arechi, "Curva Sud", arechi.getPostiPerSettore(), arechi.getFilePerSettore());
		
		/**** Biglietti da Prenotare****/
		//Roma - Juventus
		Biglietto billRJGaetano = new Biglietto(struct, gaetano, romaJuventus, tribunaOlimpico, 1, 3);
		Biglietto billRJAsdrubale = new Biglietto(struct, asdrubale, romaJuventus, tribunaOlimpico, 1, 4);
		Biglietto billRJMario = new Biglietto(struct, mario, romaJuventus, tribunaOlimpico, 1, 2);
		
		//Juventus - Roma
		Biglietto billJRGaetano = new Biglietto(struct, gaetano, juventusRoma, curvaSanSiro, 1, 3); 
		Biglietto billJRMario = new Biglietto(struct, mario, juventusRoma, curvaSanSiro, 1, 2);
		
		/**** Prenotazioni ****/
		
		//Roma - Juventus
		PrenotazioneV2 prenRJGaetano = new PrenotazioneV2(new GregorianCalendar(), billRJGaetano);
		struct.addPrenotazione(prenRJGaetano);
	
		PrenotazioneV2 prenRJAsdrubale = new PrenotazioneV2(new GregorianCalendar(), billRJAsdrubale);
		struct.addPrenotazione(prenRJAsdrubale);
		
		PrenotazioneV2 prenRJMario = new PrenotazioneV2(new GregorianCalendar(), billRJMario);
		struct.addPrenotazione(prenRJMario);
		
		//Juventus - Roma
		PrenotazioneV2 prenJRGaetano = new PrenotazioneV2(new GregorianCalendar(), billJRGaetano);
		struct.addPrenotazione(prenJRGaetano);
		
		PrenotazioneV2 prenJRMario = new PrenotazioneV2(new GregorianCalendar(), billJRMario);
		struct.addPrenotazione(prenJRMario);
		
		//Salernitana - Avellinese
		// non ci sono prenotazioni
		
		System.out.println("\nPrenotazioni Presenti: " + struct.getPrenotazioni().size() + " [Expected: 5]");
		/**** Verifica Filtri Prenotazioni ****/
		ArrayList<PrenotazioneV2> perRomaJuventus = struct.getPrenotazioniFiltrate(new PrenotationsByMatch(romaJuventus));
		
		System.out.println("Prenotazioni Partita : " + romaJuventus);
		for(PrenotazioneV2 pren: perRomaJuventus){
			System.out.println(pren);
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo() + "[Exp: 17.00]");
			System.out.println("----- partita -----");
		}
		
		System.out.println("END");
		
		System.out.println("\nPrenotazioni Partita : " + juventusRoma);
		ArrayList<PrenotazioneV2> perJuventusRoma = struct.getPrenotazioniFiltrate(new PrenotationsByMatch(juventusRoma));
		
		for(PrenotazioneV2 pren: perJuventusRoma){
			System.out.println(pren);
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo() + "[Exp: 23.75]");
			System.out.println("----- partita -----");
		}
		
		System.out.println("END");
		
		// non ci sono prenotazioni quindi non stampa nulla
		System.out.println("\nPrenotazioni Partita : " + salernitanaAvellinese);
		ArrayList<PrenotazioneV2> perSalernitanaAvellinese = struct.getPrenotazioniFiltrate(new PrenotationsByMatch(salernitanaAvellinese));
		
		for(PrenotazioneV2 pren: perSalernitanaAvellinese){
			System.out.println(pren);
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo() + "[Exp: 9.40]");
			System.out.println("----- partita -----");
		}
		
		System.out.println("END");
		
		System.out.println("\nPrenotazioni Cliente : " + gaetano);
		ArrayList<PrenotazioneV2> perGaetano = struct.getPrenotazioniFiltrate(new PrenotationsByCustomer(gaetano));
		
		for(PrenotazioneV2 pren: perGaetano){
			System.out.println(pren);
			System.out.println("Cliente: " + pren.getBigliettoPrenotato().getCliente().getNome());
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo());
			System.out.println("----- cliente ------");
		}
		
		System.out.println("END");
		
		System.out.println("\nPrenotazioni Cliente : " + mario);
		ArrayList<PrenotazioneV2> perMario = struct.getPrenotazioniFiltrate(new PrenotationsByCustomer(mario));
		
		for(PrenotazioneV2 pren: perMario){
			System.out.println(pren);
			System.out.println("Cliente: " + pren.getBigliettoPrenotato().getCliente().getNome());
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo());
			System.out.println("----- cliente ------");
		}
		
		System.out.println("END");
		
		System.out.println("\nPrenotazioni Stadio : " + olimpico);
		ArrayList<PrenotazioneV2> perOlimpico = struct.getPrenotazioniFiltrate(new PrenotationsByStadium(olimpico));
		
		for(PrenotazioneV2 pren: perOlimpico){
			System.out.println(pren);
			System.out.println("Stadio: " + pren.getBigliettoPrenotato().getPartita().getStadio());
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo());
			System.out.println("----- stadio -----");
		}
		
		System.out.println("END");
		
		System.out.println("\nPrenotazioni Stadio : " + sanSiro);
		ArrayList<PrenotazioneV2> perSanSiro = struct.getPrenotazioniFiltrate(new PrenotationsByStadium(sanSiro));
		
		for(PrenotazioneV2 pren: perSanSiro){
			System.out.println(pren);
			System.out.println("Stadio: " + pren.getBigliettoPrenotato().getPartita().getStadio());
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo());
			System.out.println("----- stadio -----");
		}
		
		System.out.println("END");
		
		/**** Acquisti ****/
		
		//Roma - Juventus (da prenotazione)
		Acquisto acqRJGaetano = new Acquisto(prenRJGaetano, struct);
		struct.addAcquisto(acqRJGaetano);
		Acquisto acqRJAsdrubale = new Acquisto(prenRJAsdrubale, struct);
		struct.addAcquisto(acqRJAsdrubale);
		
		//Juventus - Roma (da prenotazione)
		Acquisto acqJRGaetano = new Acquisto(prenJRGaetano, struct);
		struct.addAcquisto(acqJRGaetano);
		Acquisto acqJRMario = new Acquisto(prenJRMario, struct);
		struct.addAcquisto(acqJRMario);
		
		//Salernitana - Avellinese (senza prenotazione)
		Acquisto acqSAMario = new Acquisto(mario, salernitanaAvellinese, curvaSudArechi,1, 3, struct);
		struct.addAcquisto(acqSAMario);
		Acquisto acqSAAsdrubale = new Acquisto(asdrubale, salernitanaAvellinese, curvaSudArechi, 1, 4, struct);
		struct.addAcquisto(acqSAAsdrubale);
		
		/* //Salernitana - Avellinese
		Biglietto billSAMario = new Biglietto(struct, mario, salernitanaAvellinese, curvaSudArechi, 1, 3);
		Biglietto billSAAsdrubale = new Biglietto(struct, asdrubale, salernitanaAvellinese, curvaSudArechi, 1, 4); */
		
		/**** Verifica Cancellazione Prenotazioni ****/
		
		System.out.println("\nPrenotazioni presenti: " + struct.getPrenotazioni().size() + " [Expected: 1]");
		
		for(PrenotazioneV2 pren: struct.getPrenotazioni()){ // do nothing
			System.out.println(pren);
		}
		
		/**** Verifica Numero Acquisti ****/
		System.out.println("\nAcquisti Presenti: " + struct.getAcquisti().size() + " [Expected: 6]");
		
		for(Acquisto acq: struct.getAcquisti()){
			System.out.println("Partita:" + acq.getBiglietto().getPartita() + "\nPrezzo:" + acq.getBiglietto().getPrezzo());
		}
			
		/**** Verifica Filtri Acquisti ****/
		System.out.println("Acquisti per Stadio: " + olimpico);
		
		for(Acquisto acq: struct.getAcquistiFiltrati(new PurchasesByStadium(olimpico))){
			System.out.println(acq);
		}
		
		System.out.println("END");
		
		System.out.println("Acquisti per Stadio: " + sanSiro);
		
		for(Acquisto acq: struct.getAcquistiFiltrati(new PurchasesByStadium(sanSiro))){
			System.out.println(acq);
		}

		System.out.println("END");
		
		System.out.println("Acquisti per Stadio: " + arechi);
		
		for(Acquisto acq: struct.getAcquistiFiltrati(new PurchasesByStadium(arechi))){
			System.out.println(acq);
		}
		
		System.out.println("END");
		
		System.out.println("Acquisti per Partita: " + romaJuventus);
		
		for(Acquisto acq: struct.getAcquistiFiltrati(new PurchasesByMatch(romaJuventus))){
			System.out.println(acq);
		}
	
		System.out.println("END");
		
		System.out.println("Acquisti per Partita: " + juventusRoma);
		
		for(Acquisto acq: struct.getAcquistiFiltrati(new PurchasesByMatch(juventusRoma))){
			System.out.println(acq);
		}
		
	}

}
