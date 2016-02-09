package struttura;

import java.io.Serializable;

/**
 * Eccezione che notifica all'utente che il posto selezionato non e' disponibile
 * 
 * @author Gaetano Antonucci
 */
public class PostoIndisponibileException extends Exception implements Serializable{
	
	/**
	 * Costruttore con messaggio di default
	 * 
	 * @author Gaetano Antonucci
	 */
	public PostoIndisponibileException(){
		super("Il Posto selezionato non e' disponbile !!!");
	}
	
	/**
	 * Costruttore con messaggio passato tramite parametro
	 * 
	 * @param msg - il messaggio che si desidera visualizzare quando viene sollevata l'eccezione
	 * @author Gaetano Antonucci
	 */
	public PostoIndisponibileException(String msg){
		super(msg);
	}
	
	private static final long serialVersionUID = -4354382832354255766L;
}
