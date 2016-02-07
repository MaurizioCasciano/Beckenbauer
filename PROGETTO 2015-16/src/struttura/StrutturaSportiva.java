package struttura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

import struttura.filters.Filter;
import struttura.filters.PrenotationFilter;
import struttura.filters.PrenotationByMatchFilter;
import struttura.filters.PurchaseByMatchFilter;
import struttura.filters.PurchaseFilter;
import struttura.filters.ScontoByDayOfWeekFilter;
import struttura.filters.ScontoFilter;
import struttura.filters.ScontoByStadiumFilter;
import struttura.filters.ScontoByMatchFilter;
import user.AlreadyRegisteredUserException;
import user.Cliente;
import user.UserNotFoundException;
import user.Utente;

/**
 * Classe principale che modella l'intera Struttura Sportiva
 * 
 * @author Maurizio Casciano
 * @author Gaetano Antonucci
 */
public class StrutturaSportiva implements Serializable {
	/**
	 * Costruisce un oggetto StrutturaSportiva.
	 * 
	 * @param nome
	 *            - Il nome della StrutturaSportiva.
	 * @author Maurizio Casciano
	 * @author Gaetano Antonucci
	 */
	public StrutturaSportiva(String nome) {
		this.nome = nome;
		this.partiteProgrammate = new ArrayList<>();
		this.stadi = new ArrayList<>();
		this.utenti = new ArrayList<>();
		this.sconti = new ArrayList<>();
		this.prenotazioni = new ArrayList<>();
		this.acquisti = new ArrayList<>();
	}

	/**
	 * Restituisce il nome della StrutturaSportiva.
	 * 
	 * @return Il nome della StrutturaSportiva.
	 * @author Maurizio Casciano
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Aggiunge un {@link Utente} al database se non e' gia' presente,
	 * altrimenti lancia eccezione.
	 * 
	 * @param utente
	 *            - L'{@link Utente} da registrare
	 * @throws AlreadyRegisteredUserException
	 *             L'eccezione lanciata nel caso l'utente che si prova ad
	 *             inserire sia gia' presente nel database
	 * @author Maurizio Casciano
	 */
	public void addUtente(Utente utente) throws AlreadyRegisteredUserException {
		for (Utente u : this.utenti) {
			if (u.equals(utente)) {
				throw new AlreadyRegisteredUserException();
			}
		}

		this.utenti.add(utente);
	}

	/**
	 * Restituisce un {@link Utente} del database cercandolo tramite username,
	 * se non viene trovato viene lanciata eccezione.
	 * 
	 * @param username
	 *            - L'username da ricercare
	 * @return l'utente trovato
	 * @throws UserNotFoundException
	 *             L'eccezione lanciata nel caso l'utente non venga trovato
	 * @author Maurizio Casciano
	 */
	public Utente getUtente(String username) throws UserNotFoundException {
		for (Utente u : this.utenti) {
			if (u.getUsername().equalsIgnoreCase(username)) {
				return u;
			}
		}

		throw new UserNotFoundException();
	}

	/**
	 * Aggiunge una {@link Partita} al database.
	 * 
	 * @param p
	 *            - La partita da aggiungere.
	 * @throws AlreadyExistsObjectException
	 *             Ecceziona lanciata nel caso in cui la partita che si vuole
	 *             inserire e' gia' nel database
	 * @author Maurizio Casciano
	 * @author Gaetano Antonucci
	 */
	public void addPartita(Partita p) throws AlreadyExistsObjectException {
		if (this.partiteProgrammate.contains(p)) {
			throw new AlreadyExistsObjectException("Partita gia' presente !!!");
		}
		this.partiteProgrammate.add(p);
	}

	/**
	 * Aggiunge uno {@link Stadio} al database.
	 * 
	 * @param s
	 *            Lo stadio da aggiungere.
	 * @throws AlreadyExistsObjectException
	 *             Ecceziona lanciata nel caso in cui lo stadio che si vuole
	 *             inserire gia' e' nel database
	 * @author Maurzio Casciano
	 * @author Gaetano Antonucci
	 */
	public void addStadio(Stadio s) throws AlreadyExistsObjectException {

		if (this.stadi.contains(s)) {
			throw new AlreadyExistsObjectException("Stadio con lo stesso nome gia' presente !!!");
		}

		this.stadi.add(s);
	}

