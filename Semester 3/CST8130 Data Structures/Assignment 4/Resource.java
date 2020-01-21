import java.util.Scanner;
import java.util.NoSuchElementException;

/************************************************************************************************************
Purpose:  This class will model a prototypical library resource
Author:  Brady McIntosh
Date: 	Dec 5 2018
Course: F2018 - CST8130
Lab Section: 312
Data members:  	title : String - the title of the resource
				borrower : String - the name of the person/entity borrowing the resource
				dueDate : MyDate - the date the resource is due back
				overdueCost : float - the cost of returning this resource past it due date
				
Methods: 	default constructor - initializes member objects
			constructor(String) - initializes title of object only
			getHash(int) : int - returns the hashed value of this resource's title
			calcHash(String, int) : int - calculates and returns a hash based on a title and an arraySize
			toString() : String - returns a description, including all member data
			toFile() : String - returns raw member data in a file-readable format
			inputResource(Scanner, MyDate) : boolean - prompts user for member data, returns true
			fileResource(Scanner) : boolean - reads data from a single-line scanner, returns validity of data
			isOverdue(MyDate) : boolean - returns whether the passed date is larger than the internal duedate
			displayOverdue() - prints information regarding duedate and returning cost
			setDue(MyDate, int) : MyDate - returns a date incremented from passed date by passed int
         

*************************************************************************************************************/

public class Resource {
	
	protected String title;
	protected String borrower;
	protected MyDate dueDate;
	protected float overdueCost;
	
	public Resource() {
		title = new String();
		borrower = new String();
		dueDate = new MyDate();
	}
	
	public Resource(String title) {
		this.title = title;
	}
	
	public int getHash(int arrSize) {
		return calcHash(title, arrSize);
	}
	
	public static int calcHash(String title, int arrSize) {
		
		int hash = 0;
		
		for(int i = 0; i < title.length(); i++) {
			hash += title.toLowerCase().charAt(i) - 'a';
		}
		
		hash = hash % arrSize;
		
		return hash;
	}
	
	public String toString() {
		return String.format("%s borrowed by %s is due on %s, and it will cost $%.2f if returned late.",
				title, borrower, dueDate, overdueCost);
	}
	
	public String toFile() {
		return String.format("%s %s %s %.2f", title, borrower, dueDate.toFile(), overdueCost);
	}
	
	public boolean inputResource(Scanner scan, MyDate date) {
		
		System.out.print("Enter title (no spaces): ");
		if(scan.hasNext()) {
			title = scan.next();
		}
		System.out.print("Enter name of borrower (no spaces): ");
		if(scan.hasNext()) {
			borrower = scan.next();
		}
		
		return true;
	}
	
	public boolean fileResource(Scanner line, byte lcount, String fullLine) {
		
		boolean success = true;
			
		try {
			title = line.next();
			borrower = line.next();
			
			int month, day, year;
			
			// parse date from file and create new date object as due date
			month = Integer.parseInt(line.next());
			day = Integer.parseInt(line.next());
			year = Integer.parseInt(line.next());
			
			dueDate = new MyDate(year, month, day);
		}
		catch(NumberFormatException nfe) {
			System.out.printf("Error at line #%d, date format incorrect: \n\t%s\n", lcount, fullLine);
			success = false;
		}
		catch(NoSuchElementException nse) {
			System.out.printf("Error at line #%d, line ends early: \n\t%s\n", lcount, fullLine);
			success = false;
		}
		
		if(success) {
			try {
				overdueCost = Float.parseFloat(line.next());
			}
			catch(NumberFormatException nfe) {
				System.out.printf("Error at line #%d, price format incorrect: \n\t%s\n", lcount, fullLine);
				success = false;
			}
		}
		
		return success;
	}
	
	public boolean isOverdue(MyDate today) {
		
		return today.isGreaterThan(dueDate);
	}
	
	public boolean isEqual(Resource res) {
		return res.title.equals(this.title);
	}
	
	public void displayOverDue() {
		System.out.printf("This resource is overdue - $%.2f is owed by %s.\n", overdueCost, borrower);
	}
	
	protected MyDate setDue(MyDate date, int t) {
		
		//hacky "deep copy", as opposed to shallow copy
		// should replace with MyDate copy as in solution files
		
		String[] dates = date.toString().split("/");
		MyDate tempDate = new MyDate(Integer.parseInt(dates[0]),
				Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
		
		for(int i = 0; i < t; i++) {
			tempDate.addOne();
		}
		
		System.out.println("This will be due on: " + tempDate);
		
		return tempDate;
	}
	
}
