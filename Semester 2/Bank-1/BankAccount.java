/* Course: CST8132
 * Section: 312
 * Name: Brady McIntosh
 * Student Number: 040706980
 * Date: 28 Feb 2018
 */

package lab4;

/**
 * Defines a basic bank account.
 * 
 * @author Brady McIntosh
 * @version 1.0
 */
public abstract class BankAccount {

	/**
	 * The balance held in the account.
	 */
	protected double balance;

	/**
	 * Constructs a BankAccount object and sets the balance to a given value.
	 * 
	 * @param val
	 *            The given value.
	 */
	BankAccount(double val) {

		balance = val;
	}

	/**
	 * Returns the value of balance.
	 * 
	 * @return Returns the balance value of this object.
	 */
	double getBalance() {

		return balance;
	}

	/**
	 * Empty calculation and update function.
	 */
	abstract void calculateAndUpdateBalance();
	
	/**
	 * Returns the type of account.
	 * 
	 * @return Returns account type as String.
	 */
	abstract String getType();
}
