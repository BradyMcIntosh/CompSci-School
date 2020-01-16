
public class Test {

	public static void main(String[] args) {

		A aObj = new A();
		A bObj = new B();
		B bObj2 = new B();
		
		System.out.println(aObj);
		System.out.println(bObj);
		System.out.println(bObj2);
		System.out.println(bObj.calc(2));
		System.out.println(aObj.calc(2));

	}

}
