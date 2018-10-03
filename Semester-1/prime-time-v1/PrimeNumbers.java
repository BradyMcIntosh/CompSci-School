import java.util.Scanner;

public class PrimeNumbers{
	private int numberOfPrimes;
	private int[] primes;
	
	public void getNumberFromUser() {
		Scanner scanner = new Scanner(System.in);
		String inputStr = scanner.nextLine();
		
		int inputInt = Integer.parseInt(inputStr);
		
		numberOfPrimes = inputInt;
		primes = new int[inputInt];
		
	}
	
	public void generatePrimes() {
		int primeNumbers = 1;
		int current = 3;
		primes[0] = 2;
		
		while(primeNumbers < numberOfPrimes) {
			boolean isPrime = true;
			int previousPrime = 0;
			while(previousPrime < primeNumbers) {
				if(current % primes[previousPrime] == 0) {
					isPrime = false;
				}
				previousPrime = previousPrime + 1;
			}
			if(isPrime) {
				primes[primeNumbers] = current;
				primeNumbers = primeNumbers + 1;
			}
			current = current+2;
		}
	}
	
	public void generatePrimes2() {
		
		primes[0] = 2;
		int p = 0;
		
		// loop through the numbers one by one
		for (int i = 2; i<numberOfPrimes; i++) {
			
			boolean isPrime = true;

			// check to see if the number is prime
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrime = false;
					break; // exit the inner for loop
				}
			}
			
			// print the number if prime
			if (isPrime) {
				primes[p] = i;
			}
			p++;
		}

	}
	
	public void printPrimes() {
		for(int i = 0; i < primes.length; i++) {
			System.out.println((i+1)+": "+primes[i]);
		}
	}
	
	public int result() {
		return primes[primes.length-1];
	}
	
	public int result2() {
		return primes[primes.length-1];
	}
}
