package struttura;

import java.io.Serializable;

/**
 * Questa eccezione segnala all'utente che l'oggetto che si cerca di creare e' gia' esistente
 * 
 * @author Gaetano Antonucci
 *
 */
public class AlreadyExistsObjectException extends RuntimeException implements Serializable{

	/**
	 * Costruttore con messaggio di default.
	 * 
	 * @author Gaetano Antonucci
	 */
	public AlreadyExistsObjectException(){
		super("Oggetto gia' esistente !");
	}
	
	/**
	 * Costruttore con messaggio da impostare
	 * 
	 * @param msg - il messaggio che esplicita il tipo di oggetto sul quale e' stata lanciata l'eccezione
	 * @author Gaetano Antonucci
	 */
	public AlreadyExistsObjectException(String msg){
		super(msg);
	}
	
	private static final long serialVersionUID = -7042785196022609570L;

}
