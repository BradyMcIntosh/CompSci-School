package lab5;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * A subtype of BankAccount with functionality for monthly interest rates and a
 * minimum balance.
 * 
 * @author Brady McIntosh
 */
public class SavingsAccount extends BankAccount {

	/**
	 * The monthly interest rate of this account.
	 */
	protected double monthlyInterestRate;
	
	/**
	 * The minimum balance in order for interest to be earned.
	 */
	protected double minBalance;

	private Scanner input = new Scanner(System.in);
	private DecimalFormat dollarFormat = new DecimalFormat("$###,###,###.##");
	private DecimalFormat percentFormat = new DecimalFormat("###.##%");
	
	public SavingsAccount(BankOutput bo) {
		SavingsAccount.bo = bo;
	}

	/**
	 * Prompts the user for all relevant member values.
	 */
	public boolean addBankAccount() {

		// Attempt the superclass' initialization class
		// If it returns false, this also returns false immediately
		if (!super.addBankAccount()) {
			return false;
		}

		try {
			monthlyInterestRate = 0;
			minBalance = 0;

			// Prompt user for the account's monthly interest rate, within a set range
			// Requires correct input to move forward
			boolean inTest = false;
			do {
				try {
					bo.boPrintln("Monthly interest rate:");
					monthlyInterestRate = Double.parseDouble(input.nextLine());
					bo.boFileOut(monthlyInterestRate);

					if (monthlyInterestRate < 0.0 || monthlyInterestRate > 1.0) {
						bo.boPrintln("Input out of range. Rate must be between 0.0 and 1.0.");
						throw new Exception();
					}
					inTest = true;
				} 
				catch (Exception e) {
					bo.boPrintln("Please enter a valid rate.");
				}
			} while (!inTest);

			// Prompt user for the account's minimum balance, within a set range
			// Requires correct input to move forward
			boolean minTest = false;
			do {
				try {
					bo.boPrintln("Minimum balance:");
					minBalance = Double.parseDouble(input.nextLine());
					bo.boFileOut(minBalance);

					if (minBalance < 5.0 || minBalance > 100.0) {
						bo.boPrintln("Input out of range. Minimum must be between 5.0 and 100.0.");
						throw new Exception();
					}
					minTest = true;
				} 
				catch (Exception e) {
					bo.boPrintln("Please enter a valid balance.");
				}
			} while (!minTest);

			accType = "Savings Account";

			return true;
		} 
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * Calculates and adds interest if the balance is greater than the minimum balance.
	 */
	public void calculateAndUpdateBalance() {

		if (balance > minBalance) {
			balance *= 1 + monthlyInterestRate;
		}
	}

	/**
	 * Formats and returns the account's monthly interest rate and minimum balance.
	 * 
	 * @return Returns the formatted interest rate and minimum balance.
	 */
	public String toString() {

		String s = "\nInterest rate: " + percentFormat.format(monthlyInterestRate * 100);

		s = s + "\nMinimum balance: " + dollarFormat.format(minBalance);

		return super.toString() + s;
	}
}
