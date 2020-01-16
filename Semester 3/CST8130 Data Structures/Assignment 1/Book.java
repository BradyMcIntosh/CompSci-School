import java.util.Scanner;

/************************************************************************************************************
Purpose:  This class extends the Resource class for Book-specific purposes
Author:  Brady McIntosh
Date: 	Oct 4 2018
Course: F2018 - CST8130
Lab Section: 312
Data members:  	author : String - the author of the book
				DUE : int - the days after borrowing that the book is due
Methods: 	default constructor - calls Resource constructor, sets relevant cost and author field
			toString() : String - returns an appended summary of this book, with relevant new information
			inputResource(Scanner, MyDate) : boolean - prompts user for member data, returns true
         

*************************************************************************************************************/

public class Book extends Resource {
	
	protected String author;
	private static final int DUE = 14;
	
	public Book() {
		super();
		overdueCost = 2;
		
		author = new String();
	}
	
	public String toString() {
		
		return String.format("Written by %s, the book %s", author, super.toString());
	}
	
	public boolean inputResource(Scanner scan, MyDate date) {
		
		super.inputResource(scan, date);
		
		System.out.print("Enter author: ");
		if(scan.hasNext()) {
			author = scan.next();
		}
		
		dueDate = setDue(date, DUE);
		
		return true;
	}

}
