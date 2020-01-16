package lab5;

/**
 * This is thrown if a bank account is found to have insufficient funds during
 * an attempted withdrawal.
 * 
 * @author Brady McIntosh
 * @author Angela Giddings
 */
public class InsufficientFundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundsException() {
		super("Insufficient funds available.");
	}

	public InsufficientFundsException(String message) {
		super(message);
	}

}
