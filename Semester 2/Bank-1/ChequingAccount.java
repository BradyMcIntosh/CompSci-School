/* Course: CST8132
 * Section: 312
 * Name: Brady McIntosh
 * Student Number: 040706980
 * Date: 28 Feb 2018
 */

package lab4;

/**
 * A bank account that is charged a set amount every month.
 * 
 * @author Brady McIntosh
 * @version 1.0
 */
public class ChequingAccount extends BankAccount {

	/**
	 * The amount charged per month.
	 */
	double monthlyFee = 3.99;

	/**
	 * Creates a ChequingAccount object and sets its balance to a given value.
	 * 
	 * @param val
	 *            The given value.
	 */
	ChequingAccount(double val) {

		super(val);
	}

	/**
	 * Subtracts the monthly fee from the balance.
	 */
	void calculateAndUpdateBalance() {

		balance -= monthlyFee;
	}
	
	String getType() {
		
		return "Chequing";
	}
}
