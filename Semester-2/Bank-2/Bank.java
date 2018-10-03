package lab5;

import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * Holds an array of BankAccounts, and allows them to be created, manipulated
 * and displayed.
 * 
 * @author Brady McIntosh
 * @author Angela Giddings
 */
public class Bank {

	private String bankName;

	private static BankOutput bo;
	
	/**
	 * Scanner object for reading user input.
	 */
	protected static Scanner input = new Scanner(System.in);

	/**
	 * Array of BankAccount objects and its subtypes.
	 */
	protected static BankAccount[] accounts;

	/**
	 * The number of accounts that have been created.
	 */
	protected static int numAccounts = 0;

	/**
	 * The maximum number of accounts that can be created.
	 */
	protected static int bankSize = 100;
	
	private DecimalFormat dollarFormat = new DecimalFormat("$###,###,###.##");

	/**
	 * Name-only constructor. Initializes bankName and leaves bankSize alone.
	 * 
	 * @param bankName
	 */
	public Bank(String bankName, BankOutput bo) {
		this.bankName = bankName;
		Bank.bo = bo;
		accounts = new BankAccount[bankSize];
	}

	/**
	 * Full member constructor. Initializes bankName and re-initializes bankSize.
	 * 
	 * @param bankName
	 * @param bankSize
	 */
	public Bank(String bankName, int bankSize, BankOutput bo) {
		this.bankName = bankName;
		Bank.bankSize = bankSize;
		Bank.bo = bo;
		accounts = new BankAccount[bankSize];
	}

	/**
	 * Returns the name of the bank.
	 * 
	 * @return Returns the bank's given name.
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * Creates a bank account using information prompted from the user.
	 */
	public void addBankAccount() {

		// Does nothing if there is already (bankSize) number of accounts
		if (numAccounts >= bankSize) {

			bo.boPrintln("Error: Bank account limit reached.");
			return;
		}

		// stores user input, for convenience
		char option;

		// Prompts user for type of account (chequing or savings)
		// Requires correct input to move forward
		do {
			bo.boPrintln("Choose which type of bank account...");
			bo.boPrintln("\"C\"hequing or \"S\"avings");
			option = input.nextLine().toLowerCase().charAt(0);
			bo.boFileOut(option);

			// Initializes corresponding type of BankAccount
			if (option == 'c') {
				accounts[numAccounts] = new ChequingAccount(bo);
			} 
			else if (option == 's') {
				accounts[numAccounts] = new SavingsAccount(bo);
			} 
			else {
				bo.boPrintln("Invalid input.");
			}

			// Attempts to add an account - if attempt fails, print error
			if (accounts[numAccounts].addBankAccount()) {
				bo.boPrintln("Account added.");
				numAccounts++;
				bo.boPrintln("Number of accounts: " + numAccounts);
			} 
			else {
				bo.boPrintln("Error: Account could not be created.");
			}

		} while (option != 'c' && option != 's');
	}

	/**
	 * Displays the bank account specified by the user.
	 */
	public void displayAccount() {

		// If any accounts exist, prompt user for account number,
		// and display corresponding account.
		if (numAccounts > 0) {
			try {
				bo.boPrintln(accounts[findAccount()].toString());
			} 
			catch (ArrayIndexOutOfBoundsException a) {
				bo.boPrintln("Sorry, that account does not exist.");
			} 
			catch (Exception e) {
				bo.boPrintln(e);
			}
		}
		// If no accounts exist, print an error
		else {
			bo.boPrintln("Error: No accounts exist.");
		}
	}

