package user;

/**
 * Segnala che l'Utente cercato non è presente nel sistema.
 * 
 * Può essere lanciata in seguito alla ricerca di un Utente (per username o
 * secondo un qualsiasi altro criterio).
 */
public class UserNotFound extends Exception {

	public UserNotFound() {
		super("User Not Found");
	}

	public UserNotFound(String arg0) {
		super(arg0);
	}

	private static final long serialVersionUID = 8461886816004240704L;
}
