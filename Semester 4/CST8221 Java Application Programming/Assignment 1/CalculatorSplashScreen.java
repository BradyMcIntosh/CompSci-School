package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * Shows a splash screen for a set time, before the main application opens.
 * 
 * @author 	Brady McIntosh
 * @version 1.0
 * @see 	
 * @since 	1.8
 */
public class CalculatorSplashScreen extends JWindow {
	
	/** Swing serial version ID, for serializable */
	private static final long serialVersionUID = 6248477390124812345L;
	/** Number of milliseconds to show splash on-screen */
	private final int duration;
	/** Size of splash screen */
	private static Dimension size = new Dimension(450, 450);
	/** Size of border outline */
	private static int border = 10;

	/**
	 * Default constructor. Sets the time in milliseconds to show splash on-screen.
	 * 
	 * @param duration Display time in milliseconds
	 */
	CalculatorSplashScreen(int duration) {
		this.duration = duration;
	}
	
	public void showSplashWindow() {
		// create new JPanel with border layout
		JPanel splash = new JPanel(new BorderLayout());
		
		// set background color to white
		splash.setBackground(Color.WHITE);
		
		int width = size.width + border;
		int height = size.height + border;
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width)/2;
	    int y = (screen.height - height)/2;
		
		setBounds(x,y,width,height);
		
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("bean.gif"))); 
		  
	    JLabel demo = new JLabel("Brady McIntosh (not pictured)", JLabel.CENTER);
		demo.setBackground(Color.WHITE);
	    demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
	    splash.add(label, BorderLayout.CENTER);
	    splash.add(demo, BorderLayout.SOUTH);
	    // create custom RGB color
	    Color customColor = new Color(111, 78, 55);
	    splash.setBorder(BorderFactory.createLineBorder(customColor, 10));
	    
	    setContentPane(splash);
	    setVisible(true);
	    
	    // Wait a little while doing nothing, while the application is loading
	    try {
	    	Thread.sleep(duration);
	    }
	    catch (InterruptedException e) {
	    	
	    }
	    
	    dispose(); 
	}
}
