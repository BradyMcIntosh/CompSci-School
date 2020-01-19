import java.util.ArrayList;
import java.util.Scanner;

/************************************************************************************************************
Purpose:  		This class holds a list of college courses and methods for manipulating them
Author:  		Brady McIntosh
Course: 		F2018 - CST8130
Lab Section: 	310
Data members:  	college : ArrayList - a list of grade lists (courses)
				collegeName : String - the name of this list
               	
Methods: 	default constructor(String) - sets name of college and initializes list
			toString() : String - returns an iterated, line-separated representation of this class' list data
			addCourse(Scanner) - adds a course to the list, getting a name from user input
			addBlock(Scanner) - adds a block to a list according to user input
			verifyChain() - verifies that all blocks in all lists are good, prints results
			fixChain() - removes all bad blocks in a list, according to user input                                     
         

*************************************************************************************************************/

public class College {

	private ArrayList<BlockChain> college;
	private String collegeName;
	
	public College (String name) {
		collegeName = name;
		college = new ArrayList<BlockChain>();
	}
	
	public String toString() {
		
		String ret = String.format("College: %s\n", collegeName);
		for(BlockChain b : college) {
			ret += b.toString();
		}
		
		return ret;
	}
	
	public void addCourse(Scanner keyboard) {
		
		System.out.println("Enter the name of the course to add: ");
		college.add(new BlockChain(keyboard.next()));
	}
	
	public void addBlock(Scanner keyboard) {
		if(!college.isEmpty()) {
			int choice = -1;
			
			System.out.println("For which course? ");
			for(int i = 0; i < college.size(); i++) {
				System.out.printf("%d. %s\n", i, college.get(i).getName());
			}
			
			try {
				choice = Integer.parseInt(keyboard.next());
			}
			catch(NumberFormatException nfe) {
				System.out.println("That's not a number.");
				return;
			}
			
			if(choice >= 0 && choice < college.size()) {
				college.get(choice).addBlock(keyboard);
			}
			else {
				System.out.println("That's an invalid number.");
			}
		}
		
	}
	
	public void verifyChain(Scanner keyboard) {
		if(!college.isEmpty()) {
			for(int i = 0; i < college.size(); i++) {
				if(college.get(i).verifyChain()) {
					System.out.printf("%s is verified. \n", college.get(i).getName());
				}
				else {
					System.out.printf("%s is broken. \n", college.get(i).getName());
				}
			}
		}
		else {
			System.out.println("No courses to verify!");
		}
	}
	
	public void fixChain(Scanner keyboard) {
		if(!college.isEmpty()) {
			int choice = -1;
			
			System.out.println("For which course? ");
			for(int i = 0; i < college.size(); i++) {
				System.out.printf("%d. %s\n", i, college.get(i).getName());
			}
			
			try {
				choice = Integer.parseInt(keyboard.next());
			}
			catch(NumberFormatException nfe) {
				System.out.println("That's not a number.");
				return;
			}
			
			if(choice >= 0 && choice < college.size()) {
				college.get(choice).fix();
			}
			else {
				System.out.println("That's an invalid number.");
			}
		}
		
	}
}
