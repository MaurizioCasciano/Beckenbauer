package struttura;

import java.io.Serializable;
import java.util.ArrayList;
import struttura.filters.Filter;
import user.AlreadyRegisteredUserException;
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
	}

	/**
	 * Restituisce il nome della StrutturaSportiva.
	 * 
	 * @return Il nome della StrutturaSportiva.
	 */
	public String getNome() {
		return nome;
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

	private static final long serialVersionUID = -1014833830864079436L;
	private String nome;
	// una struttura sportiva che comprenda pi� stadi
	private ArrayList<Stadio> stadi;
	private ArrayList<Partita> partiteProgrammate;
	private ArrayList<Utente> utenti;
	private ArrayList<Sconti> sconti;
}
