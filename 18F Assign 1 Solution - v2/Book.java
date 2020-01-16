import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will extend a Resource for a book resource
Author:  Linda Crane and xxxxxxxxxx
Course: F2018 - CST8130
Lab Section: xxxxxxxx
Data members:  author: String
Methods: default constructor - uses intitialization 
         toString (): String - returns this resources' info
         inputResource(Scanner, MyDate): boolean - reads a valid data from the Scanner parameter and the date
                           in paramter is today's date and returns through the boolean success or not.  Calls the 
                           superclass version - then fills in dueDate and cost to reflect values for book
         
*************************************************************************************************************/

public class Book extends Resource{
	private String author = new String();
	
	public Book() {
		
	}
	
	public String toString() {
		return " author " + author + " " + super.toString();
	}
	
	public boolean inputResource(Scanner in, MyDate today) {
		if (!super.inputResource(in, today))
			return false;
		
		System.out.print ("Enter the author name (no spaces): ");
		author = in.next();
		
		// due date for magazine is 14 days from "today"
		for (int i=0; i<14; i++)
			dueDate.addOne();
		
		// late cost is flat $2
		cost = 2.00f;
		
		
		return true;
	}

}
