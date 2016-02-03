package password;

import java.io.Serializable;

/**
 * Eccezione lanciata per indicare che la password scelta non rispecchia i
 * requisiti minimi di sicurezza.
 */
public class WeakPasswordException extends IllegalArgumentException implements Serializable {

	/**
	 * Indica che la password scelta non rispecchia i requisiti minimi di
	 * sicurezza.
	 */
	public WeakPasswordException() {
		super("Password troppo debole. Riprovare.");
	}

	/**
	 * Indica che la password scelta non rispecchia i requisiti minimi di
	 * sicurezza.
	 * 
	 * @param s
	 *            Il messaggio da mostrare.
	 */
	public WeakPasswordException(String s) {
		super(s);
	}

	private static final long serialVersionUID = -9052268614532615884L;
}
