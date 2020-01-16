import java.util.Scanner;

/************************************************************************************************************
Purpose:  This class will model a prototypical library resource
Author:  Brady McIntosh
Date: 	Oct 4 2018
Course: F2018 - CST8130
Lab Section: 312
Data members:  	title : String - the title of the resource
				borrower : String - the name of the person/entity borrowing the resource
				dueDate : MyDate - the date the resource is due back
				overdueCost : float - the cost of returning this resource past it due date
				
Methods: 	default constructor - initializes member objects
			toString() : String - returns a description, including all member data
			inputResource(Scanner, MyDate) : boolean - prompts user for member data, returns true
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
	
	public String toString() {
		return String.format("%s borrowed by %s is due on %s, and it will cost $%.2f if returned late.",
				title, borrower, dueDate, overdueCost);
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
	
	public boolean isOverdue(MyDate today) {
		
		return today.isGreaterThan(dueDate);
	}
	
	public void displayOverDue() {
		System.out.printf("This resource is overdue - $%.2f is owed by %s.\n", overdueCost, borrower);
	}
	
	protected MyDate setDue(MyDate date, int t) {
		
		//hacky "deep copy", as opposed to shallow copy
		
		
//		date.toString() == "2005/06/01"
//		date.toString().split("/") == {"2005","06","01"};
//		Integer.parseInt(split[0]) == 2005
		
		
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
