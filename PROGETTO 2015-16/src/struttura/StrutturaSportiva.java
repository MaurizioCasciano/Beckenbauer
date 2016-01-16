package struttura;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.GregorianCalendar;

import struttura.filters.Filter;
import struttura.filters.PrenotationFilter;
import struttura.filters.PurchasesFilter;
import struttura.filters.ScontiFilter;
import user.AlreadyRegisteredUserException;
import user.Cliente;
import user.UserNotFound;
import user.Utente;

public class StrutturaSportiva implements Serializable {
	/**
	 * Costruisce un oggetto StrutturaSportiva.
	 * 
	 * @param nome
	 *            Il nome della StrutturaSportiva.
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
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Aggiunge un utente al database se non è già presente, altrimente lancia eccezione.
	 * @param utente
	 * 		  L'utente da registrare
	 * @throws AlreadyRegisteredUserException
	 * 		   L'eccezione lanciata nel caso l'utente che si prova ad inserire sia già 
	 * 		   presente nel database
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
	 * Restituisce un utente del database cercandolo tramite username, 
	 * se non viene trovato viene lanciata eccezione.
	 * @param username
	 * 		  L'username da ricercare
	 * @return l'utente trovato
	 * @throws UserNotFound
	 * 		   L'eccezione lanciata nel caso l'utente non venga trovato
	 * @author Maurizio Casciano
	 */
	public Utente getUtente(String username) throws UserNotFound {
		for (Utente u : this.utenti) {
			if (u.getUsername().equalsIgnoreCase(username)) {
				return u;
			}
		}

		throw new UserNotFound();
	}

	/**
	 * Aggiunge una partita al database.
	 * @param p
	 * 	      La partita da aggiungere.
	 * @author Maurizio Casciano
	 */
	public void addPartita(Partita p) {
		this.partiteProgrammate.add(p);
	}

	/**
	 * Aggiunge uno stadio al database.
	 * @param s
	 * 		 Lo stadio da aggiungere.
	 * @author Maurzio Casciano & Gaetano Antonucci
	 */
	public void addStadio(Stadio s) {
	
		if(this.stadi.contains(s))
				throw new AlreadyExistsObjectException("Stadio già presente !!!");
		this.stadi.add(s);
	}
	
	// Modificare i metodi add.
	
	/**
	 * Aggiunge una politica di sconto al database.
	 * @param sconto
	 * 		  La politica di sconto da inserire.
	 * @author Gaetano Antonucci
	 */
	public void addSconto(Sconti sconto){
		
			if(this.sconti.contains(sconto)) {
				throw new AlreadyExistsObjectException("Politica di Sconto già presente !!!");
			}
		this.sconti.add(sconto);
	}
	
	/**
	 * Aggiunge una prenotazione al database.
	 * @param pren
	 * 		  La prenotazione da inserire.
	 * @author Gaetano Antonucci
	 */
	public void addPrenotazione(PrenotazioneV2 pren){
		
			if(this.prenotazioni.contains(pren)) {
				throw new AlreadyExistsObjectException("Prenotazione già presente !!!");
			}
		this.prenotazioni.add(pren);
	}
	
