/**
 * 
 */
package objectsTable;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import objectsTable.renderers.ClienteCellRenderer;
import objectsTable.renderers.GregorianCalendarCellRenderer;
import objectsTable.renderers.PartitaCellRenderer;
import objectsTable.renderers.PostoCellRenderer;
import objectsTable.renderers.SettoreCellRenderer;
import objectsTable.renderers.StadioCellRenderer;
import struttura.Acquisto;
import struttura.Biglietto;
import struttura.DAYS_OF_WEEK;
import struttura.Partita;
import struttura.Posto;
import struttura.Prenotazione;
import struttura.Sconti;
import struttura.Settore;
import struttura.Squadra;
import struttura.Stadio;
import struttura.StrutturaSportiva;
import struttura.TIPO_SCONTO;
import struttura.filters.PrenotationsByCustomer;
import struttura.filters.PrenotationsByMatch;
import struttura.filters.PrenotationsByStadium;
import struttura.filters.PurchasesByCustomer;
import struttura.filters.PurchasesByMatch;
import struttura.filters.PurchasesByStadium;
import user.AlreadyRegisteredUserException;
import user.Cliente;

public class PrenotazioneTable extends JTable implements Serializable {

	public PrenotazioneTable() {
		super(new PrenotazioneTableModel());
		this.init();
	}

	public PrenotazioneTable(ArrayList<Prenotazione> prenotazioni) {
		super(new PrenotazioneTableModel(prenotazioni));
		this.init();
	}

	private void init() {
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setColumnSelectionAllowed(false);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		this.setSelectionBackground(Color.GREEN);
		this.setRowHeight(this.getRowHeight() + 5);
		this.addMouseListener(new RightClickRowSelectionListener());

		this.setDefaultRenderer(Cliente.class, new ClienteCellRenderer());
		this.setDefaultRenderer(Partita.class, new PartitaCellRenderer());
		this.setDefaultRenderer(Stadio.class, new StadioCellRenderer());
		this.setDefaultRenderer(Settore.class, new SettoreCellRenderer());
		this.setDefaultRenderer(Posto.class, new PostoCellRenderer());
		this.setDefaultRenderer(GregorianCalendar.class, new GregorianCalendarCellRenderer());
	}

	public Prenotazione getSelectedPrenotazione() {
		// l'indice della riga selezionata nella parte visiva
		int viewIndex = this.getSelectedRow();
		// System.out.println("viewIndex = " + viewIndex);

		if (viewIndex == -1) {
			viewIndex = 0;
		}

		// (INDISPENSABILE PER POTER UTILIZZARE SORTING e FILTERING)
		int modelIndex = this.convertRowIndexToModel(viewIndex);
		// System.out.println("modelIndex =" + modelIndex);

		return ((PrenotazioneTableModel) this.getModel()).getAcquisto(modelIndex);
	}

