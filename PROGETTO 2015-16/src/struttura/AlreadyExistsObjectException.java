package struttura;

import java.io.Serializable;

public class AlreadyExistsObjectException extends RuntimeException implements Serializable{

	public AlreadyExistsObjectException(){
		super("Oggetto gia' esistente !");
	}
	
	public AlreadyExistsObjectException(String msg){
		super(msg);
	}
	
	private static final long serialVersionUID = -7042785196022609570L;

}
