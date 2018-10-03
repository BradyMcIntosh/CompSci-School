package lab5;

/**
 * This is thrown if a bank account is found to have insufficient funds during a
 * scheduled, mandatory withdrawal (such as a monthly fee).
 * 
 * @author Brady McIntosh
 * @author Angela Giddings
 */
public class OverdrawnAccountException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OverdrawnAccountException() {
		super("This account has been overdrawn.");
	}

	public OverdrawnAccountException(String message) {
		super(message);
	}

}
