import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/************************************************************************************************************
Purpose:  This class is the method main for Lab 1 
Author:  Linda Crane and Brady McIntosh
Course: F2018 - CST8130
Lab Section: 312
                                             
         

*************************************************************************************************************/


public class Lab2 {

	public static void main(String[] args) {
		
		String dir = System.getProperty("user.dir");
		
		Scanner keyboard = new Scanner (System.in);
		Scanner fileScan;
		Scanner inputScan = null;
		String inName = new String();
		File labFile;
		FileWriter outFile = null;
		
		boolean cont = false;
		boolean fromFile = false;
		boolean valid = false;
		
		do {
			int input = 0;
			DueDates dates = new DueDates();
			
			System.out.print("Would you like to read input from a file? (y/n)");
			
			boolean inCheck = false; 
			do {
				String s = keyboard.next();
				
				if(s.equals("n")) {
					inCheck = true;
					fromFile = false;
				}
				else if(s.equals("y")) {
					inCheck = true;
					fromFile = true;
				}
				else {
					System.out.print("Please type \"y\" for yes or \"n\" for no: ");
				}
			} while(!inCheck);
			
			if(fromFile) {
				
				inCheck = false;
				do {
					System.out.println("Current directory: " + dir + "\\src\\");
					System.out.print("Enter name of file (including extension) : ");
					String s = keyboard.next();
					inName = dir + "\\src\\" + s;
					System.out.println("File location: " + inName);
					labFile = new File(inName);
					
					/*
					 * public String whatever(Scanner keyboard) {
					 * 
					 * 		boolean valid = false;
					 * 		String in;
					 * 		
					 * 		do {
					 * 			try {
					 * 				File tempf = new File(in = keyboard.next());
					 * 				Scanner scanf = new Scanner(tempf);
					 * 				scanf.next();
					 * 				valid = true;
					 * 			}
					 * 			catch (IOException ioe) {
					 * 				System.out.print("That file does not exist! Please enter a valid file name");
					 * 			} 
					 * 		} while (!valid);
					 * 
					 * 		return in;
					 * 
					 * }
					 * 
					 */
					
					
					
					
					try {
						fileScan = new Scanner(labFile);
						if(fileScan.hasNextInt()) {
							
							int arrIn = fileScan.nextInt();
							if (arrIn > 0) {
								dates = new DueDates(arrIn);
								inputScan = fileScan;
								valid = true;
							}
							else {
								System.out.println("File must not contain negative numbers.");
								valid = false;
							}
							inCheck = true;
						}
						else {
							System.out.println("File must only contain numbers.");
							valid = false;
						}
					}
					catch (IOException ioe) {
						System.out.println("Could not open file " + s);
					}
				} while (!inCheck);
			}
			else {
				do {
					System.out.print("How many assessments in this course? (must be greater than 0) : ");
					
					if(keyboard.hasNextInt()) {
						int tempIn = keyboard.nextInt();
						
						if(tempIn > 0) {
							input = tempIn;
							dates = new DueDates(input);
							valid = true;
							inputScan = keyboard;
						}
					}
					else if(keyboard.next().equals("")) {
						input = 1;
					}
					
				} while(input <= 0);
			}
			
			if (valid) {
				// run input method
				valid = dates.inputDueDates(inputScan, fromFile? true : false);
				
				if (valid) {
					System.out.println("The due dates are: ");
					System.out.println(dates);
					
					System.out.println("The due dates with one day added are: ");
					dates.addOne();
					System.out.println(dates);
				}
				
				System.out.print("Do you want to write to a file? (y/n) : ");
				
				boolean write = false;
				inCheck = false; 
				do {
					String s = keyboard.next();
					
					if(s.equals("n")) {
						inCheck = true;
						write = false;
					}
					else if(s.equals("y")) {
						inCheck = true;
						write = true;
					}
					else {
						System.out.print("Please specify if you would like to continue. (y/n) : ");
					}
				} while(!inCheck);
				
				if (write) {
					String outName;
					
					System.out.print("Enter a file name (no extension): ");
					inCheck = false;
					do {
						outName = dir + "\\src\\" + keyboard.next() + ".txt";
						if(outName.equals(inName)) {
							System.out.print("Please enter a unique file name: ");
						}
						else {
							inCheck = true;
						}
					} while (!inCheck);
					
					try {
						outFile = new FileWriter(outName);
						outFile.append(dates.toFile());
						outFile.close();
					}
					catch (IOException ioe) {
						System.out.println("Could not open file.");
					}
				}
			}
				
			System.out.print("Do another? (y/n) : ");
			
			inCheck = false; 
			do {
				String s = keyboard.next();
				
				if(s.equals("n")) {
					System.out.println("Goodbye.");
					inCheck = true;
					cont = false;
				}
				else if(s.equals("y")) {
					inCheck = true;
					cont = true;
				}
				else {
					System.out.print("Please specify if you would like to continue. (y/n) : ");
				}
			} while(!inCheck);
		} while(cont);
		
		keyboard.close();
	}
	
}

