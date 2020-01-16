import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will model a resource in a library
Author:  Linda Crane and xxxxxxxxxx
Course: F2018 - CST8130
Lab Section: xxxxxxxx
Data members:  title: String
               borrower: String
               dueDate: MyDate
               cost: float - over due charge
Methods: default constructor - uses intitialization  
         toString (): String - returns contents of the resource
         inputResource(Scanner, MyDate): boolean - reads a valid data from the Scanner parameter and the date
                           in paramter is today's date and returns through the boolean success or not
         isOverdue (MyDate): boolean - returns true/false if resource is overdue
         displayOverdue : - displays an overdue message when resource is returned (deleted)
*************************************************************************************************************/

public class Resource {
	protected String title = new String();
	protected String borrower = new String();
	protected MyDate dueDate = new MyDate();
	protected float cost = 0.0f;
	
	public Resource() {
	}
	
	public String toString() {
		return borrower + " has " + title+ " due on " + dueDate.toString() + " and if late " + cost + "\n";
	}
	
	public boolean inputResource(Scanner in, MyDate today) { 
		cost = 0.0f;
		dueDate = new MyDate(today);
		
		System.out.print ("Enter title being borrowed: ");
		title = in.next();
		
		System.out.print ("Enter borrower name (no spaces): ");
		borrower = in.next();
		
		
		
		return true;
	}
	
	public boolean isOverDue (MyDate todayDate) {
		return todayDate.isGreaterThan(dueDate);
			
	}
	
	public void displayOverDue() {
		System.out.print( " This resource is overdue...borrower owes "  + "$" + cost);
	}
	


}
