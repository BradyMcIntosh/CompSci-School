import java.util.NoSuchElementException;
import java.util.Scanner;

/************************************************************************************************************
Purpose:  This class extends the Resource class for DVD-specific purposes
Author:  Brady McIntosh
Date: 	Dec 5 2018
Course: F2018 - CST8130
Lab Section: 312
Data members:  	type : String - the type of DVD
				DUE : int - the days after borrowing that the DVD is due
Methods: 	default constructor - calls Resource constructor, sets relevant cost and type field
			toString() : String - returns an appended summary of this DVD, with relevant new information
			toFile() : String - returns raw data in a file-readable format
			inputResource(Scanner, MyDate) : boolean - prompts user for member data, returns true
			fileResource(Scanner) : boolean - reads data from a file, returns validity of data
         

*************************************************************************************************************/

public class DVD extends Resource {
	
	protected String type;
	private static final int DUE = 3;
	
	public DVD() {
		super();
		
		overdueCost = 1;
		type = new String();
	}
	
	public String toString() {
		
		return String.format("The %s of %s", type, super.toString());
	}
	
	public String toFile() {
		
		return String.format("d %s %s", super.toFile(), type);
	}
	
	public boolean inputResource(Scanner scan, MyDate date) {
		
		super.inputResource(scan, date);
		
		System.out.print("Enter DVD type: ");
		if(scan.hasNext()) {
			type = scan.next();
		}
		
		dueDate = setDue(date, DUE);
		
		return true;
	}
	
	public boolean fileResource(Scanner line, byte lcount, String fullLine) {
		
		boolean success = super.fileResource(line, lcount, fullLine);
		
		if(success) {
			try {
				type = line.next();
			}
			catch(NoSuchElementException nse) {
				System.out.printf("Error at line #%d, line ends early: \n\t%s\n", lcount, fullLine);
				success = false;
			}
		}
		
		return success;
	}

}
