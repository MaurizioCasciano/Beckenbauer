package struttura;

import java.io.Serializable;
import java.util.ArrayList;
import struttura.filters.Filter;
import user.AlreadyRegisteredUserException;
import user.UserNotFoundException;
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

	/**
	 * Aggiunge uno Stadio alla Struttura, se non già presente.
	 * @param s Lo stadio da aggiungere.
	 */
	public void addStadio(Stadio s) {
		
		if(!this.stadi.contains(s)){
			this.stadi.add(s);
		}
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

	/*
	 * Restituisce gli Stadi della Struttura.
	 */
	public ArrayList<Stadio> getStadi(){
		return this.stadi;
	}
	
	public void addUtente(Utente utente) throws AlreadyRegisteredUserException {
		for (Utente u : this.utenti) {
			if (u.equals(utente)) {
				throw new AlreadyRegisteredUserException();
			}
		}

		this.utenti.add(utente);
	}

	public Utente getUtente(String username) throws UserNotFoundException {
		for (Utente u : this.utenti) {
			if (u.getUsername().equalsIgnoreCase(username)) {
				return u;
			}
		}

		throw new UserNotFoundException();
	}

	private static final long serialVersionUID = -1014833830864079436L;
	private String nome;
	// una struttura sportiva che comprenda più stadi
	private ArrayList<Stadio> stadi;
	private ArrayList<Partita> partiteProgrammate;
	private ArrayList<Utente> utenti;
}
