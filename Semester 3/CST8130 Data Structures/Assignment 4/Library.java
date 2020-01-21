import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/************************************************************************************************************
Purpose:  This class will model and manage a library containing a hashed arraylist containing linked list
			that contains Resource objects.
Author:  Brady McIntosh
Date: 	Dec 5 2018
Course: F2018 - CST8130
Lab Section: 312
Data members:  	resourcesBorrowed : ArrayList<LinkedList<Resource>> - array of resources
				arrSize : int - "size" of the arraylist, also size of hash table
				numBorrowed : int - total number of resources borrowed
				
Methods: 	default constructor - fills arraylist with 1000 empty linked lists
			toString() : String - returns a description, including all member data
			toFile() : String - returns raw element data in a file-readable format
			inputResource(Scanner, MyDate) : boolean - prompts user for member data, returns true
			fileResource(Scanner) : boolean - reads element data from a file, returns validity of input
			resourcesOverDue(MyDate) : String - returns a summary-list of all overdue resources
			deleteResource(Scanner, MyDate) - prompts user for selection to delete resource, removes element
			find(String) : void - Searches and displays a list of resources that match the passed title
			
         

*************************************************************************************************************/

public class Library {

	private ArrayList<LinkedList<Resource>> resourcesBorrowed;
	private final int arrSize = 1000;
	private int numBorrowed;
	
	public Library() {
		resourcesBorrowed = new ArrayList<LinkedList<Resource>>(arrSize);
		numBorrowed = 0;
		
		for(int i = 0; i < arrSize; i++) {
			resourcesBorrowed.add(new LinkedList<Resource>());
		}
	}
	
	public String toString() {
		
		String s = new String();
		int count = 0;
		
		s += "Borrowed Resources: ";
		
		if(numBorrowed == 0) {
			s += "No resources have been borrowed.\n";
		}
		else {
			for(int i = 0; i < resourcesBorrowed.size(); i++) {
				
				if(resourcesBorrowed.get(i).size() > 0) {
					
					s += String.format("\nHash[%d]: ", i);
					
					for(int j = 0; j < resourcesBorrowed.get(i).size(); j++) {
						
						count++;
						s += String.format("\n\t%d: %s", count, resourcesBorrowed.get(i).get(j));
					}
				}
			}
		}
		
		s += "\n";
		return s;
	}
	
	public String toFile() {
		
		String s = new String();
		
		for(int i = 0; i < resourcesBorrowed.size(); i++) {
			for(int j = 0; j < resourcesBorrowed.get(i).size(); j++) {
			
				s += resourcesBorrowed.get(i).get(j).toFile();
				s += "\n";
			}
		}
		
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
		
		int index = res.getHash(arrSize);
		resourcesBorrowed.get(index).add(res);
		numBorrowed++;
		//sort();
	}
	
	public boolean fileResource(Scanner file) {
		
		byte lcount = 0;
		boolean success = true;
		String type = null, fullLine = null;
		Scanner line = null;
		Resource res = null;
		
		while (file.hasNextLine()) {
			lcount++;
			try {
				fullLine = file.nextLine();
				line = new Scanner(fullLine);
				
				type = line.next();
			}
			catch(NoSuchElementException nse) {
				System.out.printf("Error at line #%d, line ended early: \n\t%s\n", lcount, fullLine);
				success = false;
			}
			
			if(success) {
				
				if(type.toLowerCase().equals("b")) {
					res = new Book();
				}
				else if(type.toLowerCase().equals("m")) {
					res = new Magazine();
				}
				else if(type.toLowerCase().equals("d")) {
					res = new DVD();
				}
				else {
					System.out.printf("Error at line #%d, invalid resource type: \n\t%s\n", lcount, fullLine);
					success = false;
					res = null;
				}
			}
			if(success && (res.fileResource(line, lcount, fullLine))) {
				int index = res.getHash(arrSize);
				resourcesBorrowed.get(index).add(res);
				numBorrowed++;
				//sort();
			}
		}
		return success;
	}
	
	public String resourcesOverDue(MyDate today) {
		
		String s= "";
		int overs = 0;
		
		if(numBorrowed > 0) {
			
			s += String.format("Borrowed resources that are currently overdue, as of %s:\n", today);
			
			for(int i = 0; i < resourcesBorrowed.size(); i++) {
				for(int j = 0; j < resourcesBorrowed.get(i).size(); j++) {
				
					if(today.isGreaterThan(resourcesBorrowed.get(i).get(j).dueDate)) {
						overs++;
						s += String.format("\t%d: %s\n", overs, resourcesBorrowed.get(i).get(j));
					}
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
		
		if(numBorrowed > 0) {
				
			System.out.println("What resource would you like to return?");
			System.out.println(this);
		
		} else {
			System.out.println("No resources have been borrowed.\n");
			return;
		}
		
		do {
			System.out.printf("Please enter a number between 1 and %d, or q to quit. \n", numBorrowed);
			input = scan.next();
			
			if(input.equals("q")) {
				return;
			}
			
			try {
				del = Integer.parseInt(input);
			}
			catch (NumberFormatException e) {
				System.out.println("Error, invalid number.");
			}
			
		} while(del < 1 || del > numBorrowed);
		
		int count = 0;
		boolean loop = true;
		
		for(int i = 0; i < resourcesBorrowed.size(); i++) {
			for(int j = 0; j < resourcesBorrowed.get(i).size(); j++) {
				
				count++;
				if(count == del) {
					if(resourcesBorrowed.get(i).get(j).isOverdue(date)) {
						resourcesBorrowed.get(i).get(j).displayOverDue();
					}
					resourcesBorrowed.get(i).remove(j);
					loop = false;
					break;
				}
				
			}
			if(!loop) {
				break;
			}
		}
		System.out.println("Resource returned.");
		numBorrowed--;
	}
	
	public void find(String title) {
		
		String list = "";
		Resource temp = new Resource(title);
		int index = Resource.calcHash(title, arrSize);
		
		for(int j = 0; j < resourcesBorrowed.get(index).size(); j++) {
			if(resourcesBorrowed.get(index).get(j).isEqual(temp)) {
				list += String.format("\t%s\n", resourcesBorrowed.get(index).get(j));
			}
		}
		
		if (list.equals("")) {
			System.out.println("Sorry, no resources in this library match that title.");
		}
		else {
			System.out.println("The following resources match that title: ");
			System.out.print(list);
		}
	}
	
	
}
