import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class is the method main for Lab 1 
Author:  Linda Crane and Brady McIntosh
Course: F2018 - CST8130
Lab Section: 312
                                             
         

*************************************************************************************************************/


public class Lab1 {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner (System.in);
		
		boolean cont = false;
		
		do {
			
			int input = 0;
			
			DueDates dates = new DueDates();
			
			do {
				System.out.print("How many assessments in this course? (must be greater than 0) : ");
				
				if(keyboard.hasNextInt()) {
					int tempIn = keyboard.nextInt();
					
					if(tempIn > 0) {
						input = tempIn;
						dates = new DueDates(input);
					}
				}
				else if(keyboard.next().equals("")) {
					input = 1;
				}
				
			} while(input <= 0);
			
			// run input method
			dates.inputDueDates(keyboard, false);
			
			System.out.println("The due dates are: ");
			System.out.println(dates);
			
			System.out.println("The due dates with one day added are: ");
			dates.addOne();
			System.out.println(dates);
			
			System.out.print("Do another? (y/n) : ");
			
			boolean inCheck = false; 
			do {
				String s = keyboard.next();
				
				if(s.equals("n")) {
					System.out.println("Goodbye.");
					inCheck = true;
					cont = false;
				}
				else if(s.equals("y")) {
					inCheck = true;
					cont = true;
					System.out.println("continue");
				}
				else {
					System.out.print("Please specify if you would like to continue. (y/n) : ");
				}
			} while(!inCheck);
		} while(cont);
		
		keyboard.close();
	}
	
}

