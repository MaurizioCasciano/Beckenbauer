package user;

import java.io.Serializable;

public class AlreadyRegisteredUserException extends Exception implements Serializable{

	public AlreadyRegisteredUserException() {
		super("Already Registered User");
	}

	public AlreadyRegisteredUserException(String arg0) {
		super(arg0);
	}

	private static final long serialVersionUID = 5692728509720375264L;
}