	/**
	 * Aggiunge una politica di sconto ({@link Sconto}) al database.
	 * 
	 * @param sconto
	 *            - La politica di sconto da inserire.
	 * @throws AlreadyExistsObjectException
	 *             Ecceziona lanciata nel caso in cui la politica di sconto che
	 *             si vuole inserire gia' e' nel database
	 * @author Gaetano Antonucci
	 * @author Maurizio Casciano
	 */
	public void addSconto(Sconto sconto) throws AlreadyExistsObjectException {

		if (this.sconti.contains(sconto)) {
			throw new AlreadyExistsObjectException("Politica di Sconto gia' presente !!!");
		}
		this.sconti.add(sconto);
	}

	/**
	 * Aggiunge una {@link Prenotazione} al database.
	 * 
	 * @param pren
	 *            - La prenotazione da inserire.
	 * @throws AlreadyExistsObjectException
	 *             Ecceziona lanciata nel caso in cui la prenotazione che si
	 *             vuole inserire gia' e' nel database
	 * @author Gaetano Antonucci
	 * @author Maurizio Casciano
	 */
	public void addPrenotazione(Prenotazione pren) throws AlreadyExistsObjectException {

		if (this.prenotazioni.contains(pren)) {
			throw new AlreadyExistsObjectException("Prenotazione gia' presente !!!");
		}
		this.prenotazioni.add(pren);
	}

	/**
	 * Aggiunge l'acquisto di un biglietto al database.
	 * 
	 * @param acq
	 *            - L'acquisto da inserire
	 * @throws AlreadyExistsObjectException
	 *             Ecceziona lanciata nel caso in cui l'acquisto che si vuole
	 *             inserire gia' e' nel database
	 * @author Gaetano Antonucci
	 * @author Maurizio Casciano
	 */
	public void addAcquisto(Acquisto acq) throws AlreadyExistsObjectException {

		if (this.acquisti.contains(acq)) {
			throw new AlreadyExistsObjectException("Acquisto gia' presente !!!");
		}
		this.acquisti.add(acq);
	}

	/**
	 * Restituisce tutte le partite programmate.
	 * 
	 * @return Tutte le partite programmate.
	 * @author Maurizio Casciano
	 */
	public ArrayList<Partita> getPartiteProgrammate() {
		return this.partiteProgrammate;
	}

	/**
	 * Restituisce le partite programmate filtrate dal Filtro passato in input.
	 * 
	 * @param myFilter
	 *            Il filtro da applicare.
	 * @return Le partite programmate dopo l'applicazione del filtro.
	 * @author Maurizio Casciano
	 */
	public ArrayList<Partita> getPartiteProgrammate(Filter myFilter) {
		ArrayList<Partita> filteredByWeek = new ArrayList<>();

		for (int i = 0; i < this.partiteProgrammate.size(); i++) {
			if (myFilter.accept(this.partiteProgrammate.get(i))) {
				filteredByWeek.add(this.partiteProgrammate.get(i));
			}
		}
		return filteredByWeek;
	}

	/**
	 * Restituisce gli Stadi della Struttura
	 * 
	 * @return gli stadi
	 * @author Gaetano Antonucci
	 */
	public ArrayList<Stadio> getStadi() {
		return this.stadi;
	}

	/**
	 * Restituisce tutte le politiche di sconto, passate ed attuali, che sono
	 * presenti nel database.
	 * 
	 * @return ArrayList con tutti sconti.
	 * @author Gaetano Antonucci
	 */
	public ArrayList<Sconto> getSconti() {
		return this.sconti;
	}

	/**
	 * Restituisce gli sconti applicabili alla partita passata come parametro.
	 * 
	 * @param filtroSconti
	 *            Il filtro da applicare.
	 * @param part
	 *            La {@link Partita} sulla quale si vuole verificare ci siano
	 *            sconti
	 * @return ArrayList con gli sconti applicabili alla partita
	 * @author Gaetano Antonucci
	 */
	public ArrayList<Sconto> getScontiApplicabili(ScontoFilter filtroSconti, Partita part, GregorianCalendar dataVer) {
		ArrayList<Sconto> filteredByChoice = new ArrayList<>();

		for (int j = 0; j < this.sconti.size(); j++) {
			filtroSconti.updateCurrentSconto(j);
			if (filtroSconti.accept(part, dataVer)) {
				filteredByChoice.add(this.sconti.get(j));
			}
		}

		return filteredByChoice;
	}

