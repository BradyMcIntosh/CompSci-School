package lab5;

/**
 * This is thrown if an account number given in the creation of a new account
 * already exists in another account.
 * 
 * @author Brady McIntosh
 * @author Angela Giddings
 */
public class ExistingAccountNumberException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistingAccountNumberException() {
		super("This account number has already been chosen.");
	}

	public ExistingAccountNumberException(String message) {
		super(message);
	}

}
