package lab5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for dual printing - to the 
 * 
 * @author Brady
 *
 */
public class BankOutput {

	private FileWriter fw; 
	
	/**
	 * Attempts to initialize the member FileWriter.
	 */
	public BankOutput() {
		
		try {
			fw = new FileWriter(new File("C:\\Users\\Public\\Downloads\\bankoutput.txt"));
		}
		catch (IOException io) {
			System.out.println("Couldn't initialize file.");
		}
	}
	
	/**
	 * Close the FileWriter.
	 */
	public void boClose() {
		try {
			fw.close();
		}
		catch (IOException io) {
			System.out.println("Uh oh, couldn't close the FileWriter!");
		}
	}
	
	/**
	 * Writes a given String to the output file only.
	 * 
	 * @param out The given String
	 */
	public void boFileOut(String out) {
		
		try {
			fw.write(out);
			fw.write(System.lineSeparator());
		}
		catch (IOException io) {
			System.out.println("Couldn't write that String to file.");
		}
	}
	
	/**
	 * Writes a given int to the output file only.
	 * 
	 * @param out The given int
	 */
	public void boFileOut(int out) {
		
		try {
			fw.write(out+"");
			fw.write(System.lineSeparator());
		}
		catch (IOException io) {
			System.out.println("Couldn't write that int to file.");
		}
	}
	
	/**
	 * Writes a given char to the output file only.
	 * 
	 * @param out The given char
	 */
	public void boFileOut(char out) {
		
		try {
			fw.write(out+"");
			fw.write(System.lineSeparator());
		}
		catch (IOException io) {
			System.out.println("Couldn't write that char to file.");
		}
	}
	
	/**
	 * Writes a given double to the output file only.
	 * 
	 * @param out The given double
	 */
	public void boFileOut(double out) {
		
		try {
			fw.write(out+"");
			fw.write(System.lineSeparator());
		}
		catch (IOException io) {
			System.out.println("Couldn't write that double to file.");
		}
	}
	
	/**
	 * Prints a given String to the console and to the output file.
	 * 
	 * @param out The given String
	 */
	public void boPrint(String out) {
		
		System.out.print(out);
		
		try {
			fw.write(out);
		}
		catch (IOException io) {
			System.out.println("Couldn't write that String to file.");
		}
	}
	
	/**
	 * Prints a given Object to the console and to the output file.
	 * 
	 * @param out The given Object
	 */
	public void boPrint(Object ob) {
		
		System.out.print(ob);
		
		try {
			fw.write(ob.toString());
		}
		catch (IOException io) {
			System.out.println("Couldn't write that String to file.");
		}
	}
	
	/**
	 * Prints a given String to the console and to the output file,
	 * with a newline at the end.
	 * 
	 * @param out The given String
	 */
	public void boPrintln(String out) {
		
		System.out.println(out);
		
		try {
			fw.write(out);
			fw.write(System.lineSeparator());
		}
		catch (IOException io) {
			System.out.println("Couldn't write that String to file.");
		}
	}
	
	/**
	 * Prints a given Object to the console and to the output file,
	 * with a newline at the end.
	 * 
	 * @param out The given Object
	 */
	public void boPrintln(Object ob) {
		
		System.out.println(ob);
		
		try {
			fw.write(ob.toString());
			fw.write(System.lineSeparator());
		}
		catch (IOException io) {
			System.out.println("Couldn't write that String to file.");
		}
	}
	
}
