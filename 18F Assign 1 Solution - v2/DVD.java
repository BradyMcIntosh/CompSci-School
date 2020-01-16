import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will extend a Resource for a DVD resource
Author:  Linda Crane and xxxxxxxxxx
Course: F2018 - CST8130
Lab Section: xxxxxxxx
Data members:  type: String
Methods: default constructor - uses intitialization 
         toString (): String - returns this resources' info
         inputResource(Scanner, MyDate): boolean - reads a valid data from the Scanner parameter and the date
                           in paramter is today's date and returns through the boolean success or not.  Calls the 
                           superclass version - then fills in dueDate and cost to reflect values for dvd
         
*************************************************************************************************************/

public class DVD extends Resource{
	private String type = new String();

	public DVD() {
	
		}

	public String toString() {
		return " type of DVD : " + type + " " + super.toString();
	}
	
	public boolean inputResource(Scanner in, MyDate today) {
		if (!super.inputResource(in, today))
			return false;
		
		System.out.print ("Enter the type of DVD (no spaces): ");
		type = in.next();
		
		// due date for DVD is 3 days from "today"
		for (int i=0; i<3; i++)
			dueDate.addOne();
		
		// late cost is flat $1
		cost = 1.00f;
		
		
		return true;
	}

}
