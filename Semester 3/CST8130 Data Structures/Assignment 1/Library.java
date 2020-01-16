import java.util.Scanner;

/************************************************************************************************************
Purpose:  This class will model and manage a library containing a dynamic array of multiple resource objects
Author:  Brady McIntosh
Date: 	Oct 4 2018
Course: F2018 - CST8130
Lab Section: 312
Data members:  	resourcesBorrowed : Resource[] - array of resources
				numResources : int - the amount of real values inside the array
				max : int - the memory size of the array
				
Methods: 	default constructor - initializes member objects, sets max
			toString() : String - returns a description, including all member data
			inputResource(Scanner, MyDate) : boolean - prompts user for member data, returns true
			resourcesOverDue(MyDate) : String - returns a summary-list of all overdue resources
			deleteResource(Scanner, MyDate) - prompts user for selection to delete resource, re-allocates
												empty space
			
         

*************************************************************************************************************/

public class Library {

	private Resource[] resourcesBorrowed;
	private int numResources;
	private int max;
	
	public Library() {
		max = 10;
		resourcesBorrowed = new Resource[max];
		numResources = 0;
	}
	
	public String toString() {
		
		String s = new String();
		
		s += "Borrowed Resources: ";
		
		if(numResources == 0) {
			s += "No resources have been borrowed.\n";
		} 
		else {
			for(int i = 0; i < numResources; i++) {
			
				s += String.format("\n\t%s: %s", i + 1, resourcesBorrowed[i]);
			}
		}
		
		s += "\n";
		return s;
	}
	
	public void inputResource(Scanner scan, MyDate date) {
		
		String in;
		Resource res;
		
		do {
			System.out.print("Enter type of resource being borrowed... \n\tb for Book "
							+ "\n\td for DVD, \n\tm for Magazine, \n\tq to Quit: ");
			in = scan.next().toLowerCase();
		} while (!(in.equals("b") || in.equals("d") || in.equals("m") || in.equals("q")));
		
		if(in.equals("q")) {
			return;
		}
		
		if(in.equals("b")) {
			res = new Book();
		}
		else if(in.equals("d")) {
			res = new DVD();
		}
		else {
			res = new Magazine();
		}
		
		res.inputResource(scan, date);
		resourcesBorrowed[numResources] = res;
		numResources++;
		
		if(numResources == max) {
			
			int maxNew = max + max / 2;
			Resource[] arrNew = new Resource[maxNew];
			
			for(int i = 0; i < numResources; i++) {
				
				arrNew[i] = resourcesBorrowed[i];
			}
			
//			System.out.printf("expanded array from %d to %d\n", max, maxNew);
			
			resourcesBorrowed = arrNew;
			max = maxNew;
		}
	}
	
	public String resourcesOverDue(MyDate today) {
		
		String s= "";
		int overs = 0;
		
		if(numResources > 0) {
			
			s += String.format("Borrowed resources that are currently overdue, as of %s:\n", today);
			
			for(int i = 0; i < numResources; i++) {
				
				if(today.isGreaterThan(resourcesBorrowed[i].dueDate)) {
					
//					s += String.format("%s is greater than %s\n", today, resourcesBorrowed[i].dueDate);
					
					s += String.format("\t%s: %s\n", overs + 1, resourcesBorrowed[i]);
					overs++;
				}
			}
		} else {
			return "No resources have been borrowed.\n";
		}
		
		if(overs == 0) {
			return "No resources are overdue.\n";
		}
		
		return s;
	}
	
	public void deleteResource(Scanner scan, MyDate date) {
		
		String input = "";
		int del = 0;
		
		if(numResources > 0) {
				
			System.out.printf("What resource would you like to return?\n", numResources);
			System.out.println(this);
		
			//TODO don't need the else, empty condition already in this.toString
		} else {
			System.out.println("No resources have been borrowed.\n");
			return;
		}
		
		do {
			System.out.printf("Please enter a number between 1 and %d, or q to quit. \n", numResources);
			input = scan.next();
			
			if(input.equals("q")) {
				return;
			}
			
			try {
				del = Integer.parseInt(input);
			}
			catch (NumberFormatException e) {
			}
			
		} while(del < 1 || del > numResources);
		
		System.out.println("Resource returned.");
		
		if(resourcesBorrowed[del-1].isOverdue(date)) {
			resourcesBorrowed[del-1].displayOverDue();
		}
		
		resourcesBorrowed[del-1] = resourcesBorrowed[numResources-1];
		resourcesBorrowed[numResources-1] = null;
		
		numResources--;
		
		
	}
	
	
}
