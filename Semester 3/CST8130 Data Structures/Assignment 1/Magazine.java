import java.util.Scanner;

/************************************************************************************************************
Purpose:  This class extends the Resource class for Magazine-specific purposes
Author:  Brady McIntosh
Date: 	Oct 4 2018
Course: F2018 - CST8130
Lab Section: 312
Data members:  	edition : MyDate - the issue or edition of the magazine
				DUE : int - the days after borrowing that the magazine is due
Methods: 	default constructor - calls Resource constructor, sets relevant cost and edition field
			toString() : String - returns an appended summary of this magazine, with relevant new information
			inputResource(Scanner, MyDate) : boolean - prompts user for member data, returns true
         

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
	
	public boolean inputResource(Scanner scan, MyDate date) {
		
		super.inputResource(scan, date);
		
		System.out.print("Enter edition date: ");
		edition.inputDate(scan);
		
		dueDate = setDue(date, DUE);
		
		return true;
		
	}

}
