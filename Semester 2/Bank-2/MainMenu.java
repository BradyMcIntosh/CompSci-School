package lab5;

import java.util.Scanner;

/**
 * The main menu for interacting with the bank simulator class.
 * 
 * @author Brady McIntosh
 * @author Angela Giddings
 */
public class MainMenu {
	
	public static BankOutput bo;

	/**
	 * Prompts for bank and account information, then loops through user actions until the user quits.
	 */
	public static void main(String[] args) {
		
		// prints an output file to the public downloads directory
		bo = new BankOutput();
		
		Scanner input = new Scanner(System.in);
		
		String name = new String();
		int size = 0;
		
		// Prompts for name of bank
		// Requires correct input to move forward
		do {
			try {
				bo.boPrintln("Please enter the name of this bank.");
				name = input.nextLine();
				bo.boFileOut(name);
			}
			catch (Exception e) {
				bo.boPrintln("Invalid input.");
				bo.boPrintln(e);
			}
		} while(name.equals(""));
		
		// Prompts for max number of accounts
		// Requires correct input to move forward
		do {
			try {
				bo.boPrintln("Please enter the maximum number of accounts.");
				size = Integer.parseInt(input.nextLine());
				bo.boFileOut(size);
				if (size < 1) {
					throw new Exception();
				}
			}
			catch (Exception e) {
				bo.boPrintln("Invalid input.");
			}
		} while (size < 1);
		
		Bank bank = new Bank(name, size, bo);
		
		bo.boPrintln("Welcome to the Bank of " + bank.getBankName());
		
		char option = 'x';
		
		do {
			
			displayMenu();
			
			option = input.nextLine().toLowerCase().charAt(0);
			bo.boFileOut(""+option);
			
			switch (option) {
			
				case 'a':
					bo.boPrintln("You have chosen to add an account.");
					bank.addBankAccount();
					break;
					
				case 'd':
					bo.boPrintln("You have chosen to display an account.");
					bank.displayAccount();
					break;
					
				case 'u':
					bo.boPrintln("You have chosen to update an account.");
					bank.updateAccount();
					break;
					
				case 'm':
					bo.boPrintln("You have chosen to perform a monthly update.");
					bank.monthlyUpdate();
					break;
					
				case 'q':
					break;

				default:
					
					bo.boPrintln("Please enter a valid option.");
			}
			
			
		} while (option != 'q');
		
		bo.boPrintln("Have a good day!");
		bo.boClose();
		
		input.close();
	}
	
	/**
	 * Displays all the available options to the user.
	 */
	public static void displayMenu() {
		
		bo.boPrintln("Enter your choice:");
		bo.boPrintln("a: Add new account");
		bo.boPrintln("d: Display account details");
		bo.boPrintln("u: Update account balance");
		bo.boPrintln("m: Month-end update");
		bo.boPrintln("q: Quit");
		
	}
}
