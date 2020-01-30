import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will model a simple date by keeping day, month and year information.   Leap years are NOT
               accommodated in this class.
Author:  Linda Crane and xxxxxxxxxx
Course: F2018 - CST8130
Lab Section: xxxxxxxx
Data members:  day : int - value between 1 and 31 inclusive (depending on value in month)
               month: int - value between 1 and 12 inclusive
               year: int - positive value
Methods: default constructor - sets date to Jan 1, 2018
         initial constructor (day, month, year) - sets date to parameters passed
         copy constructor (MyDate) - sets date to same as object passed in
         toString (): String - prints date in year/moht/day format
         inputDate(Scanner): boolean - reads a valid date from the Scanner parameter and returns through
                                       boolean success or not
         addOne(): void - adds one to the day field and then adjusts month and year as needed.
         isEqual (MyDate): boolean - returns true/false whether parameter and object being acted on have equal values
         isGreaterThan(MyDate): boolean - returns true/false whether object values are greater than parameter object values                                              
         

*************************************************************************************************************/
public class MyDate {
		private int day = 1;
		private int month = 1;
		private int year = 2018;
		
		public MyDate() {
		}
		
		public MyDate(int day, int month, int year) {
			if (year >0)
				this.year = year;
			else this.year = 2018; // parameter was invalid - so set to valid year
			
			if (month > 0 && month < 13) 
				this.month = month;
			else this.month = 1; // parameter was invalid - so set to valid  month
			
			if (day > 0 && day <= 31)
				this.day = day;
			else this.day = 1;	// parameter was invalid - so set to valid day
			if (month == 2 && day > 29)
					this.day = 1; // parameter was invalid - so set to valid day
			if (day > 30 && (month == 9 ||month == 4 ||month == 6 ||month == 11))  
				    this.day = 1; // parameter was invalid - so set to valid day
				    
		}
		
		public MyDate (MyDate rhs) {
			this.year = rhs.year;
			this.day = rhs.day;
			this.month = rhs.month;
		}
		
		public String toString() {   return new String (" " + year + "/" + month + "/" + day);
		}
		public int toInt() {  // in month day year format
			return month*1000000 + day*10000 + year;		
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
				
				System.out.print ("Enter day - between 1 and 31: ");
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
		
		public void addOne (){
			this.day++;
			if (this.day > 31 || (this.month == 2 && this.day > 29) || (this.day > 30 && (this.month == 9 ||this.month == 4 ||this.month == 6 ||this.month == 11) ) ){ 
				this.day = 1;
				this.month ++;
				if (this.month > 12) {
					this.month = 1;
					this.year++;
				}
			}
		}
		
		public boolean isEqual (MyDate rhs){
			return (year == rhs.year && month == rhs.month && day == rhs.day);
		}
		
		public boolean isGreaterThan (MyDate rhs) {
			if (this.year > rhs.year)
				return true;
			if (this.year < rhs.year)
				return false;
			
			// years are equal
			if (this.month > rhs.month)
				return true;
			if (this.month < rhs.month)
				return false;
			
			// months are also equal
			if (this.day > rhs.day)
				return true;
			else return false;
		}
		
		



}


