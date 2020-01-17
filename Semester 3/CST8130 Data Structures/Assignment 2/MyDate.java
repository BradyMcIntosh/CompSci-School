import java.util.Scanner;

/************************************************************************************************************
Purpose:  This class will model a simple date by keeping day, month and year information.   Leap years are NOT
               accommodated in this class.
Author:  Linda Crane and Brady McIntosh
Date: 	Nov 1 2018
Course: F2018 - CST8130
Lab Section: 312
Data members:  day : int - value between 1 and 31 inclusive (depending on value in month)
               month: int - value between 1 and 12 inclusive
               year: int - positive value
Methods: default constructor - sets date to Jan 1, 2018
		 constructor(int, int, int) - sets date to passed integers, in y/m/d order
		 constructor(MyDate, int) - returns a new date incremented from the passed one by int days
         toString (): String - prints date in yyyy/mm/dd format
         toFile() : String - returns date in mm dd yyyy format
         inputDate(Scanner): boolean - reads a valid date from the Scanner parameter and returns through
                                       boolean success or not
         addOne(): void - adds one to the day field and then adjusts month and year as needed.
         isEqual(MyDate): boolean - returns whether or not this date is equal to the passed date
         isGreaterThan(MyDate): boolean - returns whether or not this date is greater than the passed date                                              
         

*************************************************************************************************************/

public class MyDate {
	
	private int day = 1;
	private int month = 1;
	private int year = 2018;
	
	public MyDate() {
	}
	
	public MyDate(int y, int m, int d) {
		year = y;
		month = m;
		day = d;
	}
	
	public MyDate(MyDate old, int offset) {
		old.day = day;
		old.month = month;
		old.year = year;
		
		for(int i = 0; i < offset; i++) {
			addOne();
		}
	}
	
	public String toString() {   
		return String.format("%d/%02d/%02d", year, month, day);
	}
	
	public String toFile() {
		return String.format("%02d %02d %d", month, day, year);
	}
	
	public boolean inputDate(Scanner in) {
		month = 0;
		day = 0; 
		year = 0;
		
		do {
				
			System.out.print ("Enter month - between 1 and 12: ");
			if (in.hasNextInt())
				this.month = in.nextInt();
			else {
				System.out.println ("Invalid month input");
				in.next();
			}
		} while (this.month <= 0 || this.month > 12);
		
		do {
			// giving month-specific day range
			if(this.month == 2) {
				System.out.print ("Enter day - between 1 and 29: ");
			}
			else if(inSet(this.month,9,4,6,11)) {
				System.out.print ("Enter day - between 1 and 30: ");
			}
			else {
				System.out.print ("Enter day - between 1 and 31: ");
			}
			
			if (in.hasNextInt())
				this.day = in.nextInt();
			else {
				System.out.println ("Invalid day input");
				in.next();
			}
		} while (this.day <= 0 || this.day > 31 || (this.month == 2 && this.day > 29) || (this.day > 30 && (this.month == 9 ||this.month == 4 ||this.month == 6 ||this.month == 11) ) );
		
		do {
			System.out.print ("Enter year: ");
			if (in.hasNextInt())
				this.year = in.nextInt();
			else {
				System.out.println ("Invalid day input");
				in.next();
			}
		} while (this.year <= 0);
			
		return true;		
	}
	
	public void addOne() {
		
		day++;
		
		// day validation
		if(inSet(month, 9, 4, 6, 11)) {
			if(day > 30) {
				month++;
				day = 1;
			}
		}
		else if(month == 2) {
			if(day > 29) {
				month ++;
				day = 1;
			}
		}
		else {
			if(day > 31) {
				month++;
				day = 1;
			}
		}
		
		// month validation
		if(month > 12) {
			year++;
			month = 1;
		}
	}
	
	// returns true if the local date and the parameter date are equal
	public boolean isEqual(MyDate date) {
		
		if(date.year == this.year && date.month == this.month && date.day == this.day) {
			return true;
		}
		return false;
	}
	
	// returns true if the local date is greater than the parameter date
	public boolean isGreaterThan(MyDate date) {
		
		int locInt = Integer.parseInt(this.toString().replaceAll("/", ""));
		int forInt = Integer.parseInt(date.toString().replaceAll("/", ""));
		
		//System.out.printf("%d > %d ?\n", locInt, forInt);
		
		if(locInt > forInt) {
			return true;
		}
		
		return false;
	}
	
	// determines if a value, "x", is within a specified set, "r"
		private boolean inSet(int x, int... r) {
			
			for(int i : r) {
				if(x == i) {
					return true;
				}
			}
			
			return false;
		}

}
