package struttura;

import java.io.Serializable;
import java.util.ArrayList;
import struttura.filters.Filter;
import struttura.filters.PrenotationFilter;
import struttura.filters.PurchasesFilter;
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
	
	public void addUtente(Utente utente) throws AlreadyRegisteredUserException {
		for (Utente u : this.utenti) {
			if (u.equals(utente)) {
				throw new AlreadyRegisteredUserException();
			}
		}

		this.utenti.add(utente);
	}

	public Utente getUtente(String username) throws UserNotFound {
		for (Utente u : this.utenti) {
			if (u.getUsername().equalsIgnoreCase(username)) {
				return u;
			}
		}

		throw new UserNotFound();
	}

	public void addPartita(Partita p) {
		this.partiteProgrammate.add(p);
	}

	public void addStadio(Stadio s) {
		this.stadi.add(s);
	}
	
	public void addSconto(Sconti sconto){
		this.sconti.add(sconto);
	}
	
	public void addPrenotazione(PrenotazioneV2 pren){
		this.prenotazioni.add(pren);
	}
	
	public void addAcquisto(Acquisto acq){
		this.acquisti.add(acq);
	}

	/**
	 * Restituisce tutte le partite programmate.
	 * 
	 * @return Tutte le partite programmate.
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
	
	
	public ArrayList<Sconti> getSconti(){
		return this.sconti;
	}
	
	/**
	 * Restituisce gli sconti filtrati dal filtro passato in input.
	 * 
	 * @param filtroSconti
	 * 			Il filtro da applicare
	 * @return gli sconti applicabili dopo l'applicazione del filtro.
	 */
	public ArrayList<Sconti> getScontiApplicabili(Filter filtroSconti){
		ArrayList<Sconti> filteredByChoice = new ArrayList<>();
		
		for(int i = 0; i < this.partiteProgrammate.size(); i++){
			for(int j = 0; j < this.sconti.size(); i++){
				if(filtroSconti.accept(this.partiteProgrammate.get(i))){
					filteredByChoice.add(this.sconti.get(j));
				}
			}
		}
		
		return filteredByChoice;
	}
	
	public ArrayList<PrenotazioneV2> getPrenotazioni(){
		return this.prenotazioni;
	}
	
	public ArrayList<PrenotazioneV2> getPrenotazioniFiltrate(PrenotationFilter filtroPrenotazioni){
		ArrayList<PrenotazioneV2> filteredByChoice = new ArrayList<>();
		
		for(int i = 0; i < this.prenotazioni.size(); i++){
			if(filtroPrenotazioni.accept(this.prenotazioni.get(i))){
				filteredByChoice.add(this.prenotazioni.get(i));
			}
		}
		
		return filteredByChoice;
	}
	
	public void cancellaPrenotazioneCliente(Cliente c, Partita part){
		
		for(int i = 0; i < this.prenotazioni.size(); i++){
			if((this.prenotazioni.get(i).getBigliettoPrenotato().getCliente().equals(c)) &&
				this.prenotazioni.get(i).getBigliettoPrenotato().getPartita().equals(part)){
					
					this.prenotazioni.remove(i);
					i--;
			}
		}
	}
	
	public ArrayList<Acquisto> getAcquisti(){
		return this.acquisti;
	}
	
	public ArrayList<Acquisto> getAcquistiFiltrati(PurchasesFilter filtroAcquisti){
		ArrayList<Acquisto> filteredByChoice = new ArrayList<>();
		
		for(int i = 0; i < this.acquisti.size(); i++){
			if(filtroAcquisti.accept(this.acquisti.get(i))){
				filteredByChoice.add(this.acquisti.get(i));
			}
		}
		return filteredByChoice;
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
