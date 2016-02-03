package user;

import java.io.Serializable;

import password.WeakPasswordException;

/**
 * Classe che modella un gestore di un sistema informatico.
 * 
 * @author Maurizio
 */
public class Gestore extends Utente implements Serializable {

	/**
	 * Crea un nuovo Gestore di un sistema informatico.
	 * 
	 * @param nome
	 *            Il nome del Gestore.
	 * @param cognome
	 *            Il cognome del Gestore.
	 * @param username
	 *            L'username del Gestore, utilizzato per accedere ad un sistema
	 *            informatico.
	 * @param password
	 *            La password del Gestore, utilizzata per accedere ad un sistema
	 *            informatico.
	 * @throws WeakPasswordException
	 *             Indica che la password scelta non rispecchia i requisiti
	 *             minimi di sicurezza.
	 */
	public Gestore(String nome, String cognome, String username, String password) throws WeakPasswordException {
		super(nome, cognome, username, password);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	private static final long serialVersionUID = -6300659179686656153L;
}
