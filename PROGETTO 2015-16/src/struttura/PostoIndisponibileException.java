package struttura;

import java.io.Serializable;

public class PostoIndisponibileException extends Exception implements Serializable{

	public PostoIndisponibileException(){
		super("Il Posto selezionato non è disponbile !!!");
	}
	
	public PostoIndisponibileException(String msg){
		super(msg);
	}
	
	private static final long serialVersionUID = -4354382832354255766L;
}
