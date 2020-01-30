import java.util.Scanner;

public class Lab6Palindrome {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a string: ");
		String input = scan.nextLine();
		scan.close();
		
		LList list1 = new LList();
		LList list2 = new LList();
		
		for(int i = 0; i < input.length(); i++) {
			if(Character.isAlphabetic(input.charAt(i))) {
				list1.addAtHead(input.toLowerCase().charAt(i));
				list2.addAtHead(input.toLowerCase().charAt(i));
			}
		}
		
		LList tempL = new LList();
		
		while(!list2.isEmpty()) {
			tempL.addAtHead(list2.deleteAtHead().getData());
		}
		
		list2 = tempL;
		
//		System.out.println("You entered: ");
//		list1.display();
//		System.out.println("Reversed: ");
//		list2.display();
		
		boolean isPalindrome = true;
		
		while(!list1.isEmpty()) {
			if(list1.deleteAtHead().getData() != list2.deleteAtHead().getData()) {
				isPalindrome = false;
			}
		}
		
		if(isPalindrome) {
			System.out.println("That string is a palindrome.");
		}
		else {
			System.out.println("That string is NOT a palindrome.");
		}
		

	}

}