	/**
	 * Restituisce tutte le prenotazioni presenti nel database.
	 * 
	 * @return ArrayList con tutte le prenotazioni.
	 * @author Gaetano Antonucci
	 */
	public ArrayList<Prenotazione> getPrenotazioni() {
		return this.prenotazioni;
	}

	/**
	 * Restituisce le prenotazioni in base al filtro passato in input.
	 * 
	 * @param filtroPrenotazioni
	 *            Il filtro da applicare
	 * @return ArrayList con le prenotazioni ottenute.
	 * @author Gaetano Antonucci
	 */
	public ArrayList<Prenotazione> getPrenotazioniFiltrate(PrenotationFilter filtroPrenotazioni) {
		ArrayList<Prenotazione> filteredByChoice = new ArrayList<>();

		for (int i = 0; i < this.prenotazioni.size(); i++) {
			if (filtroPrenotazioni.accept(this.prenotazioni.get(i))) {
				filteredByChoice.add(this.prenotazioni.get(i));
			}
		}

		return filteredByChoice;
	}

	/**
	 * Cancella una prenotazione dal database in base al cliente e alla partita
	 * 
	 * @param c
	 *            Il cliente che ha prenotato
	 * @param part
	 *            La partita per cui è stata fatta una prenotazione
	 * @author Gaetano Antonucci
	 */
	public void cancellaPrenotazioneCliente(Cliente c, Partita part) {

		for (int i = (this.prenotazioni.size() - 1); i >= 0; i--) {
			if ((this.prenotazioni.get(i).getBigliettoPrenotato().getCliente().equals(c))
					&& this.prenotazioni.get(i).getBigliettoPrenotato().getPartita().equals(part)) {

				this.prenotazioni.remove(i);
			}
		}
	}

	/**
	 * Cancella la prenotazione passata come parametro dall'ArrayList delle
	 * prenotazioni della struttura sportiva.
	 * 
	 * @param prenotazioneDaCancellare
	 *            - La prenotazione da cancellare
	 * 
	 * @author Gaetano Antonucci
	 */
	public void cancellaPrenotazione(Prenotazione prenotazioneDaCancellare) {

		if (prenotazioneDaCancellare != null) {
			for (int i = (this.prenotazioni.size() - 1); i >= 0; i--) {
				if (this.prenotazioni.get(i).equals(prenotazioneDaCancellare)) {
					this.prenotazioni.remove(i);
				}
			}
		}
	}

