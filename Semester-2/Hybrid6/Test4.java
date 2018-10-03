import javax.swing.JOptionPane;

public class Test4 {

	public static String sum(int num1, int num2) {
		return String.format("%d + %d = %d", num1, num2, (num1 + num2));
	}
	
	public static void main(String[] args) {
		
		int number1;
		int number2;
		
		try {
			number1 = 
				Integer.parseInt(JOptionPane.showInputDialog("Enter an integer: "));
			number2 = Integer.parseInt(
					JOptionPane.showInputDialog("Enter another integer: "));
			System.out.println(sum(number1, number2));
		}
		catch(NumberFormatException numberFormatException) {
			System.out.println(numberFormatException.toString());
		}
	}
	
	/* Output (given nums 3 and 4.7):
	 * 
	 * java.lang.NumberFormatException: For input string: "4.7"
	 */
}
