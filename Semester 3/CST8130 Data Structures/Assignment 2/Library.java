import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/************************************************************************************************************
Purpose:  This class will model and manage a library containing an arraylist of multiple resource objects
Author:  Brady McIntosh
Date: 	Nov 1 2018
Course: F2018 - CST8130
Lab Section: 312
Data members:  	resourcesBorrowed - ArrayList<Resource> - array of resources
				
Methods: 	default constructor - calls int constructor with a size of 10
			constructor(int) - sets resourcesBorrowed capacity to argument, or 10 if int is below 1
			toString() : String - returns a description, including all member data
			toFile() : String - returns raw element data in a file-readable format
			inputResource(Scanner, MyDate) : boolean - prompts user for member data, returns true
			fileResource(Scanner) : boolean - reads element data from a file, returns validity of input
			resourcesOverDue(MyDate) : String - returns a summary-list of all overdue resources
			deleteResource(Scanner, MyDate) - prompts user for selection to delete resource, removes element
			sort() : void - Uses insertion sort to sort arraylist resources alphabetically by title.
			find(String) : void - Searches and displays a list of resources that match the passed title
			
         

*************************************************************************************************************/

public class Library {

	private ArrayList<Resource> resourcesBorrowed;
	
	public Library() {
		this(10);
	}
	
	public Library(int max) {
		if (max <= 0) {
			max = 10;
		}
		resourcesBorrowed = new ArrayList<Resource>(max);
	}
	
	public String toString() {
		
		String s = new String();
		
		s += "Borrowed Resources: ";
		
		if(resourcesBorrowed.size() == 0) {
			s += "No resources have been borrowed.\n";
		} 
		else {
			for(int i = 0; i < resourcesBorrowed.size(); i++) {
			
				s += String.format("\n\t%s: %s", i + 1, resourcesBorrowed.get(i));
			}
		}
		
		s += "\n";
		return s;
	}
	
	public String toFile() {
		
		String s = new String();
		
		for(int i = 0; i < resourcesBorrowed.size(); i++) {
			
			s += resourcesBorrowed.get(i).toFile();
			s += "\n";
		}
		
		return s;
	}
	
	public void inputResource(Scanner scan, MyDate date) {
		
		// put all file stuff here
		
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
		resourcesBorrowed.add(res);
		sort();
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
			if(success && (success = res.fileResource(line, lcount, fullLine))) {
				resourcesBorrowed.add(res);
				sort();
			}
		}
		return success;
	}
	
	public String resourcesOverDue(MyDate today) {
		
		String s= "";
		int overs = 0;
		
		if(resourcesBorrowed.size() > 0) {
			
			s += String.format("Borrowed resources that are currently overdue, as of %s:\n", today);
			
			for(int i = 0; i < resourcesBorrowed.size(); i++) {
				
				if(today.isGreaterThan(resourcesBorrowed.get(i).dueDate)) {
					
//					s += String.format("%s is greater than %s\n", today, resourcesBorrowed[i].dueDate);
					
					s += String.format("\t%s: %s\n", overs + 1, resourcesBorrowed.get(i));
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
		
		if(resourcesBorrowed.size() > 0) {
				
			System.out.println("What resource would you like to return?");
			System.out.println(this);
		
		} else {
			System.out.println("No resources have been borrowed.\n");
			return;
		}
		
		do {
			System.out.printf("Please enter a number between 1 and %d, or q to quit. \n", resourcesBorrowed.size());
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
			
		} while(del < 1 || del > resourcesBorrowed.size());
		
		System.out.println("Resource returned.");
		
		if(resourcesBorrowed.get(del-1).isOverdue(date)) {
			resourcesBorrowed.get(del-1).displayOverDue();
		}
		
		resourcesBorrowed.remove(del-1);
	}
	
	private void sort() {
		
		for (int i = 1; i < resourcesBorrowed.size(); i++) {
			
			String c1 = resourcesBorrowed.get(i).title;
			int j = i-1;
			
			// while c1 < res@j
			while (j >= 0 && c1.compareToIgnoreCase(resourcesBorrowed.get(j).title) < 0) {
				
				Resource mov = resourcesBorrowed.get(j+1);
				resourcesBorrowed.remove(j+1);
				resourcesBorrowed.add(j, mov);
				j--;
			}
		}
	}
	
	public void find(String title) {
		
		String list = "";
		
		for(int i = 0; i < resourcesBorrowed.size(); i++) {
			if(resourcesBorrowed.get(i).title.equals(title)) {
				list += resourcesBorrowed.get(i).toString();
				list += "\n";
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