	private static final long serialVersionUID = 8865381633135165349L;
	
	
	
	
	public static void main(String[] args) {
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
		Partita romaJuventus = new Partita(roma, juventus, olimpico,
				new GregorianCalendar(2016, Calendar.JANUARY, 19, 19, 00));
		struct.addPartita(romaJuventus);

		Partita juventusRoma = new Partita(juventus, roma, sanSiro,
				new GregorianCalendar(2016, Calendar.JANUARY, 20, 20, 0));
		struct.addPartita(juventusRoma);

		Partita salernitanaAvellinese = new Partita(salernitana, avellinese, arechi,
				new GregorianCalendar(2016, Calendar.JANUARY, 21, 17, 0));
		struct.addPartita(salernitanaAvellinese);

		/**** Sconti ****/
		Sconti scontoOlimpico = new Sconti(TIPO_SCONTO.TutteLePartiteDelloStadio, 10,
				new GregorianCalendar(2016, Calendar.JANUARY, 18), new GregorianCalendar(2016, Calendar.JANUARY, 24),
				olimpico);
		struct.addSconto(scontoOlimpico);

		Sconti scontoRomaJuventus = new Sconti(TIPO_SCONTO.PartitaCorrente, 15,
				new GregorianCalendar(2016, Calendar.JANUARY, 19), new GregorianCalendar(2016, Calendar.JANUARY, 19),
				romaJuventus);
		struct.addSconto(scontoRomaJuventus);

		Sconti scontoMercoledi = new Sconti(TIPO_SCONTO.GiornoPrestabilito, 5,
				new GregorianCalendar(2016, Calendar.JANUARY, 18), new GregorianCalendar(2016, Calendar.JANUARY, 24),
				DAYS_OF_WEEK.Mercoledi);
		struct.addSconto(scontoMercoledi);

		Sconti scontoGiovedi = new Sconti(TIPO_SCONTO.GiornoPrestabilito, 3,
				new GregorianCalendar(2016, Calendar.JANUARY, 18), new GregorianCalendar(2016, Calendar.JANUARY, 24),
				DAYS_OF_WEEK.Giovedi);
		struct.addSconto(scontoGiovedi);

		Sconti scontoSalernitanaAvellinese = new Sconti(TIPO_SCONTO.PartitaCorrente, 6,
				new GregorianCalendar(2016, Calendar.JANUARY, 21), new GregorianCalendar(2016, Calendar.JANUARY, 21),
				salernitanaAvellinese);
		struct.addSconto(scontoSalernitanaAvellinese);

		Sconti scontoArechi = new Sconti(TIPO_SCONTO.TutteLePartiteDelloStadio, 4.5,
				new GregorianCalendar(2016, Calendar.JANUARY, 18), new GregorianCalendar(2016, Calendar.JANUARY, 24),
				arechi);
		struct.addSconto(scontoArechi);

		/*** Clienti ****/
		Cliente gaetano = new Cliente("Gaetano", "Antonucci", "gnoanto94", "P@ssw0rd");
		try {
			struct.addUtente(gaetano);
		} catch (AlreadyRegisteredUserException e) {
			e.printStackTrace();
		}
		Cliente asdrubale = new Cliente("Asdrubale", "NonSense", "asdru55", "P@ssw0rd");
		try {
			struct.addUtente(asdrubale);
		} catch (AlreadyRegisteredUserException e) {
			e.printStackTrace();
		}
		Cliente mario = new Cliente("Mario", "Rossi", "mario45", "P@ssw0rd");
		try {
			struct.addUtente(mario);
		} catch (AlreadyRegisteredUserException e) {
			e.printStackTrace();
		}

		/**** Settori degli Stadi ****/
		Settore tribunaOlimpico = new Settore(olimpico, "Tribuna", olimpico.getPostiPerSettore(),
				olimpico.getNumeroFilePerSettore());
		Settore curvaSanSiro = new Settore(sanSiro, "Curva", sanSiro.getPostiPerSettore(),
				sanSiro.getNumeroFilePerSettore());
		Settore curvaSudArechi = new Settore(arechi, "Curva Sud", arechi.getPostiPerSettore(),
				arechi.getNumeroFilePerSettore());

		/**** Biglietti da Prenotare ****/
		// Roma - Juventus
		Biglietto billRJGaetano = new Biglietto(struct, gaetano, romaJuventus, tribunaOlimpico, 1, 3);
		Biglietto billRJAsdrubale = new Biglietto(struct, asdrubale, romaJuventus, tribunaOlimpico, 1, 4);
		Biglietto billRJMario = new Biglietto(struct, mario, romaJuventus, tribunaOlimpico, 1, 2);

		// Juventus - Roma
		Biglietto billJRGaetano = new Biglietto(struct, gaetano, juventusRoma, curvaSanSiro, 1, 3);
		Biglietto billJRMario = new Biglietto(struct, mario, juventusRoma, curvaSanSiro, 1, 2);

		/**** Prenotazioni ****/

		// Roma - Juventus
		Prenotazione prenRJGaetano = new Prenotazione(new GregorianCalendar(2016, Calendar.JANUARY, 18), billRJGaetano);
		struct.addPrenotazione(prenRJGaetano);

		Prenotazione prenRJAsdrubale = new Prenotazione(new GregorianCalendar(2016, Calendar.JANUARY, 18),
				billRJAsdrubale);
		struct.addPrenotazione(prenRJAsdrubale);

		Prenotazione prenRJMario = new Prenotazione(new GregorianCalendar(2016, Calendar.JANUARY, 18, 16, 0),
				billRJMario); // test su tempo
		struct.addPrenotazione(prenRJMario);

		// Juventus - Roma
		Prenotazione prenJRGaetano = new Prenotazione(new GregorianCalendar(2016, Calendar.JANUARY, 18), billJRGaetano);
		struct.addPrenotazione(prenJRGaetano);

		Prenotazione prenJRMario = new Prenotazione(new GregorianCalendar(2016, Calendar.JANUARY, 18), billJRMario);
		struct.addPrenotazione(prenJRMario);

		// Salernitana - Avellinese
		// non ci sono prenotazioni

		System.out.println("\nPrenotazioni Presenti: " + struct.getPrenotazioni().size() + " [Expected: 5]");
		/**** Verifica Filtri Prenotazioni ****/
		ArrayList<Prenotazione> perRomaJuventus = struct.getPrenotazioniFiltrate(new PrenotationsByMatch(romaJuventus));

		System.out.println("\nPrenotazioni Partita : " + romaJuventus);
		for (Prenotazione pren : perRomaJuventus) {
			System.out.println(pren);
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo() + " [Exp: 17.00]");
			System.out.println("----- partita -----");
		}

