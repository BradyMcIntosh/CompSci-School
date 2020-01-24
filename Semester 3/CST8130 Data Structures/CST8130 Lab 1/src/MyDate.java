import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will model a simple date by keeping day, month and year information.   Leap years are NOT
               accommodated in this class.
Author:  Linda Crane and Brady McIntosh
Course: F2018 - CST8130
Lab Section: 312
Data members:  day : int - value between 1 and 31 inclusive (depending on value in month)
               month: int - value between 1 and 12 inclusive
               year: int - positive value
Methods: default constructor - sets date to Jan 1, 2018
         toString (): String - prints date in year/moht/day format
         inputDate(Scanner): boolean - reads a valid date from the Scanner parameter and returns through
                                       boolean success or not
         addOne(): void - adds one to the day field and then adjusts month and year as needed.                                              
         

*************************************************************************************************************/
public class MyDate {
		private int day = 1;
		private int month = 1;
		private int year = 2018;
		
		public MyDate() {
		}
		
		public String toFile() {
			
			return String.format("%d %d %d\n", month, day, year);
		}
		
		public String toString() {
			return String.format("%d/%02d/%02d", year, month, day);
		}
		
		public boolean inputDate(Scanner in, boolean file) {
			month = 0;
			day = 0; 
			year = 0;
			
			if (file) {
				if (in.hasNextInt()) {
					this.month = in.nextInt();
					if (this.month <= 0 || this.month > 12) {
						System.out.println ("Invalid month input @ " + this.month);
						return false;
					}
				}
				else {
					if(in.hasNext()) {
						System.out.println("Invalid month input @ " + in.next());
					}
					else {
						System.out.println("File ended unexpectedly.");
					}
					return false;
				}
				
				if (in.hasNextInt()) {
					this.day = in.nextInt();
					
					// giving month-specific day range
					if(this.month == 2) {
						if(this.day < 1 || this.day > 29) {
							System.out.println("Invalid day input @ " + this.day);
							return false;
						}
					}
					else if(inSet(this.month,9,4,6,11)) {
						if(this.day < 1 || this.day > 30) {
							System.out.println("Invalid day input @ " + this.day);
							return false;
						}
					}
					else {
						if(this.day < 1 || this.day > 31) {
							System.out.println("Invalid day input @ " + this.day);
							return false;
						}
					}
				}
				else {
					if(in.hasNext()) {
						System.out.println("Invalid day input @ " + in.next());
					}
					else {
						System.out.println("File ended unexpectedly.");
					}
					return false;
				}
				
				if (in.hasNextInt()) {
					this.year = in.nextInt();
				}
				else {
					if(in.hasNext()) {
						System.out.println("Invalid year input @ " + in.next());
					}
					else {
						System.out.println("File ended unexpectedly.");
					}
					return false;
				}
			}
			else {
			
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
			}
				
			return true;		
		}
		
		public void addOne (){
			
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

