
public class Lab7Timed {

	private long timer;
	private long diff;
	
	int result1; int result2;
	
	String string1 = new String();
	String string2 = new String();

	public static void main(String[] args) {
		
		
		Lab7Timed timer = new Lab7Timed();
		System.out.println("Program developed by Brady McIntosh");
		PrimeNumbers primeNs = new PrimeNumbers();
		primeNs.getNumberFromUser();
		
		timer.startTimer();
		primeNs.generatePrimes();
		timer.stopTimer();
		primeNs.printPrimes();
		timer.displayTimer(1);
		timer.setVar(primeNs.result(), 1);
		
		timer.startTimer();
		primeNs.generatePrimes2();
		timer.stopTimer();
		primeNs.printPrimes();
		timer.displayTimer(2);
		timer.setVar(primeNs.result(), 2);
		
		timer.displayTimer(3);
	}

	public void startTimer()
	{
		timer=System.currentTimeMillis();
		while(timer==System.currentTimeMillis());
		timer=System.currentTimeMillis();
	}

	public void stopTimer()
	{
		diff = System.currentTimeMillis()-timer;
	}
	
	public void setVar(int res, int ver) {
		if(ver==1) {
			result1 = res;
		}
		
		if(ver==2) {
			result2 = res;
		}
	}

	public void displayTimer(int ver)
	{
		if(ver == 1) {
			if(diff<1000)
				string1 = (diff+" milliseconds - BONUS");
			else
				string1 = (diff/1000.0+" seconds - NO BONUS");
			
		}
		if(ver == 2) {
			if(diff<1000)
				string2 = (diff+" milliseconds - BONUS");
			else
				string2 = (diff/1000.0+" seconds - NO BONUS");
		}
		if(ver == 3) {
			System.out.println("1: "+result1+", "+string1);
			System.out.println("2: "+result1+", "+string2);
		}
	}
}
