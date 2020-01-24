import java.util.Scanner;
/************************************************************************************************************
Purpose:  This class will model a due dates for assessments in a course
Author:  Linda Crane and Brady McIntosh
Course: F2018 - CST8130
Lab Section: 312
Data members:	dueDates - array of MyDate references

Methods:	default constructor - sets dueDates array to size of 10
			constructor(int) - sets dueDates array to size of passed integer
			inputDueDates(Scanner): boolean - loops through the dueDates array and uses Scanner input
											to set member attributes.
			addOne(): void - adds 1 day to each date in the dueDates array.
			toString(): String - prints out a list of dates in the array.


*************************************************************************************************************/

public class DueDates {
	private MyDate[] dueDates ;
	
	public DueDates() {
		dueDates = new MyDate[10];
	}
	
	public DueDates(int max) {
		dueDates = new MyDate[max];
	}
	
	public boolean inputDueDates(Scanner in, boolean file) {
		
		boolean valid = true;
		
		if (file) {
			
			for(int i = 0; i < dueDates.length; i++) {
				if(dueDates[i] == null) {
					dueDates[i] = new MyDate();
				}
				valid = dueDates[i].inputDate(in, file);
				if (!valid) {
					break;
				}
			
			}
		}
		else {
			System.out.println("Enter due dates:");
			
			for(int i = 0; i < dueDates.length; i++) {
				if(dueDates[i] == null) {
					dueDates[i] = new MyDate();
				}
				System.out.printf("  %d:\n", i+1);
				dueDates[i].inputDate(in, file);
			}
		}
		
		return valid;
	}
	
	public void addOne () {
		
		for(int i = 0; i < dueDates.length; i++) {
			if(dueDates[i] == null) {
				dueDates[i] = new MyDate();
			}
			dueDates[i].addOne();
		}
	}
	
	private int parseDate(MyDate d) {
		return Integer.parseInt(d.toString().replaceAll("/", ""));
	}
	
	private MyDate[] arrSort(MyDate[] dueDates) {
		
		MyDate[] sort = dueDates;
		
		for(int i = 0; i < sort.length; i++) {
			for(int j = i; j > 0; j--) {
				if(j > 0 && parseDate(sort[j-1]) > parseDate(sort[j])) {
					
					MyDate mov = sort[j-1];
					sort[j-1] = sort[j];
					sort[j] = mov;
				}
				else {
					break;
				}
			}
		}
		
		return sort;
	}
	
	public String toFile() {
		
		dueDates = arrSort(dueDates);
		
		String out = new String();
		
		out += dueDates.length + "\n";
		
		for(int i = 0; i < dueDates.length; i++) {
			if(dueDates[i] == null) {
				dueDates[i] = new MyDate();
			}
			out += dueDates[i].toFile();
		}
		
		return out;
	}
	
	public String toString() {
		
		dueDates = arrSort(dueDates);
		
		String out = new String();
		
		for(int i = 0; i < dueDates.length; i++) {
			if(dueDates[i] == null) {
				dueDates[i] = new MyDate();
			}
			out += String.format("  %d: %s\n", i+1, dueDates[i]);
		}
		
		return out;
	}

}

