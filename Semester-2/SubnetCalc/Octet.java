
public class Octet {

	private String binary;
	private int decimal;
	
	public void setDec(int x) {
		
		decimal = x;
		
		binary = "" + Integer.toString(x, 2);
		
		while(binary.length() < 8) {
			binary = "0" + binary;
		}
	}
	
	public void setBin(String s) {
		
		binary = s;
		
		while(binary.length() < 8) {
			binary = "0" + binary;
		}
		
		decimal = Integer.parseInt(s, 2);
	}
	
	public int getInt() {
		
		return decimal;
	}
	
	public String getBin() {
		
		return binary;
	}
}
