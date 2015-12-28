package user;

import java.io.Serializable;
import java.util.ArrayList;

import password.WeakPasswordException;
import struttura.Acquisto;
import struttura.Prenotazione;

public class Cliente extends Utente implements Serializable {

	/**
	 * Crea un nuovo Cliente di un sistema informatico.
	 * 
	 * @param nome
	 *            Il nome del Cliente.
	 * @param cognome
	 *            Il cognome del Cliente.
	 * @param username
	 *            L'username del Cliente.
	 * @param password
	 *            La password del Cliente, utilizzata per accedere ad un sistema
	 *            informatico.
	 * @throws WeakPasswordException
	 *             Indica che la password scelta non rispecchia i requisiti
	 *             minimi di sicurezza.
	 */
	public Cliente(String nome, String cognome, String username, String password) throws WeakPasswordException {
		super(nome, cognome, username, password);
	}

	public ArrayList<Prenotazione> getPrenotazioniEffettuate() {
		return prenotazioniEffettuate;
	}

	public ArrayList<Acquisto> getAcquistiEffettuati() {
		return acquistiEffettuati;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	private static final long serialVersionUID = -3154746431526711515L;
	private ArrayList<Prenotazione> prenotazioniEffettuate;
	private ArrayList<Acquisto> acquistiEffettuati;
}
