import java.util.Scanner;

public class PowerGame {
	
	public static void main(String [] args) {
		int input;
		int solution;
		int score = -1;
		long t0 = System.currentTimeMillis(); //time when game starts
		long time;
		
		System.out.println("Answer the following questions. You have 30 seconds.");		
		do {
			int exponent = (int) Math.floor(Math.random() * 13);
			solution = (int) Math.pow(2,exponent);
			Scanner scanner = new Scanner(System.in);
			score++; //adds 1 to score every cycle, or for every correct answer
			
			System.out.print("What is 2^"+exponent+"? ");
			input = scanner.nextInt();
			//asks user for solution to the generated equation
			
			time = System.currentTimeMillis() - t0; //time at the end of each loop
			
		} while(input == solution && time < 30000);
		
		if(input != solution) {
			System.out.println("Nope. The answer to that one was "+solution+".");
			System.out.println("You got "+score+" out of "+(score+1)+" answers correct.");
		} else {
			System.out.println("Correct, but out of time!");
			System.out.println("You got all "+(score+1)+" answers correct.");
		}
		
		if(time > 30000) {
			System.out.println("You lasted at least 30 seconds. Congratulations!");
		} else {
			System.out.println("You didn't last 30 seconds. Sorry, loser.");
		}
	}
}
