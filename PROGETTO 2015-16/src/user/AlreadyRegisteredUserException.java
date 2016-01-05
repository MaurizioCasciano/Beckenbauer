package user;

public class AlreadyRegisteredUserException extends Exception {

	public AlreadyRegisteredUserException() {
		super("Alrady Registered User");
	}

	public AlreadyRegisteredUserException(String arg0) {
		super(arg0);
	}

	private static final long serialVersionUID = 5692728509720375264L;
}
