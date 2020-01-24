import java.util.Scanner;

public class Lab3 {
	
	private static boolean testPalindrome(String s) {
		
		System.out.println(s);
		
		if(s.charAt(0) == s.charAt(s.length()-1)) {
			if(s.length() > 2) {
				return testPalindrome(s.substring(1, s.length()-1));
			}
			else {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		
		String input = new String();
		Scanner scan = new Scanner(System.in);
		
		do {
			System.out.print("Enter a string (q to quit): ");
			input = scan.nextLine();
			input = input.replaceAll(" ", "").toLowerCase();
			
			if(!input.equals("q")) {
				if(testPalindrome(input)) {
					System.out.printf("%s is a palindrome!", input);
				}
				else {
					System.out.printf("%s is not a palindrome...", input);
				}
				System.out.println("\n");
			}
			
			
			
		} while(!input.equals("q"));
		
		scan.close();
	}

}
