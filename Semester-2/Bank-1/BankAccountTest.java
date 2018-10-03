/* Course: CST8132
 * Section: 312
 * Name: Brady McIntosh
 * Student Number: 040706980
 * Date: 28 Feb 2018
 */

package lab4;

import java.util.Random;

/**
 * Tests a set of bank accounts with random values.
 * 
 * @author Brady McIntosh
 * @version 1.1
 */
public class BankAccountTest {
	
	private BankAccount[] accounts;

	/**
	 * Constructs this object, defines an array of abstract accounts,
	 * and instantiates a set of specific accounts in their place.
	 */
	BankAccountTest() {
		
		Random rand = new Random();

		// four uninitialized, abstract account objects
		accounts = new BankAccount[4];

		// initializing  SavingsAccounts
		accounts[0] = new SavingsAccount(100 * rand.nextDouble());
		accounts[1] = new SavingsAccount(100 * rand.nextDouble());

		// initializing ChequingAccounts
		accounts[2] = new ChequingAccount(100 * rand.nextDouble());
		accounts[3] = new ChequingAccount(100 * rand.nextDouble());
	}

	/**
	 * Cycles through each account in a given array and 
	 * calculates their monthly updates.
	 * 
	 * @param accounts The given array of accounts.
	 */
	private void monthlyProcess(BankAccount[] accounts) {

		for (BankAccount acc : accounts) {

			acc.calculateAndUpdateBalance();
		}
	}

	/**
	 * Cycles through each account in a given array and prints their balances.
	 * 
	 * @param accounts The given array of accounts.
	 */
	private void display(BankAccount[] accounts) {
		
		for (int i = 0; i < accounts.length; i++) {

			// prints account number and account type
			System.out.print("\tAccount #" + i + ", " + accounts[i].getType() + ": ");
			
			// prints account balance
			System.out.println((int)(accounts[i].getBalance() * 100) / 100.0);
		}
	}

	/**
	 * Performs 12 cycles of the monthly commands,
	 * simulating a year of activity.
	 * 
	 * @param args Takes args.
	 */
	public static void main(String[] args) {

		BankAccountTest runtime = new BankAccountTest();

		for (int i = 0; i < 12; i++) {
			
			// header for each month
			System.out.println("Balances for Month " + (i+1));
			
			// performs monthly processes, then prints them
			runtime.monthlyProcess(runtime.accounts);
			runtime.display(runtime.accounts);
		}
	}
}