	/**
	 * Deposits to or withdraws from a bank account, according to user input.
	 */
	public void updateAccount() {

		// if any accounts exist
		if (numAccounts > 0) {
			int acc = -1;

			// Prompts the user for an account number
			// Requires correct input to move forward
			do {
				try {
					acc = findAccount();
				} 
				catch (ArrayIndexOutOfBoundsException a) {
					bo.boPrintln("Sorry, that account does not exist.");
				} 
				catch (Exception e) {
					bo.boPrintln(e);
				}
			} while (acc == -1);
			
			bo.boPrintln(accounts[acc].accType + accounts[acc].getAccNumber() 
				+ "\nBalance: " + dollarFormat.format(accounts[acc].balance));

			boolean typeTest = false;
			char option = 'x';

			// Prompts the user for the kind of transaction (ie withdrawal or deposit)
			// Requires correct input to move forward
			do {
				bo.boPrintln("\"W\"ithdrawal or \"D\"eposit?");
				try {
					option = input.nextLine().toLowerCase().charAt(0);
					bo.boFileOut(option);
					if (option != 'w' && option != 'd') {
						throw new Exception();
					}
				}
				catch (Exception e) {
					bo.boPrintln("Error: Invalid input.");
					continue;
				}
				typeTest = true;
			} while (!typeTest);
			
			boolean amTest = false;
			double amnt = 0;
			
			// Prompts the user for the amount to transact
			// Requires correct input to move forward
			do {
				bo.boPrint("Please specify the amount to ");
				bo.boPrintln(option == 'd'? "deposit:": "withdraw:");
				try {
					amnt = Double.parseDouble(input.nextLine());
					bo.boFileOut(amnt);
					if (amnt == 0) {
						bo.boPrintln("So you're a wise guy, huh?");
						continue;
					}
				}
				catch (Exception e) {
					bo.boPrintln("Error: Invalid input.");
					continue;
				}
				amTest = true;
			} while (!amTest);
			
			if (option == 'w') {
				
				try {
					accounts[acc].withdraw(amnt);
				}
				catch (InsufficientFundsException is) {
					bo.boPrintln("Error: Insufficient funds.");
				}
			}
			else {
				
				accounts[acc].deposit(amnt);
			}
		}
		// if no accounts exist
		else {
			bo.boPrintln("Error: No accounts exist.");
		}
	}

	/**
	 * Cycles through each account (if any exist) and performs its respective
	 * monthly update.
	 */
	public void monthlyUpdate() {

		// if any accounts exist
		if (numAccounts > 0) {
			for (int i = 0; i < accounts.length; i++) {
				if(accounts[i] != null) {
					accounts[i].calculateAndUpdateBalance();
				}
			}
		}
		// if no accounts exist
		else {
			bo.boPrintln("Error: No accounts exist.");
		}
	}

	/**
	 * Prompts the user for an account number and passes the input as a parameter to
	 * searchAccounts().
	 * 
	 * @return Returns the result of searchAccounts().
	 */
	public int findAccount() {

		int accToFind = 0;
		boolean numTest = false;

		// Prompts user for an account number
		// Requires valid input to move forward
		do {
			try {
				bo.boPrintln("Please enter the account number.");
				accToFind = Integer.parseInt(input.nextLine());
				bo.boFileOut(accToFind);
				numTest = true;
			} catch (Exception e) {
				bo.boPrintln("Error: Invalid input.");
			}
		} while (!numTest);

		return searchAccounts(accToFind);
	}

	/**
	 * Looks for the account (if any exist) corresponding to the given account
	 * number, and returns its index, if found.
	 * 
	 * @param accToFind
	 *            The given account number.
	 * @return Returns the accounts[] index if the account exists. Returns -1
	 *         otherwise.
	 */
	public static int searchAccounts(int accToFind) {

		// Cycles the full accounts array and tests each corresponding accNumber
		// Only starts searching if at least one account has been created
		if (numAccounts > 0) {
			for (int i = 0; i <= numAccounts - 1; i++) {

				try {
					if (accounts[i].accNumber == accToFind) {
						return i;
					}
				} catch (Exception e) {
					bo.boPrintln(e);
				}
			}
		}

		return -1;
	}
}
