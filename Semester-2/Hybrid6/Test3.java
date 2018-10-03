
public class Test3 {

	public static String divide(int number1, int number2) {
		
		return String.format("%d divided by %d is %d",
				number1, number2, (number1 / number2));
	}
	
	public static void main(String[] args) {
		
		try {
			System.out.println(divide(4,2));
			System.out.println(divide(20,5));
			System.out.println(divide(100,0));
		}
		catch(Exception exception) {
			System.out.println(exception.toString());
		}
	}
	
	/* Output:
	 * 
	 * 4 divided by 2 is 2
	 * 20 divided by 5 is 4
	 * java.lang.ArithmeticException: / by zero
	 */
	
}
