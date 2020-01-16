
public class Test2 {

	public static void method3() throws RuntimeException {
		
		throw new RuntimeException("RuntimeException occurred in method3");
	}
	
	public static void method2() throws RuntimeException {
		
		try {
			method3();
		}
		catch(RuntimeException exception) {
			System.out.printf("The following exception occurred in method2\n%s\n",
					exception.toString());
			throw exception;
		}
	}
	
	public static void method1() throws RuntimeException {
		
		try {
			method2();
		}
		catch(RuntimeException exception) {
			System.out.printf("The following exception occurred in method1\n%s\n",
					exception.toString());
			throw exception;
		}
	}
	
	public static void main(String[] args) {
		
		try {
			method1();
		}
		catch(RuntimeException exception) {
			System.out.printf("The following exception occurred in main\n%s\n",
					exception.toString());
		}
	}
	
	/* Output:
	 * 
	 * The following exception occurred in method2
	 * java.lang.RuntimeException: RuntimeException occurred in method3
	 * The following exception occurred in method1
	 * java.lang.RuntimeException: RuntimeException occurred in method3
	 * The following exception occurred in main
	 * java.lang.RuntimeException: RuntimeException occurred in method3
	 */
}
