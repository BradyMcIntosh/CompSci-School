import java.util.Scanner;

/************************************************************************************************************
Purpose:  		This class holds a grade from a course, associated information and current/previous security hashes
Author:  		Linda Crane and Brady McIntosh
Course: 		F2018 - CST8130
Lab Section: 	310
Data members:  	date : MyDate - the date associated with the grade
				studentNumber : int - the student number associated with the grade
               	grade : int - the value of the grade
               	previousHash : float - the security hash of the previous block
               	currentHash : float - the security hash of this block
               	
Methods: 	default constructor - sets initial values and calculates hash
			calculateHash() : float - generates a security hash based on block data
			toString() : String - returns a linear representation of this block's data
			getCurrentHast() : float - returns the security hash of the current block
			isEqual(Block) : boolean - checks if the passed block's previous hash matches this one's current
			updatePreviousHash(float) - takes a float and sets it as this block's previous hash
			addInfoToBlock(Scanner, float) : boolean - accepts block data from user input and re-calculates hash                                            
         

*************************************************************************************************************/

public class Block {
	private MyDate date;  // part of data - in month day year format  (eg) 2152018
	private int studentNumber; // part of data
	private int grade;  // part of data
	private float previousHash;
	private float currentHash;
	
	public Block() {
		date = new MyDate(11,11,2018);
		studentNumber = 0;
		grade = 100;
		previousHash = 0f;
		currentHash = calculateHash();
		
	}
	
	public float calculateHash() {
		return (date.toInt()+studentNumber+grade)/88;   
	}
	
	public String toString() {
		return "" + studentNumber + " " + grade + " " + date +  " current: " + currentHash + " previous: " + previousHash ;
	}
	
//	public Block next() {
//		return nextOne;
//	}
	
	public float getCurrentHash() {
		return currentHash;
	}
	
	public boolean isEqual (Block temp) {
		return (previousHash == temp.currentHash);
	}
//	public void updateNext(Block newOne) {
//		nextOne = newOne;
//	}
	
	public void updatePreviousHash(float prevHash) {
		previousHash = prevHash;
	}
	
	public boolean addInfoToBlock(Scanner keyboard, float previousHash) {
		System.out.print ("Enter date: ");
		date.inputDate(keyboard);
		
		System.out.print ("Enter student number: ");
		while (!keyboard.hasNextInt())  {
			System.out.print ("Invalid...enter an int for student number: ");
			keyboard.next();
		}
		studentNumber = keyboard.nextInt();
		
		
		System.out.println ("Enter grade: ");
		while (!keyboard.hasNextInt())  {
			System.out.print ("Invalid...enter an int for grade: ");
			keyboard.next();
		}
		grade = keyboard.nextInt();
		
		currentHash = calculateHash();
		this.previousHash = previousHash;
		return true;
	}
	
}
