import java.util.Scanner;

/************************************************************************************************************
Purpose:  This class hosts a menu for interacting with a simple Library object.
Author:  Brady McIntosh
Date: 	Oct 4 2018
Course: F2018 - CST8130
Lab Section: 312
Data members:  
Methods: main()                                            
         

*************************************************************************************************************/

public class Assign1 {
	
	public static void main(String[] args) {
		
		Library lib = new Library();
		Scanner scan = new Scanner(System.in);
		MyDate today = new MyDate();
		boolean cont = true;
		String command = "";
		
		System.out.println("Please enter today's date:");
		today.inputDate(scan);
		
		do {
			System.out.println("Select an option.");
			System.out.println("\t1. Borrow a resource \n\t2. Display all borrowed resources"
					+ "\n\t3. Display overdue resources \n\t4. Return a resource"
					+ "\n\t5. Change today's date \n\t6. Quit");
			
			command = scan.next();
			
			if(command.equals("1")) {
				lib.inputResource(scan, today);
			}
			else if(command.equals("2")) {
				System.out.println(lib);
			}
			else if(command.equals("3")) {
				System.out.println(lib.resourcesOverDue(today));
			}
			else if(command.equals("4")) {
				lib.deleteResource(scan, today);
			}
			else if(command.equals("5")) {
				System.out.println("Please enter today's date:");
				today.inputDate(scan);
			}
			else if(command.equals("6")) {
				System.out.println("Goodbye.");
				cont = false;
			}
			
			
		} while(cont);
		
		
		
		
//		MyDate d2 = new MyDate();
//		
//		today.inputDate(scan);
//		d2.inputDate(scan);
//		
//		System.out.printf("%s %s equal to %s\n", today, today.isEqual(d2)? "is": "is not", d2);
//		System.out.printf("%s %s greater than %s\n", today, today.isGreaterThan(d2)? "is": "is not", d2);
//		System.out.printf("%s %s greater than %s\n", d2, d2.isGreaterThan(today)? "is": "is not", today);
//		
//		lib.inputResource(scan, today);
//		lib.inputResource(scan, today);
//		lib.inputResource(scan, d2);
//		lib.inputResource(scan, d2);
//		
//		System.out.println(lib);
//		System.out.println(lib.resourcesOverDue(d2));
		
	}

}
