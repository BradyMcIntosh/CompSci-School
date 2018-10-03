
public class B extends A{
	
	private int b;
	
	public B() {
	}
	
	public String toString() {
		
		return super.toString() + " " + b;
	}
	
	public int calc (int x) {
		
		return b + x;
	}
}
