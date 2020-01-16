public class PowerBenchmark {
	public static void main(String [] args) {
		{
			System.out.println("Control (empty loop)");
			long count = 0;
			
			long t0 = System.currentTimeMillis();
			while (System.currentTimeMillis() < t0 + 1000) {
				
				double x = Math.random() * 5 + 1;
				
				double tmp = x;
				
				count ++;
				
			}
			
			System.out.println(count);
		}
		{
			System.out.println("Math.pow(x,3)");
			long count = 0;
			
			long t0 = System.currentTimeMillis();
			while (System.currentTimeMillis() < t0 + 1000) {
				
				double x = Math.random() * 5 + 1;
				
				double tmp = Math.pow(x,3);
				
				count ++;
				
			}
			
			System.out.println(count);
		}
		{
			System.out.println("x*x*x");
			long count = 0;
			
			long t0 = System.currentTimeMillis();
			while (System.currentTimeMillis() < t0 + 1000) {
				
				double x = Math.random() * 5 + 1;
				
				double tmp = x*x*x;
				
				count ++;
				
			}
			
			System.out.println(count);
		}
		{
			System.out.println("Control (empty loop)");
			long count = 0;
			
			long t0 = System.currentTimeMillis();
			while (System.currentTimeMillis() < t0 + 1000) {
				
				double x = Math.random() * 5 + 1;
				
				double tmp = x;
				
				count ++;
				
			}
			
			System.out.println(count);
		}
	}
}