	/**
	 * Aggiunge l'acquisto di un biglietto al database.
	 * @param acq
	 * 		  L'acquisto da inserire
	 * @author Gaetano Antonucci.
	 */
	public void addAcquisto(Acquisto acq){
		
			if(this.acquisti.contains(acq)) {
				throw new AlreadyExistsObjectException("Acquisto già presente !!!");
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
	 * Restituisce tutte le politiche di sconto, passate e attuali, che sono presenti
	 * nel database.
	 * @return ArrayList<Sconti> con tutti sconti.
	 * @author Gaetano Antonucci
	 */
	public ArrayList<Sconti> getSconti(){
		return this.sconti;
	}
	
	/**
	 * Restituisce gli sconti applicabili alla partita passata come parametro.
	 * @param filtroSconti
	 * 		  Il filtro da applicare.
	 * @param part
	 * 		  La partita sulla quale si vuole verificare ci siano sconti
	 * @return ArrayList<Sconti> con gli sconti applicabili alla partita
	 * @author Gaetano Antonucci
	 */
	public ArrayList<Sconti> getScontiApplicabili(ScontiFilter filtroSconti, Partita part){
		ArrayList<Sconti> filteredByChoice = new ArrayList<>();
		
			for(int j = 0; j < this.sconti.size(); j++){
				filtroSconti.updateCurrentSconto(j);
				if(filtroSconti.accept(part)){
					filteredByChoice.add(this.sconti.get(j));
				}
			}
		
		return filteredByChoice;
	}
	
	/**
	 * Restituisce tutte le prenotazioni presenti nel database.
	 * @return ArrayList<PrenotazioneV2> con tutte le prenotazioni.
	 * @author Gaetano Antonucci
	 */
	public ArrayList<PrenotazioneV2> getPrenotazioni(){
		return this.prenotazioni;
	}
	
	/**
	 * Restituisce le prenotazioni in base al filtro passato in input.
	 * @param filtroPrenotazioni
	 * 	      Il filtro da applicare
	 * @return ArrayList<PrenotazioneV2> con le prenotazioni ottenute.
	 * @author Gaetano Antonucci
	 */
	public ArrayList<PrenotazioneV2> getPrenotazioniFiltrate(PrenotationFilter filtroPrenotazioni){
		ArrayList<PrenotazioneV2> filteredByChoice = new ArrayList<>();
		
		for(int i = 0; i < this.prenotazioni.size(); i++){
			if(filtroPrenotazioni.accept(this.prenotazioni.get(i))){
				filteredByChoice.add(this.prenotazioni.get(i));
			}
		}
		
		return filteredByChoice;
	}
	
	/**
	 * Cancella una prenotazione dal database in base al cliente e alla partita
	 * @param c
	 * 		  Il cliente che ha prenotato
	 * @param part
	 * 		  La partita per cui è stata fatta una prenotazione
	 * @author Gaetano Antonucci
	 */
	public void cancellaPrenotazioneCliente(Cliente c, Partita part){
		
		/*for(int i = 0; i < this.prenotazioni.size(); i++){
			if((this.prenotazioni.get(i).getBigliettoPrenotato().getCliente().equals(c)) &&
				this.prenotazioni.get(i).getBigliettoPrenotato().getPartita().equals(part)){
					
					this.prenotazioni.remove(i);
					i--;
			}
		}*/
		
		for(int i = (this.prenotazioni.size() - 1); i >= 0; i--){
			if((this.prenotazioni.get(i).getBigliettoPrenotato().getCliente().equals(c)) &&
					this.prenotazioni.get(i).getBigliettoPrenotato().getPartita().equals(part)){
						
						this.prenotazioni.remove(i);
			}			
		}
	}
	
	/**
	 * Verifica se una determinata partita è stata prenotata da un determinato cliente
	 * @param clt
	 * 	      Il cliente su cui si effettua la verifica.
	 * @param prt
	 * 		 La partita su cui si vuole verificare la prenotazione
	 * @return boolean con l'esito della verifica.
	 * @author Gaetano Antonucci
	 */
	public boolean verificaPrenotazione(Cliente clt, Partita prt){
		boolean result = false;
		
		for(PrenotazioneV2 prenotazione: this.prenotazioni){
			if((prenotazione.getBigliettoPrenotato().getCliente().equals(clt)) 
					&& (prenotazione.getBigliettoPrenotato().getPartita().equals(prt))){
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	 * Restituisce tutti gli acquisti presenti nel database.
	 * @return ArrayList<Acquisto> con tutti gli acquisti.
	 * @author Gaetano Antonucci
	 */
	public ArrayList<Acquisto> getAcquisti(){
		return this.acquisti;
	}
	
	/**
	 * Restituisce gli acquisti in base al filtro passato in input.
	 * @param filtroAcquisti
	 *        Il filtro da applicare.
	 * @return ArrayList<Acquisto> con gli acquisti ottenuti.
	 * @author Gaetano Antonucci
	 */
	public ArrayList<Acquisto> getAcquistiFiltrati(PurchasesFilter filtroAcquisti){
		ArrayList<Acquisto> filteredByChoice = new ArrayList<>();
		
		for(int i = 0; i < this.acquisti.size(); i++){
			if(filtroAcquisti.accept(this.acquisti.get(i))){
				filteredByChoice.add(this.acquisti.get(i));
			}
		}
		return filteredByChoice;
	}
	
	/**
	 * Verifica se un biglietto per una determinata partita è stato acquistato
	 * da un determinato cliente.
	 * @param clt
	 * 	      Il cliente su cui si effettua la verifica.
	 * @param prt
	 * 		  La partita su cui si effettua la verifica.
	 * @return boolean con l'esito della verifica.
	 * @author Gaetano Antonucci
	 */
	public boolean verificaAcquisto(Cliente clt, Partita prt){
		boolean result = false;
		
		for(Acquisto acquisto: this.acquisti){
			if((acquisto.getBiglietto().getCliente().equals(clt)) 
					&& (acquisto.getBiglietto().getPartita().equals(prt))){
				result = true;
			}
		}
		
		return result;
	}
	
	public boolean verificaValiditàPrenotazione(PrenotazioneV2 prenotazione){
		boolean result = false;
		
		GregorianCalendar dataAttuale = new GregorianCalendar();
		
		long dataAttualeMillis = dataAttuale.getTimeInMillis();
		long dataPartitaMillis = prenotazione.getBigliettoPrenotato().getPartita().getData().getTimeInMillis();
		
		double dataAttualeOre = (((dataAttualeMillis / 1000) / 60) / 60);
		double dataPartitaOre = (((dataPartitaMillis / 1000) / 60) / 60);

		if(dataAttualeOre <= (dataPartitaOre - 12)){
			result = true;
		}
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if(this == obj){
			return true;
		}
		
		if(obj == null){
			return false;
		}
		
		if (getClass() != obj.getClass())
			return false;
		
		StrutturaSportiva other = (StrutturaSportiva) obj;
		
		if(this.nome.equals(other.nome)){
			result = true;
		}
		
		return result;
	}



	private static final long serialVersionUID = -1014833830864079436L;
	private String nome;
	// una struttura sportiva che comprenda pi� stadi
	private ArrayList<Stadio> stadi;
	private ArrayList<Partita> partiteProgrammate;
	private ArrayList<Utente> utenti;
	private ArrayList<Sconti> sconti;
	private ArrayList<PrenotazioneV2> prenotazioni;
	private ArrayList<Acquisto> acquisti;
}
