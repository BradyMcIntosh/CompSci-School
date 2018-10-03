package lab5;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * A subtype of BankAccount with functionality for monthly fees.
 * 
 * @author Brady McIntosh
 */
public class ChequingAccount extends BankAccount {
	
	/**
	 * The amount of money withdrawn at every monthly update.
	 */
	protected double monthlyFee;
	
	private Scanner input = new Scanner(System.in);
	private DecimalFormat format = new DecimalFormat("$###,###,###.##");
	
	public ChequingAccount(BankOutput bo) {
		ChequingAccount.bo = bo;
	}
	
	/**
	 * Prompts the user for all relevant member values.
	 */
	public boolean addBankAccount() {
		
		// Attempt the superclass' initialization class
		// If it returns false, this also returns false immediately
		if(!super.addBankAccount()) {
			return false;
		}
		
		// Prompt user for the account's monthly fee, within a set range
		// Requires correct input to move forward
		boolean monTest = false;
		try {
			monthlyFee = 0;
			do {
				bo.boPrintln("Monthly fee: ");
				try {
					monthlyFee = Double.parseDouble(input.nextLine());
					bo.boFileOut(monthlyFee);
					
					if(monthlyFee < 5.0 || monthlyFee > 10.0) {
						bo.boPrintln("Input out of range.\n"
								+ "Fee must be between 5.0 and 10.0." );
						throw new Exception();
					}
					monTest = true;
				}
				catch (Exception e) {
					bo.boPrintln("Please enter a valid amount.");
				}
			}
			while (!monTest);
			
			accType = "Chequing Account";
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Attempts to subtract the monthly fee from the balance.
	 */
	public void calculateAndUpdateBalance() {
		try {
			balance -= monthlyFee;
			
			if(balance < 0) {
				throw new OverdrawnAccountException();
			}
		}
		catch (OverdrawnAccountException o) {
			bo.boPrintln("Error: Account " + accNumber + " is overdrawn.");
		}
		catch (Exception e) {
			bo.boPrintln(e);
		}
	}
	
	/**
	 * Formats and returns the account's given monthly fee value.
	 * 
	 * @return Returns the formatted monthly fee.
	 */
	public String toString() {
		
		return super.toString() + "\nMonthly fee: " + format.format(monthlyFee);
	}
}
