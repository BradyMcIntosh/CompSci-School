import java.util.NoSuchElementException;
import java.util.Scanner;

/************************************************************************************************************
Purpose:  This class extends the Resource class for Magazine-specific purposes
Author:  Brady McIntosh
Date: 	Nov 1 2018
Course: F2018 - CST8130
Lab Section: 312
Data members:  	edition : MyDate - the issue or edition of the magazine
				DUE : int - the days after borrowing that the magazine is due
Methods: 	default constructor - calls Resource constructor, sets relevant cost and edition field
			toString() : String - returns an appended summary of this magazine, with relevant new information
			toFile() : String - returns raw data in a file-readable format
			inputResource(Scanner, MyDate) : boolean - prompts user for member data, returns true
			fileResource(Scanner) : boolean - reads data from a file, returns validity of data
         

*************************************************************************************************************/

public class Magazine extends Resource {
	
	protected MyDate edition;
	private static final int DUE = 7;
	
	public Magazine() {
		super();
		
		overdueCost = 1;
		edition = new MyDate();
	}
	
	public String toString() {
		
		return String.format("The %s edition of the magazine %s", edition, super.toString());
	}
	
	public String toFile() {
		
		return String.format("m %s %s", super.toFile(), edition.toFile());
	}
	
	public boolean inputResource(Scanner scan, MyDate date) {
		
		super.inputResource(scan, date);
		
		System.out.print("Enter edition date: ");
		edition.inputDate(scan);
		
		dueDate = setDue(date, DUE);
		
		return true;
		
	}
	
	public boolean fileResource(Scanner line, byte lcount, String fullLine) {
		
		boolean success = super.fileResource(line, lcount, fullLine);
		
		if(success) {
			try {
				int month = Integer.parseInt(line.next());
				int day = Integer.parseInt(line.next());
				int year = Integer.parseInt(line.next());
				
				edition = new MyDate(year, month, day);
			}
			catch(NumberFormatException nfe) {
				System.out.printf("Error at line #%d, date format incorrect: \n\t%s\n", lcount, fullLine);
				success = false;
			}
			catch(NoSuchElementException nse) {
				System.out.printf("Error at line #%d, line ends early: \n\t%s\n", lcount, fullLine);
				success = false;
			}
		}
		
		return success;
	}

}
