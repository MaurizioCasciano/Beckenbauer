package user;

public class AlreadyRegisteredUser extends Exception {

	public AlreadyRegisteredUser() {
		super("Alrady Registered User");
	}

	public AlreadyRegisteredUser(String arg0) {
		super(arg0);
	}

	private static final long serialVersionUID = 5692728509720375264L;
}
