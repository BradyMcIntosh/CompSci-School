/* Course: CST8132
 * Section: 312
 * Name: Brady McIntosh
 * Student Number: 040706980
 * Date: 28 Feb 2018
 */

package lab4;

/**
 * A bank account that accrues interest over time.
 * 
 * @author Brady McIntosh
 * @version 1.0
 */
public class SavingsAccount extends BankAccount {

	/**
	 * The annual rate of interest.
	 */
	private double annualInterestRate = 0.07;

	/**
	 * Constructs a SavingsAccount object and sets its balance to a given value.
	 * 
	 * @param val
	 *            The given value.
	 */
	SavingsAccount(double val) {

		super(val);
	}

	/**
	 * Accrues monthly interest on the balance, based on the annual rate.
	 */
	void calculateAndUpdateBalance() {

		balance *= (annualInterestRate / 12) + 1;
	}
	
	String getType() {
		
		return "Savings";
	}
}
