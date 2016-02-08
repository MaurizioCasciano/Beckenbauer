package struttura;

import java.io.Serializable;

/**
 * Eccezione usata per evitare un'ulteriore prenotazione in presenza di un
 * acquisto.
 * 
 * @author Maurizio
 */
public class PrenotationNotAllowedException extends RuntimeException implements Serializable {

	public PrenotationNotAllowedException() {
		super("Prenotazione non consentita!!!");
	}

	public PrenotationNotAllowedException(String arg0) {
		super(arg0);
	}

	private static final long serialVersionUID = -6939472173352130610L;
}
