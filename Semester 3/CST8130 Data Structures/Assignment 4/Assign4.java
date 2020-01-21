import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/************************************************************************************************************
 * Purpose: This class hosts a menu for interacting with a simple Library object. 
 * Author: Brady McIntosh 
 * Date: Dec 5 2018
 * Course: F2018 - CST8130 Lab
 * Section: 312 
 * 
 *************************************************************************************************************/

public class Assign4 {

	public static void main(String[] args) {

		Library lib = new Library();
		Scanner scan = new Scanner(System.in);
		MyDate today = new MyDate();
		boolean cont = true;
		String command = "";

		System.out.println("Please enter today's date:");
		today.inputDate(scan);

		do {
			System.out.println("Select an option.");
			System.out.println("\t1. Borrow a resource \n\t2. Display all borrowed resources"
					+ "\n\t3. Display overdue resources \n\t4. Return a resource"
					+ "\n\t5. Change today's date \n\t6. Read the contents of a file"
					+ "\n\t7. Save this library to a file"
					+ "\n\t8. Display a specific resource \n\t9. Quit");

			command = scan.next();

			if (command.equals("1")) {
				lib.inputResource(scan, today);
				
			} else if (command.equals("2")) {
				System.out.println(lib);
				
			} else if (command.equals("3")) {
				System.out.println(lib.resourcesOverDue(today));
				
			} else if (command.equals("4")) {
				lib.deleteResource(scan, today);
				
			} else if (command.equals("5")) {
				System.out.println("Please enter today's date:");
				today.inputDate(scan);
				
			} else if (command.equals("6")) {
				System.out.println("Enter the name of the file.");
				Scanner uifile = null;
				try {
					uifile = new Scanner(new File(fileName(scan)));
				}
				catch (FileNotFoundException fnf) {
					System.out.println("File not found!");
				}
				if(null != uifile) {
					if(lib.fileResource(uifile)) {
						System.out.println("File read successfully!");
					}
					else {
						System.out.println("File reading has ended prematurely.");
					}
					uifile.close();
				}
			} else if (command.equals("7")) {

				System.out.println("Enter the name of the new file: ");
				FileWriter outfile;
				try {
					outfile = new FileWriter(scan.next());
					outfile.append(lib.toFile());
					outfile.close();
					System.out.println("File writing successful!");
				}
				catch (IOException ioe) {
					System.out.println("There was a problem writing to file.");
				}
				
			} else if (command.equals("8")) {
				System.out.println("Please enter the resource's title:");
				lib.find(scan.next());
				
			} else if (command.equals("9")) {
				System.out.println("Goodbye.");
				cont = false;
			}

		} while (cont);

	}

	private static String fileName(Scanner keyboard) {

		boolean valid = true;
		String uinput = keyboard.next();
		do {
			try {
				File fileenter = new File(uinput);
				Scanner filescan = new Scanner(fileenter);
				filescan.next();
				filescan.close();
			} catch (IOException ioe) {
				System.out.println("That is not a valid file. Try again.");
			}
		} while (valid != true);

		return uinput;
	}

}
