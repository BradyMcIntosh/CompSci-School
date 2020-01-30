import java.util.TreeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Lab7Dictionary {
	
	private TreeMap<String,Integer> clearTree(TreeMap<String,Integer> dict) {
		System.out.println("Clear tree...");
		return new TreeMap<String,Integer>();
		
	}
	
	private void userAdd(TreeMap<String,Integer> dict, Scanner keyboard) {
		System.out.println("User add...");
		
		System.out.println("Enter the word to be added.");
		addWord(dict, keyboard.next());
		
	}
	
	private void fileAdd(TreeMap<String,Integer> dict, Scanner keyboard) {
		
		System.out.println("File add...");
		
		Scanner fileRead = null;
		try {
			fileRead = new Scanner(new File(fileName(keyboard)));
		}
		catch (FileNotFoundException fnf) {
			System.out.println("File not found!");
		}
		
		while(fileRead.hasNext()) {
			String word = fileRead.next();
			if(word.matches(".+--.+")) {
				String[] words = word.split("--");
				for(String w : words) {
					addWord(dict, w);
				}
				continue;
			}
			addWord(dict, word);
		}
		fileRead.close();
		
	}
	
	private static String fileName(Scanner keyboard) {

		System.out.println("Enter the name of the file.");
		boolean valid = true;
		String uinput = keyboard.next();
		do {
			try {
				File fileenter = new File(uinput);
				Scanner filescan = new Scanner(fileenter);
				filescan.next();
				filescan.close();
			} catch (IOException ioe) {
				System.out.println("That is not a valid file. Try again.");
			}
		} while (valid != true);

		return uinput;
	}
	
	private String processWord(String word) {
		return word.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
	}
	
	private void addWord(TreeMap<String,Integer> dict, String word) {
		
		word = processWord(word);
		
		if(!word.isEmpty()) {
			//System.out.printf("Adding word: \"%s\"", word);
			Integer val = new Integer(1);
			if(dict.get(word) != null) {
				//System.out.print(" adding 1...");
				val = dict.get(word) + 1;
			}
			//System.out.println(" putted with value of " + val);
			dict.put(word, val);
		}
	}
	
	private void wordCount(TreeMap<String,Integer> dict, Scanner keyboard) {
		
		System.out.println("Enter the word to count: ");
		String word = processWord(keyboard.next());
		System.out.printf("%s occurs %s times.\n", word, dict.get(word) == null ? 0 : dict.get(word));
		
	}
	
	private void nodeCount(TreeMap<String,Integer> dict) {
		System.out.println("Word count is " + dict.size());
	}

	public static void main(String[] args) {
		
		Lab7Dictionary lab = new Lab7Dictionary();
		TreeMap<String,Integer> dict = new TreeMap<String,Integer>();
		Scanner keyboard = new Scanner(System.in);
		String command;
		
		do {
			System.out.println("Select an option.");
			System.out.println("\t1. Clear the dictionary \n\t2. Add text from keyboard"
					+ "\n\t3. Add text from a file \n\t4. Search for a word count"
					+ "\n\t5. Display number of nodes \n\t6. Quit");
			
			command = keyboard.next();
			
			if (command.equals("1")) {
				dict = lab.clearTree(dict);
				
			} else if (command.equals("2")) {
				lab.userAdd(dict, keyboard);
				
			} else if (command.equals("3")) {
				lab.fileAdd(dict, keyboard);
				
			} else if (command.equals("4")) {
				lab.wordCount(dict, keyboard);
				
			} else if (command.equals("5")) {
				lab.nodeCount(dict);
				
			} else if (command.equals("6")) {
				System.out.println("Goodbye.");
				
			}
			else {
				System.out.println("That is not a valid option.");
			}
			
		} while(!command.equals("6"));

	}

}
