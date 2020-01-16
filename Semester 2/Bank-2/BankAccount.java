package lab5;

import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * Typifies a basic bank account, as a superclass for its subtypes. 
 * 
 * @author Brady McIntosh
 */
public abstract class BankAccount {
	
	/**
	 * The standard output object.
	 */
	static BankOutput bo;

	/**
	 * The account's given number.
	 */
	protected int accNumber;
	
	/**
	 * The amount of money in the account.
	 */
	protected double balance;
	
	/**
	 * The account's subtype.
	 */
	protected String accType;
	
	/**
	 * The customer object representing the account holder.
	 */
	protected Customer accHolder;
	
	private Scanner input = new Scanner(System.in);
	private DecimalFormat format = new DecimalFormat("$###,###,###.##");
	
	/**
	 * Prompts the user for all information relevant to the account, including sub-type.
	 * 
	 * @return Returns false if there are any unhandled exceptions. Returns true otherwise.
	 */
	public boolean addBankAccount() {
		
		try {
			bo.boPrintln("Please enter the information for this account.");
			
			// Prompts user for account number
			// Requires correct input to move forward
			boolean aTest = false;
			do {
				bo.boPrintln("Account number:");
				try {
					int i = Integer.parseInt(input.nextLine());
					bo.boFileOut(i);
					// if account number does not already exist
					if (Bank.searchAccounts(i) == -1) {
						accNumber = i;
						aTest = true;
					}
					// if account number does exist
					else {
						throw new ExistingAccountNumberException();
					}
				}
				catch (ExistingAccountNumberException e) {
					bo.boPrintln("That account number already exists.");
				}
				catch (Exception e) {
					bo.boPrintln("Please enter a valid number.");
				}
			} while(!aTest);
			
			// Prompts user for initial account balance
			// Requires correct input to move forward
			boolean bTest = false;
			do {
				bo.boPrintln("Initial balance:");
				try {
					balance = Double.parseDouble(input.nextLine());
					bo.boFileOut(balance);
					// if given balance is negative
					if (balance < 0) {
						bo.boPrintln("Error: Balance cannot be negative.");
						throw new Exception();
					}
					// if given balance is zero or positive
					bTest = true;
				}
				catch (Exception e) {
					bo.boPrintln("Please enter a valid amount.");
				}
				
			} while(!bTest);
			
			// temporary values for later initialization
			String fName = new String();
			String lName = new String();
			String email = new String();
			int pNum = 0;
			
			// Prompts user for first name
			// Requires correct input to move forward
			boolean fnTest = false;
			do {
				bo.boPrintln("First name of account holder:");
				try {
					fName = input.nextLine();
					bo.boFileOut(fName);
					fnTest = true;
				}
				catch (Exception e) {
					bo.boPrintln("Please enter a valid name.");
				}
			} while(!fnTest);
			
			// Prompts user for last name
			// Requires correct input to move forward
			boolean lnTest = false;
			do {
				bo.boPrintln("Last name of account holder:");
				try {
					lName = input.nextLine();
					bo.boFileOut(lName);
					lnTest = true;
				}
				catch (Exception e) {
					bo.boPrintln("Please enter a valid name.");
				}
			} while(!lnTest);
			
			// Prompts user for email address
			// Requires correct input to move forward
			boolean eTest = false;
			do {
				bo.boPrintln("Email address of account holder:");
				try {
					email = input.nextLine();
					bo.boFileOut(email);
					int atLoc = email.indexOf('@');
					int dotLoc = email.indexOf('.');
					if ((atLoc != -1 && dotLoc != -1) 
							&& (atLoc < dotLoc)) {
						eTest = true;
					}
					else {
						throw new Exception();
					}
				}
				catch (Exception e) {
					bo.boPrintln("Please enter a valid email.");
					bo.boPrintln("Emails have at least one '@' character,\n"
							+ "followed by at least one '.' character.");
				}
			} while(!eTest);
			
			// Prompts user for phone number
			// Requires correct input to move forward
			boolean pTest = false;
			do {
				bo.boPrintln("Phone number of account holder:");
				try {
					pNum = Integer.parseInt(input.nextLine());
					bo.boFileOut(pNum);
					pTest = true;
				}
				catch (Exception e) {
					bo.boPrintln("Please enter a valid phone number.");
				}
			} while(!pTest);
			
			// creates account-holder customer with temporary values
			accHolder = new Customer(fName, lName, email, pNum);
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Formats all the account information into three lines and returns it.
	 * 
	 * @return Returns the formatted account information.
	 */
	public String toString() {
		
		String ret = new String();
		
		ret = ret + accType + " " + accNumber + "\n";
		ret = ret + "Balance: " + format.format(balance) + "\n";
		ret = ret + accHolder.toString();
		
		return ret;
	}
	
	/**
	 * Adds the given amount to the account's balance.
	 * 
	 * @param amount The amount to be added.
	 */
	public void deposit(double amount) {
		
		balance += amount;
	}
	
	/**
	 * Subtracts the given amount from the account's balance.
	 * 
	 * @param amount The amount to be subtracted.
	 * @throws InsufficientFundsException If amount is greater than balance.
	 */
	public void withdraw(double amount) throws InsufficientFundsException {
		
		if(amount > balance) {
			throw new InsufficientFundsException();
		}
		
		balance -= amount;
	}
	
	public abstract void calculateAndUpdateBalance();
	
	/**
	 * Returns the account number corresponding to this account.
	 * 
	 * @return Returns this account's account number.
	 */
	public int getAccNumber() {
		
		return accNumber;
	}
}
