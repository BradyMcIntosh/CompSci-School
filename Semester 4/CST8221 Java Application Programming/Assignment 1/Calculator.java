package calculator;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * Runner container for the Calculator application.
 * 
 * @author 	Brady McIntosh
 * @version 1.0
 * @see 	
 * @since 	1.8
 */
public class Calculator {
	
	public static void main(String[] args) {
		
		// Create and show a splash screen that will show for 5 seconds
		CalculatorSplashScreen splashWindow = new CalculatorSplashScreen(5000);
		splashWindow.showSplashWindow();
		
		Runnable calc = new Runnable() {
			@Override
			public void run() {
				
				JFrame runner = new JFrame(CalculatorViewController.TITLE);
				
				runner.setSize(CalculatorViewController.MIN_SIZE);
				runner.setMinimumSize(CalculatorViewController.MIN_SIZE);
				runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				runner.setContentPane(new CalculatorViewController());
				runner.setLocationRelativeTo(null);
				runner.setVisible(true);
			}
		};
		
		EventQueue.invokeLater(calc);
	}
	
}
