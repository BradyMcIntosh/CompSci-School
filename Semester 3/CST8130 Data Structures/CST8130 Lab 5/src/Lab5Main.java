import java.util.Scanner;

public class Lab5Main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner (System.in);
		BlockChain myBlockChain = new BlockChain ("CST8130");
		String menuChoice = "a";
		
		while (menuChoice.charAt(0) != '5') {
			System.out.println ("\nEnter 1 to display the chain: ");
			System.out.println ("2 to add a good block: ");
			System.out.println ("3 to add a bad block: ");
			System.out.println ("4 to verify chain: ");
			System.out.println ("5 to quit: ");
			menuChoice = keyboard.next();
			
			switch (menuChoice.charAt(0)) {
			case '1': myBlockChain.printBlockChain();
					  break;
			case '2': myBlockChain.addBlock(keyboard);
			          break;
			case '3': myBlockChain.addBadBlock(keyboard);
			  		  break;
			case '4': if (myBlockChain.verifyChain())
							System.out.println ("Chain is verified");
					   else  System.out.println ("Chain is broken");
	          		  break;
			case '5': System.out.println ("Goodbye");
					  break;
			default:  System.out.println ("Invalid choice...");
			}
		}
		

	}

}