	/**
	 * Verifica se una determinata partita e' stata prenotata da un determinato
	 * cliente
	 * 
	 * @param clt
	 *            - Il {@link Cliente} su cui si effettua la verifica.
	 * @param prt
	 *            - La {@link Partita} su cui si vuole verificare la
	 *            prenotazione
	 * @return {@code boolean} con l'esito della verifica.
	 * @author Gaetano Antonucci
	 */
	public boolean verificaPrenotazione(Cliente clt, Partita prt) {
		boolean result = false;

		for (Prenotazione prenotazione : this.prenotazioni) {
			if ((prenotazione.getBigliettoPrenotato().getCliente().equals(clt))
					&& (prenotazione.getBigliettoPrenotato().getPartita().equals(prt))) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * Restituisce tutti gli acquisti presenti nel database.
	 * 
	 * @return ArrayList con tutti gli acquisti.
	 * @author Gaetano Antonucci
	 */
	public ArrayList<Acquisto> getAcquisti() {
		return this.acquisti;
	}

	/**
	 * Restituisce gli acquisti in base al filtro passato in input.
	 * 
	 * @param filtroAcquisti
	 *            - Il filtro da applicare.
	 * @return ArrayList con gli acquisti ottenuti.
	 * @author Gaetano Antonucci
	 */
	public ArrayList<Acquisto> getAcquistiFiltrati(PurchaseFilter filtroAcquisti) {
		ArrayList<Acquisto> filteredByChoice = new ArrayList<>();

		for (int i = 0; i < this.acquisti.size(); i++) {
			if (filtroAcquisti.accept(this.acquisti.get(i))) {
				filteredByChoice.add(this.acquisti.get(i));
			}
		}
		return filteredByChoice;
	}

	/**
	 * Verifica se un biglietto per una determinata partita e' stato acquistato
	 * da un determinato cliente.
	 * 
	 * @param clt
	 *            - Il {@link Cliente} su cui si effettua la verifica.
	 * @param prt
	 *            - La {@link Partita} su cui si effettua la verifica.
	 * @return {@code boolean} con l'esito della verifica.
	 * @author Gaetano Antonucci
	 */
	public boolean verificaAcquisto(Cliente clt, Partita prt) {
		boolean result = false;

		for (Acquisto acquisto : this.acquisti) {
			if ((acquisto.getBiglietto().getCliente().equals(clt))
					&& (acquisto.getBiglietto().getPartita().equals(prt))) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * Cancella l'oggetto acquisto passato come paramentro dall'ArrayList di
	 * acquisti della struttura sportiva.
	 * 
	 * @param acquistoDaCancellare
	 *            - L'acquisto da cancellare.
	 * @author Gaetano Antonucci
	 */
	public void cancellaAcquisto(Acquisto acquistoDaCancellare) {

		if (acquistoDaCancellare != null) {
			for (int i = (this.acquisti.size() - 1); i >= 0; i--) {
				if (this.acquisti.get(i).equals(acquistoDaCancellare)) {
					this.acquisti.remove(i);
				}
			}
		}
	}

	/**
	 * Cancella le prenotazioni e gli acquisti di una determinata partita
	 * 
	 * @param partita
	 *            - La partita per cui si desidera eliminare acquisti e
	 *            prenotazioni
	 * @author Gaetano Antonucci
	 */
	public void cancellaPrenotazioniAcquistiPerPartita(Partita partita) {

		ArrayList<Prenotazione> prenotazioniDaCancellare;
		ArrayList<Acquisto> acquistiDaCancellare;

		if (partita != null) {
			prenotazioniDaCancellare = this.getPrenotazioniFiltrate(new PrenotationByMatchFilter(partita));
			acquistiDaCancellare = this.getAcquistiFiltrati(new PurchaseByMatchFilter(partita));

			for (Prenotazione pren : prenotazioniDaCancellare) {
				this.cancellaPrenotazione(pren);
			}

			for (Acquisto acq : acquistiDaCancellare) {
				this.cancellaAcquisto(acq);
			}
		}
	}

	/**
	 * Verifica se una prenotazione e' ancora valida. N.B. - Una prenotazione
	 * scade 12 ore prima della partita a cui fa riferimento.
	 * 
	 * @param prenotazione
	 *            - La prenotazione da controllare
	 * @return {@code boolean} che indica la validita'
	 * @author Maurizio Casciano
	 * @author Gaetano Antonucci
	 */
	public boolean verificaValiditaPrenotazione(Prenotazione prenotazione) {
		boolean result = false;

		GregorianCalendar dataAttuale = new GregorianCalendar();

		long dataAttualeMillis = dataAttuale.getTimeInMillis();
		long dataPartitaMillis = prenotazione.getBigliettoPrenotato().getPartita().getData().getTimeInMillis();

		double dataAttualeOre = (((dataAttualeMillis / 1000) / 60) / 60);
		double dataPartitaOre = (((dataPartitaMillis / 1000) / 60) / 60);

		// uguale (=) eliminato per evitare di sforare con la scadenza.
		if (dataAttualeOre < (dataPartitaOre - ORE_SCADENZA_PRENOTAZIONE)) {
			result = true;
		}

		return result;
	}

	/**
	 * Cancella tutte le prenotazioni che non sono piu valide
	 * 
	 * @author Gaetano Antonucci
	 */
	public void cancellaPrenotazioniScadute() {

		for (int i = (this.prenotazioni.size() - 1); i >= 0; i--) {
			if (!this.verificaValiditaPrenotazione(this.prenotazioni.get(i))) {
				// metodo di reset dei posti
				Prenotazione prenotazioneScaduta = this.prenotazioni.get(i);
				Partita partita = prenotazioneScaduta.getPartita();
				partita.resetSeatStatus(prenotazioneScaduta, SeatStatus.LIBERO);
				this.cancellaPrenotazione(this.prenotazioni.get(i));
			}
		}
	}

	/**
	 * Dato un cliente e una partita, restituisce un prenotazione se presente.
	 * 
	 * @param clt
	 *            - Il cliente su cui si effettua la ricerca.
	 * @param partita
	 *            - La partita su cui si effettua la ricerca.
	 * @return la prenotazione trovata
	 * @author Gaetano Antonucci
	 */
	public Prenotazione getPrenotazioneCliente(Cliente clt, Partita partita) {
		Prenotazione risultato = null;
		for (Prenotazione p : this.prenotazioni) {
			if (p.getBigliettoPrenotato().getCliente().equals(clt)
					&& p.getBigliettoPrenotato().getPartita().equals(partita)) {
				risultato = p;
			}
		}
		return risultato;
	}

	/**
	 * Calcola l'incasso della struttura sportiva in base all'ArrayList passato
	 * in input.
	 * 
	 * @param perIncasso
	 *            L'ArrayList in input, può essere this.acquisti, allora sarà
	 *            il totale complessivo, oppure un ArrayList ottenuto dai metodi
	 *            di filtro precedenti.
	 * @return {@code double} con l'incasso totale.
	 * @author Gaetano Antonucci
	 */
	public double calcolaIncasso(ArrayList<Acquisto> perIncasso) {
		double sommaPrezzi = 0;

		for (Acquisto acq : perIncasso) {
			sommaPrezzi += acq.getBiglietto().getPrezzo();
		}

		return sommaPrezzi;
	}

	public double getBestAvailablePrice(Partita partita, GregorianCalendar dataBiglietto) {

		double prezzoDiPartenza = partita.getStadio().getPrezzoPerPartita();

		ArrayList<Sconto> perPartita = this.getScontiApplicabili(new ScontoByMatchFilter(this.getSconti()), partita, dataBiglietto);
		ArrayList<Sconto> perStadio = this.getScontiApplicabili(new ScontoByStadiumFilter(this.getSconti()), partita, dataBiglietto);
		ArrayList<Sconto> perGiorno = this.getScontiApplicabili(new ScontoByDayOfWeekFilter(this.getSconti()), partita, dataBiglietto);

		double maxScontoPartita = 0.00;
		double maxScontoStadio = 0.00;
		double maxScontoGiorno = 0.00;

		for (Sconto s1 : perPartita) {
			if (maxScontoPartita <= s1.getPercetualeSconto()) {
				maxScontoPartita = s1.getPercetualeSconto();
			}
		}

		for (Sconto s2 : perStadio) {
			if (maxScontoStadio <= s2.getPercetualeSconto()) {
				maxScontoStadio = s2.getPercetualeSconto();
			}
		}

		for (Sconto s3 : perGiorno) {
			if (maxScontoGiorno <= s3.getPercetualeSconto()) {
				maxScontoGiorno = s3.getPercetualeSconto();
			}
		}

		double[] scontiMassimi = { maxScontoPartita, maxScontoGiorno, maxScontoStadio };
		Arrays.sort(scontiMassimi);
		double maxSconto = scontiMassimi[scontiMassimi.length - 1];

		 /*System.out.println("Verifica Sconto su Biglietto");
		 System.out.println("maxScontoPartita " + maxScontoPartita);
		 System.out.println("maxScontoStadio " + maxScontoStadio);
		 System.out.println("maxScontoGiorno " + maxScontoGiorno); */

		double prezzoFinale = prezzoDiPartenza - (prezzoDiPartenza * maxSconto);

		return prezzoFinale;
	}

	/**
	 * Verifica se l'oggetto corrente e' uguale all'oggetto passato come
	 * parametro
	 * 
	 * @param obj
	 *            - l'oggetto su cui effettuare la verifica
	 * @return {@code true} se quest'oggetto e' uguale all'oggetto passato come
	 *         parametro, {@code false} altrimenti.
	 * @author Gaetano Antonucci
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass())
			return false;

		StrutturaSportiva other = (StrutturaSportiva) obj;

		if (this.nome.equals(other.nome)) {
			result = true;
		}

		return result;
	}

	private static final long serialVersionUID = -1014833830864079436L;
	private String nome;
	// una struttura sportiva che comprenda piu' stadi
	private ArrayList<Stadio> stadi;
	private ArrayList<Partita> partiteProgrammate;
	private ArrayList<Utente> utenti;
	private ArrayList<Sconto> sconti;
	private ArrayList<Prenotazione> prenotazioni;
	private ArrayList<Acquisto> acquisti;

	private static final int ORE_SCADENZA_PRENOTAZIONE = 12;
}