		System.out.println("END");

		System.out.println("\nPrenotazioni Partita : " + juventusRoma);
		ArrayList<Prenotazione> perJuventusRoma = struct.getPrenotazioniFiltrate(new PrenotationsByMatch(juventusRoma));

		for (Prenotazione pren : perJuventusRoma) {
			System.out.println(pren);
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo() + " [Exp: 23.75]");
			System.out.println("----- partita -----");
		}

		System.out.println("END");

		// non ci sono prenotazioni quindi non stampa nulla
		System.out.println("\nPrenotazioni Partita : " + salernitanaAvellinese);
		ArrayList<Prenotazione> perSalernitanaAvellinese = struct
				.getPrenotazioniFiltrate(new PrenotationsByMatch(salernitanaAvellinese));

		for (Prenotazione pren : perSalernitanaAvellinese) {
			System.out.println(pren);
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo() + " [Exp: 9.40]");
			System.out.println("----- partita -----");
		}

		System.out.println("END");

		System.out.println("\nPrenotazioni Cliente : " + gaetano);
		ArrayList<Prenotazione> perGaetano = struct.getPrenotazioniFiltrate(new PrenotationsByCustomer(gaetano));

		for (Prenotazione pren : perGaetano) {
			System.out.println(pren);
			System.out.println("Cliente: " + pren.getBigliettoPrenotato().getCliente().getNome());
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo());
			System.out.println("----- cliente ------");
		}

		System.out.println("END");

		System.out.println("\nPrenotazioni Cliente : " + mario);
		ArrayList<Prenotazione> perMario = struct.getPrenotazioniFiltrate(new PrenotationsByCustomer(mario));

		for (Prenotazione pren : perMario) {
			System.out.println(pren);
			System.out.println("Cliente: " + pren.getBigliettoPrenotato().getCliente().getNome());
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo());
			System.out.println("----- cliente ------");
		}

		System.out.println("END");

		System.out.println("\nPrenotazioni Stadio : " + olimpico);
		ArrayList<Prenotazione> perOlimpico = struct.getPrenotazioniFiltrate(new PrenotationsByStadium(olimpico));

		for (Prenotazione pren : perOlimpico) {
			System.out.println(pren);
			System.out.println("Stadio: " + pren.getBigliettoPrenotato().getPartita().getStadio());
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo());
			System.out.println("----- stadio -----");
		}

		System.out.println("END");

		System.out.println("\nPrenotazioni Stadio : " + sanSiro);
		ArrayList<Prenotazione> perSanSiro = struct.getPrenotazioniFiltrate(new PrenotationsByStadium(sanSiro));

		for (Prenotazione pren : perSanSiro) {
			System.out.println(pren);
			System.out.println("Stadio: " + pren.getBigliettoPrenotato().getPartita().getStadio());
			System.out.println(pren.getBigliettoPrenotato().getPartita());
			System.out.println(pren.getBigliettoPrenotato().getPrezzo());
			System.out.println("----- stadio -----");
		}

		
		JFrame frame = new JFrame("Prenotazioni");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PrenotazioneTable prenotazioniTable = new PrenotazioneTable(struct.getPrenotazioni());
		frame.add(new JScrollPane(prenotazioniTable));
		frame.setSize(1000, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		System.out.println(struct.getPrenotazioni().size());
		System.out.println("END");
		
		while(true){
			
			if(false)break;
		}

		/**** Acquisti ****/

		// Roma - Juventus (da prenotazione)
		Acquisto acqRJGaetano = new Acquisto(prenRJGaetano, struct);
		struct.addAcquisto(acqRJGaetano);
		Acquisto acqRJAsdrubale = new Acquisto(prenRJAsdrubale, struct);
		struct.addAcquisto(acqRJAsdrubale);

		// Juventus - Roma (da prenotazione)
		Acquisto acqJRGaetano = new Acquisto(prenJRGaetano, struct);
		struct.addAcquisto(acqJRGaetano);
		Acquisto acqJRMario = new Acquisto(prenJRMario, struct);
		struct.addAcquisto(acqJRMario);

		// Salernitana - Avellinese (senza prenotazione)
		Acquisto acqSAMario = new Acquisto(mario, salernitanaAvellinese, curvaSudArechi, 1, 3, struct);
		struct.addAcquisto(acqSAMario);
		Acquisto acqSAAsdrubale = new Acquisto(asdrubale, salernitanaAvellinese, curvaSudArechi, 1, 4, struct);
		struct.addAcquisto(acqSAAsdrubale);

		/*
		 * //Salernitana - Avellinese Biglietto billSAMario = new
		 * Biglietto(struct, mario, salernitanaAvellinese, curvaSudArechi, 1,
		 * 3); Biglietto billSAAsdrubale = new Biglietto(struct, asdrubale,
		 * salernitanaAvellinese, curvaSudArechi, 1, 4);
		 */

		/**** Verifica Cancellazione Prenotazioni ****/

		System.out.println("\nPrenotazioni presenti: " + struct.getPrenotazioni().size() + " [Expected: 1]");

		for (Prenotazione pren : struct.getPrenotazioni()) { // do nothing
			System.out.println(pren);
		}

		/**** Verifica Prenotazioni Scadute ****/
		// Le prenotazioni per la partita RomaJuventus scadono alle ore 07:00
		// del 19/01/2016
		// quindi l'ultima prenotazione rimasta deve essere cancellata.
		for (int i = 0; i < struct.getPrenotazioni().size(); i++) {
			System.out.println(struct.getPrenotazioni().get(i));

			if (!struct.verificaValiditaPrenotazione(struct.getPrenotazioni().get(i))) {
				struct.cancellaPrenotazioneCliente(struct.getPrenotazioni().get(i).getBigliettoPrenotato().getCliente(),
						struct.getPrenotazioni().get(i).getBigliettoPrenotato().getPartita());

				i--;
			}
		}

		System.out.println(
				"\nPrenotazioni presenti dopo verifica: " + struct.getPrenotazioni().size() + " [Expected: 0]");

		/**** Verifica Numero Acquisti ****/
		System.out.println("\nAcquisti Presenti: " + struct.getAcquisti().size() + " [Expected: 6]");

		for (Acquisto acq : struct.getAcquisti()) {
			System.out.println(
					"Partita:" + acq.getBiglietto().getPartita() + "\nPrezzo:" + acq.getBiglietto().getPrezzo());
		}

		/**** Verifica Filtri Acquisti ****/
		System.out.println("\nAcquisti per Stadio: " + olimpico);

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchasesByStadium(olimpico))) {
			System.out.println(acq);
			System.out.println("------- Acq. Stadio -------");
		}

		System.out.println("END");

		System.out.println("\nAcquisti per Stadio: " + sanSiro);

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchasesByStadium(sanSiro))) {
			System.out.println(acq);
			System.out.println("------- Acq. Stadio -------");
		}

		System.out.println("END");

		System.out.println("\nAcquisti per Stadio: " + arechi);

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchasesByStadium(arechi))) {
			System.out.println(acq);
			System.out.println("------- Acq. Stadio -------");
		}

		System.out.println("END");

		System.out.println("\nAcquisti per Partita: " + romaJuventus);

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchasesByMatch(romaJuventus))) {
			System.out.println(acq);
			System.out.println("------- Acq. Partita -------");
		}

		System.out.println("END");

		System.out.println("\nAcquisti per Partita: " + juventusRoma);

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchasesByMatch(juventusRoma))) {
			System.out.println(acq);
			System.out.println("------- Acq. Partita -------");
		}

		System.out.println("END");

		System.out.println("\nAcquisti per Partita: " + salernitanaAvellinese);

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchasesByMatch(salernitanaAvellinese))) {
			System.out.println(acq);
			System.out.println("------- Acq. Partita -------");
		}

		System.out.println("END");

		System.out.println("\n Acquisti per Cliente: " + gaetano);

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchasesByCustomer(gaetano))) {
			System.out.println(acq);
			System.out.println("------- Acq. Cliente -------");
		}

		System.out.println("END");

		System.out.println("\n Acquisti per Cliente: " + asdrubale);

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchasesByCustomer(asdrubale))) {
			System.out.println(acq);
			System.out.println("------- Acq. Cliente -------");
		}

		System.out.println("END");

		System.out.println("\n Acquisti per Cliente: " + mario);

		for (Acquisto acq : struct.getAcquistiFiltrati(new PurchasesByCustomer(mario))) {
			System.out.println(acq);
			System.out.println("------- Acq. Cliente -------");
		}

		System.out.println("END");

		/***** Incassi *****/
		System.out.println("\nIncasso totale della Struttura " + struct.getNome() + " : "
				+ struct.calcolaIncasso(struct.getAcquisti()) + " [Exp: 100.30]");

		System.out.println("\n Incasso per stadio: " + olimpico);
		System.out.println(" " + struct.calcolaIncasso(struct.getAcquistiFiltrati(new PurchasesByStadium(olimpico)))
				+ " [Exp: 34.00]");

		System.out.println("\n Incasso per stadio: " + sanSiro);
		System.out.println(" " + struct.calcolaIncasso(struct.getAcquistiFiltrati(new PurchasesByStadium(sanSiro)))
				+ " [Exp: 47.50]");

		System.out.println("\n Incasso per stadio: " + arechi);
		System.out.println(" " + struct.calcolaIncasso(struct.getAcquistiFiltrati(new PurchasesByStadium(arechi)))
				+ " [Exp: 18.80]");
	}
	
}
