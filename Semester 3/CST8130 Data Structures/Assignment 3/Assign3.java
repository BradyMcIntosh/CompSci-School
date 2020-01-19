import java.util.Scanner;

/************************************************************************************************************
Purpose:  		This class hosts the menu and primary instance for a "college" (list of courses)
Author:  		Linda Crane and Brady McIntosh
Course: 		F2018 - CST8130
Lab Section: 	310
Data members:  	
               	
Methods: 	main - menu loop for manipulating college course                                   
         

*************************************************************************************************************/

public class Assign3 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner (System.in);
		College myCollege = new College ("Algonquin");
		String menuChoice = "a";
		
		while (menuChoice.charAt(0) != '6') {
			System.out.println ("\nEnter 1 to display the college courses: ");
			System.out.println ("2 to add a new course: ");
			System.out.println ("3 to add a block: ");
			System.out.println ("4 to verify chains: ");
			System.out.println ("5 to fix a chain: ");
			System.out.println ("6 to quit: ");
			menuChoice = keyboard.next();
			
			switch (menuChoice.charAt(0)) {
			case '1': System.out.println(myCollege);
					  break;
			case '2': myCollege.addCourse(keyboard);
			          break;
			case '3': myCollege.addBlock(keyboard);
			  		  break;
			case '4': myCollege.verifyChain(keyboard);
	          		  break;
			case '5': myCollege.fixChain(keyboard);
					  break;
			case '6': System.out.println ("Goodbye");
					  break;
			default:  System.out.println ("Invalid choice...");
			}
		}
		

	}

}

