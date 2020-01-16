import java.util.Scanner;

/*
 * Created by Brady McIntosh
 * in the winter of 2017
 * to bet fake money
 * and confuse his professor.
 */
 
 /*
  * I added something that was
  * funny to me, though I may
  * ultimately lose marks for it.
  * The dealer will be offended
  * if you enter the wrong inputs.
  * If you make enough mistakes,
  * you will be kicked out.
  */
 
 public class Gambling {
	 
	 public static void main(String [] args) {
		 
		int pot, bet;
		int insults = 0;
		boolean amicable = true;
		 
		Scanner scanner = new Scanner(System.in);
		 
		System.out.println("\n= = = = = = = = = =\n"+
		                   "= G A M B L I N G =\n"+
		                   "= W I T H   T H E =\n"+
		                   "= B R A D S T E R =\n"+
		                   "= = = = = = = = = =\n");
		
		System.out.println("Welcome to the virtual casino.");
		System.out.println("We are playing \"solitaire dice\".");
		System.out.println("Enter a bet of zero to leave.");
		
		pot = 100;
		
		//game continues as long as you have money and the dealer likes you
		while(pot > 0 && amicable) {
			
			do {
				
				System.out.println("\n= = = = = = = = = = \n");
				System.out.println("You have $" + pot + "."
					+"\nPlace your bet. ");
			
				bet = -1;
			
				String input = scanner.next();
				
				if(input.equals("?")) {
					bet = roll(1,pot);
					System.out.println("Random bet!");
					break;
				}
				
				if(input.equals("~")) {
					pot += 100;
					System.out.println("I didn't see anything.");
					continue;
				}
				
				//if input is an integer
				if(intTest(input)) {
					int value = Integer.parseInt(input);
					//if input is more than or equal to zero
					if(value>=0) {
						bet = value;
						break;
					//if input is less than zero
					} else {
						System.out.println("Bets must be positive.");
					}
				//if input is not an integer
				} else {
					System.out.println("Bets must be numbers.");
				}
				//every time you make a mistake, the dealer likes you less
				insults++;
			} while (bet < 0 && insults <= 10);
			
			if(bet==0) {break;}
			if(insults > 10) {
				amicable = false;
				break;
			}
			
			pot -= bet;
			System.out.println("You have bet $"+bet+".");
			
			//rolls three random numbers, each between 1 and 6
			int rollA = roll(1,6);
			int rollB = roll(1,6);
			int rollC = roll(1,6);
			
			System.out.printf("Three dice were rolled!"
				+ "\nTheir outcomes: %d, %d, %d.\n",rollA,rollB,rollC);
				
			//if all three rolls are the same
			if(rollA == rollB && rollA == rollC) {
				System.out.println("TRIPLES!!! You've won $"+bet*3+" back!!!");
				pot += bet*3;
				continue;
			//if two rolls are the same
			} else if (rollA == rollB || rollA == rollC || rollB == rollC) {
				System.out.println("Doubles!! You've won $"+bet*2+" back!!");
				pot += bet*2;
				continue;
			//if all three rolls add up to 10
			} else if (rollA+rollB+rollC > 10) {
				System.out.println("Tenner! You've won $"+bet+" back!");
				pot += bet;
				continue;
			//if you are a loser
			} else {
				System.out.println("Bad roll. You don't win squat.");
			}
		}
		
		//if the dealer likes you
		if (amicable) {
			//if you don't have money
			if(pot == 0) {
				System.out.println("See? The house always wins.");
			//if you do have money
			} else {
				System.out.println("Pleasure doing business with you.");
			}
		//if the dealer doesn't like you
		} else {
			System.out.println("\nMy patience is now, finally, at its end. I am done with you."
				+ "\nIf the depth of your funds is equal to that of your insolence,"
				+ "\nthen you shall have no trouble taking your business elsewhere.");
		}
		
		System.out.println("Goodbye.");
	 }
	 
	 public static boolean intTest(String input) {
		 
		 //not sure if this does anything
		 if(input.isEmpty()){return false;}
		 for(int i = 0; i < input.length(); i++) {
			 //if the first character is - and there is more than one character
			 if(i==0 && input.charAt(0)=='-' && input.length()>1) {
				 continue;
			 }
			 //if the current character isn't a digit
			 if(!Character.isDigit(input.charAt(i))) {
				 return false;
			 }
		 }
		 return true;
	}
	 
	 //returns a random number between the two inputs
	 public static int roll(int min, int max) {
		 
		 return (int)(Math.random() * ((max - min) + 1)) + min;
	 }
 }
