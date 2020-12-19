/**
 * File name: CalculatorViewController.java
 * Author: Brady McIntosh, 040706980
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: A1 Part 2
 * Date: 
 * Professor: Daniel Cormier
 * Purpose: Simple Java GUI for a calculator, using Swing library.
 * Class list: CalculatorViewController
 */

package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

/**
 * Presentation and manipulation of a graphical calculator.
 * 
 * @author 	Brady McIntosh
 * @version 1.1
 * @see 	
 * @since 	1.8
 */
public class CalculatorViewController extends JPanel {

	/** The top section in the calculator display. */
	private JTextField display1;
	/** The bottom section in the calculator display. */
	private JTextField display2;
	/** The mode/error display panel. */ 
	private JLabel error;
	/** The button representing the dot, or '.', operation. */
	private JButton dotButton;
	/** Array of hexadecimal (alphanumerical) value buttons. */
	private JButton[] hexButtons;
	
	// static constants for initializing GUI elements
	
	/** Serial ID for serialization. */
	static final long serialVersionUID = 6248477390124812344L;
	
	// package-protected constants required to create this instance
	/** The title of the application. */
	static final String TITLE = "Calculator";
	/** The minimum display size of the application. */
	static final Dimension MIN_SIZE = new Dimension(380, 540);
	
	// package-protected constants relating to the display fields
	static final int DISPLAY_COLUMNS = 14;
	static final int ACTUAL_COLUMNS = 35;
	
	// package-protected constants relating to the mode settings
	static final String MODE_TEXT_HEX = "Hex";
	static final String MODE_TEXT_ONE_ZERO = ".0"; 
	static final String MODE_TEXT_TWO_ZERO = ".00"; 
	static final String MODE_TEXT_SCI = "Sci"; 
	
	// package-protected constants relating to the operator buttons
	static final String OP_PLUS = "plus";
	static final String OP_MINUS = "minus";
	static final String OP_MULTIPLY = "multiply";
	static final String OP_DIVIDE = "divide";
	
	// private constants relating to the display fields
	private static final int DISPLAY_HEIGHT = 30;
	private static final String DISPLAY_INIT = "0.0";
	
	//private constants relating to the error/mode label
	private static final String ERR_TEXT = "F";
	private static final Dimension ERR_SIZE = new Dimension(52,55);
	private static final Border ERR_BORDER = BorderFactory.createMatteBorder(0,1,0,5,Color.BLACK);
	
	// private constants relating to the backspace button
	private static final String BACK_ARROW = "\u21DA";
	private static final Dimension BACK_SIZE = new Dimension(52, 55);
	private static final Border BACK_BORDER = BorderFactory.createMatteBorder(0,5,0,1,Color.BLACK);
	private static final String BACK_TOOLTIP = "";
	
	// private constants relating to the combined display panel
	private static final Border C_DISP_BORDER = BorderFactory.createMatteBorder(0,0,5,0,Color.BLACK);
	
	// private constants relating to the hex mode checkbox
	private static final String HEX_TEXT = MODE_TEXT_HEX;
	private static final Border HEX_BORDER = BorderFactory.createMatteBorder(0,5,0,0,Color.BLACK);
	
	// private constants relating to the radio buttons
	private static final int RADIO_ROWS = 1;
	private static final int RADIO_COLS = 3;
	private static final Border RADIO_BORDER = BorderFactory.createMatteBorder(0,5,0,0,Color.BLACK);
	
	// private constants relating to the mode row container
	private static final Border MODE_BORDER = BorderFactory.createEmptyBorder();
	
	// private constants relating to the alphanumeric button grid
	private static final int GRID_ROWS = 6;
	private static final int GRID_COLS = 3;
	private static final int GRID_GAP = 3;
	private static final int ALPHA_COUNT = 6;
	private static final int ALPHA_OFFSET = 65;
	private static final int NUM_COUNT = 14;
	private static final int NUM_OFFSET = 48;
	
	// private constants relating to the operator buttons
	private static final Dimension OP_SIZE = new Dimension(48,45);
	private static final int OP_ROWS = 2;
	private static final int OP_COLS = 1;
	private static final int OP_GAP = GRID_GAP;
	private static final Border OP_BORDER_PROD = BorderFactory.createMatteBorder(0,1,0,5,Color.BLACK);
	private static final Border OP_BORDER_SUM = BorderFactory.createMatteBorder(0,5,0,1,Color.BLACK);
	
	// private constants relating to the head panel
	private static final Border BODY_BORDER = BorderFactory.createMatteBorder(5,0,0,0,Color.BLACK);
	
	
	private class Controller implements ActionListener {
		
		/** The functional calculator class that performs arithmetic operations. */
		private CalculatorModel calculator = new CalculatorModel();
		
		private boolean inputActive = false;
		private String inputMode = MODE_TEXT_TWO_ZERO;
		
		/** The temporary user input */
		private StringBuilder input = new StringBuilder("0.00");
		
		/** The sign indicator (true means positive) */
		private boolean positive = true;
		
		/** The number of columns to offset, to account for the sign indicator */
		private byte displayOffset;
		
		/** Whether the first operand has been saved. */
		private boolean firstOperandSaved = false;
		
		/** Whether the first operand has been saved. */
		private boolean secondOperandWritten = false;
		
		/** Whether the operator has been saved. */
		private boolean operatorSet = false;
		
		// display action command of captured event in secondary field
		@Override
		public void actionPerformed(ActionEvent action) {
			
			if(positive) {
				displayOffset = 1;
			}
			else {
				displayOffset = 0;
			}
			
			// attempt to set operational mode and precision
			if(action.getActionCommand().equals(MODE_TEXT_HEX)
					|| action.getActionCommand().equals(MODE_TEXT_ONE_ZERO)
					|| action.getActionCommand().equals(MODE_TEXT_TWO_ZERO)
					|| action.getActionCommand().equals(MODE_TEXT_SCI)) {
				
				System.out.println("Operational mode: "  + action.getActionCommand());
				
				// clear input
				inputActive = false;
				inputMode = action.getActionCommand();
				
				// reset temp bools
				firstOperandSaved = false;
				secondOperandWritten = false;
				operatorSet = false;
				
				switch (action.getActionCommand()) {
					case MODE_TEXT_HEX: input = new StringBuilder("0");
						break;
					case MODE_TEXT_ONE_ZERO: input = new StringBuilder("0.0");
						break;
					case MODE_TEXT_TWO_ZERO: input = new StringBuilder("0.00");
						break;
					case MODE_TEXT_SCI: input = new StringBuilder("0.00");
						break;
				}
				
				// reset error state display
				error.setText(ERR_TEXT);
				error.setBackground(Color.YELLOW);
				
				// enable or disable the dot button / hex buttons
				if (action.getActionCommand().equals(MODE_TEXT_HEX)) {
					dotButton.setEnabled(false);
					
					error.setText("H");
					error.setBackground(Color.GREEN);
					
					for(int i = 0; i < ALPHA_COUNT; i++) {
						hexButtons[i].setEnabled(true);
					}
				}
				else {
					dotButton.setEnabled(true);
					
					error.setText("F");
					error.setBackground(Color.YELLOW);
					
					for(int i = 0; i < ALPHA_COUNT; i++) {
						hexButtons[i].setEnabled(false);
					}
				}
				
				display1.setText("");
				
				if(!calculator.setOpMode(action.getActionCommand())) {
					display2.setText("Invalid operational mode");
				}
				if(!calculator.setPrecision(action.getActionCommand())) {
					display2.setText("Invalid precision mode");
				}
			}
			 // attempt to add a number/letter (or period) to temporary input
			if(action.getActionCommand().equals("1")
					|| action.getActionCommand().equals("2")
					|| action.getActionCommand().equals("3")
					|| action.getActionCommand().equals("4")
					|| action.getActionCommand().equals("5")
					|| action.getActionCommand().equals("6")
					|| action.getActionCommand().equals("7")
					|| action.getActionCommand().equals("8")
					|| action.getActionCommand().equals("9")
					|| action.getActionCommand().equals("0")
					|| action.getActionCommand().equals("A")
					|| action.getActionCommand().equals("B")
					|| action.getActionCommand().equals("C")
					|| action.getActionCommand().equals("D")
					|| action.getActionCommand().equals("E")
					|| action.getActionCommand().equals("F")
					|| action.getActionCommand().equals(".")) {
				
				System.out.println("Add number: "  + action.getActionCommand());
				
				// if input is not active, activate it and reset input
				if (!inputActive) {
					inputActive = true;
					input = new StringBuilder();
				}
				
				// if entering second operand, reset input
				if (firstOperandSaved && !secondOperandWritten) {
					secondOperandWritten = true;
					input = new StringBuilder();
				}
				
				// if there is space, add new digit
				if((input.length() + displayOffset) <= ACTUAL_COLUMNS ) {
					input.append(action.getActionCommand());
					display2.setText(input.toString());
				}
				
				
			}
			
			// change sign indicator
			if(action.getActionCommand().equals("sign")) {
				
				System.out.println("Change sign: " + action.getActionCommand());
				
				if(!inputActive) {
					inputActive = true;
				}
				
				if(positive) {
					input.insert(0, "-");
				}
				else {
					input.deleteCharAt(0);
				}
				
				positive = !positive;
				display2.setText(input.toString());
				
			}
			
			// dot (decimal point) button
			if(action.getActionCommand().equals("dot")) {
				
				System.out.println("Dot: " + action.getActionCommand());
				
				if(!inputActive) {
					inputActive = true;
					
					input = new StringBuilder("0.");
				}
				else {
					input.append(".");
				}
				
				display2.setText(input.toString());
				
			}
			
			// attempt to delete last digit (backspace)
			if(action.getActionCommand().equals("backspace")) {
				
				System.out.println("Backspace: "  + action.getActionCommand());
				
				// check if there are at least 2 non-sign digits left in user input
				// if so, delete last digit normally
				if(input.length() + (1 - displayOffset) > 1) {
					input.deleteCharAt(input.length() - 1);
				}
				// if not, reset entire input and set default sign
				else {
					inputActive = false;
					positive = true;
					
					// set input to appropriate representation of 0
					switch (inputMode) {
						case MODE_TEXT_HEX: input = new StringBuilder("0");
							break;
						case MODE_TEXT_ONE_ZERO: input = new StringBuilder("0.0");
							break;
						case MODE_TEXT_TWO_ZERO: input = new StringBuilder("0.00");
							break;
						case MODE_TEXT_SCI: input = new StringBuilder("0.00");
							break;
					}
				}
				
				display2.setText(input.toString());
				System.out.println("Backspace...");
			}
			
			// attempt to set operator (save temp input)
			if(action.getActionCommand().equals(OP_PLUS)
					|| action.getActionCommand().equals(OP_MINUS)
					|| action.getActionCommand().equals(OP_MULTIPLY)
					|| action.getActionCommand().equals(OP_DIVIDE)) {
				
				System.out.println("Set operator: "  + action.getActionCommand());
			
				// if the first operand has not been saved, save (and check for errors)
				if(!firstOperandSaved) {
					if(!calculator.setOperand1(input.toString())) {
						
						// TODO error stuff
						error.setText("E");
						error.setBackground(Color.RED);
						
						input = new StringBuilder();
						display2.setText("Invalid operand!");
					}
					
					firstOperandSaved = true;
				}
				
				if(!calculator.setArithOperator(action.getActionCommand())) {
					// TODO error stuff
					error.setText("E");
					error.setBackground(Color.RED);
				}
				
				display1.setText(input.toString() + " " + action.getActionCommand() /* + " "*/);
			}
			
			// CLEAR
			if(action.getActionCommand().equals("clear")) {
				
				System.out.println("Clear: "  + action.getActionCommand());
				
				// clear input
				inputActive = false;
				input = new StringBuilder();
				
				// reset temp bools
				firstOperandSaved = false;
				secondOperandWritten = false;
				operatorSet = false;
				
				if(inputMode.equals(MODE_TEXT_HEX)) {
					error.setText("H");
					error.setBackground(Color.GREEN);
				}
				else {
					error.setText(ERR_TEXT);
					error.setBackground(Color.YELLOW);
				}
				
				display1.setText("");
				
				// set input to appropriate representation of 0
				switch (inputMode) {
					case MODE_TEXT_HEX: input = new StringBuilder("0");
						break;
					case MODE_TEXT_ONE_ZERO: input = new StringBuilder("0.0");
						break;
					case MODE_TEXT_TWO_ZERO: input = new StringBuilder("0.00");
						break;
					case MODE_TEXT_SCI: input = new StringBuilder("0.00");
						break;
				}
				
				display2.setText(input.toString());
			}
			
			// attempt to calculate w/ equals (do calculation)
			if(action.getActionCommand().equals("equals")) {
				
				System.out.println("Equals: "  + action.getActionCommand());
				
				if(firstOperandSaved) {
					// set second operand
					if(!calculator.setOperand2(input.toString())) {
						
						// TODO error stuff
						error.setText("E");
						error.setBackground(Color.RED);
					}
					
					// reset temp bools
					firstOperandSaved = false;
					secondOperandWritten = false;
					operatorSet = false;
					
					// calculate and display result
					String result = calculator.getResult();
					
					input = new StringBuilder(result);
					display1.setText("");
					
					if(result.length() <= ACTUAL_COLUMNS) {
						display2.setText(result);
					}
					else {
						display2.setText("Result cannot be displayed...");
					}
				}
			}
		}
		
		
		
		
		
		
	}
	
	/**
	 * Create & set all user-facing elements in the calculator GUI.
	 * 
	 * @author 	Brady McIntosh
	 * @version 1.0
	 * @see 	
	 * @since 	1.8
	 */
	public CalculatorViewController() {
		
		// instantiate Controller
		Controller handler = new Controller();
		
		// create & instantiate display1
		display1 = new JTextField();
		display1.setColumns(DISPLAY_COLUMNS);
		display1.setBounds(display1.getX(), display1.getY(), display1.getWidth(), DISPLAY_HEIGHT);
		display1.setHorizontalAlignment(JTextField.RIGHT);
		display1.setBackground(Color.WHITE);
		display1.setBorder(BorderFactory.createEmptyBorder());
		display1.setEditable(false);
		
		// create & instantiate display2
		display2 = new JTextField();
		display2.setColumns(DISPLAY_COLUMNS);
		display2.setBounds(display2.getX(), display2.getY(), display2.getWidth(), DISPLAY_HEIGHT);
		display2.setHorizontalAlignment(JTextField.RIGHT);
		display2.setBackground(Color.WHITE);
		display2.setBorder(BorderFactory.createEmptyBorder());
		display2.setEditable(false);
		
		// set initial text to display
		display2.setText(DISPLAY_INIT);
		
		// create panel containing both display textfields
		JPanel displayCombined = new JPanel();
		displayCombined.setLayout(new BorderLayout());
		displayCombined.setBackground(Color.WHITE);
		displayCombined.setBorder(BorderFactory.createEmptyBorder());
		displayCombined.add(display1, BorderLayout.NORTH);
		displayCombined.add(display2, BorderLayout.SOUTH);
		
		// create & instantiate error/mode label
		error = new JLabel();
		error.setText(ERR_TEXT);
		error.setPreferredSize(ERR_SIZE);
		error.setBackground(Color.YELLOW);
		error.setBorder(ERR_BORDER);
		error.setHorizontalAlignment(JLabel.CENTER);
		error.setVerticalAlignment(JLabel.CENTER);
		
		// create & initialize backspace button
		JButton backspaceButton = createButton(BACK_ARROW, "backspace", Color.BLACK, Color.YELLOW,
				handler);
		backspaceButton.setPreferredSize(BACK_SIZE);
		backspaceButton.setBorder(BACK_BORDER);
		backspaceButton.setToolTipText(BACK_TOOLTIP);
		// set mnemonic "Alt+B" as alternate trigger
		backspaceButton.setMnemonic(KeyEvent.VK_B);
		// make background yellow & transparent
		backspaceButton.setBackground(Color.YELLOW);
		backspaceButton.setContentAreaFilled(false);
		// create tooltip
		backspaceButton.setToolTipText("Backspace (Alt-B)");
		
		// create panel containing display, mode/error label, and backspace button
		JPanel displayRow = new JPanel();
		displayRow.setBackground(Color.YELLOW);
		displayRow.setBorder(C_DISP_BORDER);
		displayRow.setLayout(new BorderLayout());
		displayRow.add(error, BorderLayout.WEST);
		displayRow.add(displayCombined, BorderLayout.CENTER);
		displayRow.add(backspaceButton, BorderLayout.EAST);
		
		// create & instantiate hex checkbox
		JCheckBox hexMode = new JCheckBox(HEX_TEXT);
		hexMode.setBackground(Color.GREEN);
		hexMode.setBorder(HEX_BORDER);
		hexMode.setActionCommand(MODE_TEXT_HEX);
		hexMode.addActionListener(handler);
		// set preferred size width to match that of error/mode label
		//hexMode.setPreferredSize(new Dimension(error.getWidth(), hexMode.getHeight()));
		
		// create & instantiate radio button #1
		JRadioButton radioOneZero = new JRadioButton(MODE_TEXT_ONE_ZERO);
		radioOneZero.setBackground(Color.YELLOW);
		radioOneZero.setActionCommand(MODE_TEXT_ONE_ZERO);
		radioOneZero.addActionListener(handler);
		
		// create & instantiate radio button #2
		JRadioButton radioTwoZero = new JRadioButton(MODE_TEXT_TWO_ZERO);
		radioTwoZero.setBackground(Color.YELLOW);
		radioTwoZero.setActionCommand(MODE_TEXT_TWO_ZERO);
		radioTwoZero.addActionListener(handler);
		
		// create & instantiate radio button #3
		JRadioButton radioSci = new JRadioButton(MODE_TEXT_SCI);
		radioSci.setBackground(Color.YELLOW);
		radioSci.setActionCommand(MODE_TEXT_SCI);
		radioSci.addActionListener(handler);
		
		// create a a button group for the radio & hex buttons
		ButtonGroup modeGroup = new ButtonGroup();
		modeGroup.add(hexMode);
		modeGroup.add(radioOneZero);
		modeGroup.add(radioTwoZero);
		modeGroup.add(radioSci);
		
		// set default radio button
		radioTwoZero.setSelected(true);
		
		// create a container for the radio buttons
		JPanel radioContainer = new JPanel();
		radioContainer.setLayout(new GridLayout(RADIO_ROWS,RADIO_COLS));
		radioContainer.setBackground(Color.BLACK);
		radioContainer.setBorder(RADIO_BORDER);
		radioContainer.add(radioOneZero);
		radioContainer.add(radioTwoZero);
		radioContainer.add(radioSci);
		
		// create a container for the radio & hex buttons
		JPanel modeRow = new JPanel();
		modeRow.setBackground(Color.BLACK);
		modeRow.setLayout(new BorderLayout());
		modeRow.setBorder(MODE_BORDER);
		modeRow.add(hexMode, BorderLayout.WEST);
		modeRow.add(radioContainer, BorderLayout.EAST);
		
		// create a container for the head, including modeRow and displayRow
		JPanel head = new JPanel();
		head.setLayout(new BorderLayout());
		head.add(displayRow, BorderLayout.NORTH);
		head.add(modeRow, BorderLayout.SOUTH);
		
		// create a container for the alphanumeric button grid
		JPanel buttonGrid = new JPanel();
		buttonGrid.setLayout(new GridLayout(GRID_ROWS, GRID_COLS, GRID_GAP, GRID_GAP));
		buttonGrid.setBackground(Color.WHITE);
		
		// initialize array containing grid buttons
		hexButtons = new JButton[GRID_ROWS * GRID_COLS];
		
		// create grid buttons in loop
		for(int i = 0; i < hexButtons.length; i++) {
			
			char chr;		// temporary button text
			String text;	// the text of the button
			String ac;		// the action command string of the button
			Color bg;		// the background colour of the button
			
			if (i <= NUM_COUNT || i == 16) {
				bg = Color.BLUE;
			}
			else {
				bg = Color.MAGENTA;
			}
			
			// logic determining button text
			if(i < ALPHA_COUNT) {
				// set A-F
				chr = (char) (i + ALPHA_OFFSET);
			}
			else if (i <= NUM_COUNT) {
				// set 1-9
				
				int numSkip;	// correction for non-ordered numbering
				
				// set numerical order correction
				if(i >= 6 && i < 9) {
					numSkip = 1;
				}
				else if(i >= 9 && i < 12 ) {
					numSkip = -5;
				}
				else { // i >= 12 && i < 15
					numSkip = -11;
				}
				
				// set title according to corrected offset
				chr = (char) (i + NUM_OFFSET + numSkip);
			}
			else {
				// set bottom row - dot, 0, sign
				if(i == 15) {
					chr = '.';
				}
				else if(i == 16) {
					chr = '0';
				}
				else { // i == 17
					chr = 0x00B1; // the plus/minus symbol
				}
			}
			
			// set button strings
			text = new Character(chr).toString();
			ac = new Character(chr).toString();
			
			// special case for dot symbol
			if (chr == '.') {
				ac = "dot";
			}
			// special case for plus/minus symbol
			if (chr == 0x00B1) {
				ac = "sign";
			}
			
			// create button
			hexButtons[i] = createButton(text, ac, Color.BLACK, bg, handler);
			
			// set dot button
			if (i == 15) {
				dotButton = hexButtons[i];
			}
			
			// set alphabetical buttons to disabled
			if (i < ALPHA_COUNT) {
				hexButtons[i].setEnabled(false);
			}
			
			// add to container
			buttonGrid.add(hexButtons[i]);
			
			System.out.println( i + ": " + chr);
		}
		
		// TODO these are too small for some reason...
		// create clear button
		JButton clear = createButton("C", "clear", Color.BLACK, Color.RED, handler);
		
		// create equals button
		JButton equals = createButton("=", "equals", Color.BLACK, Color.YELLOW, handler);
		
		// create & initialize keypad container, including clear, equals & hex buttons
		JPanel keypad = new JPanel();
		keypad.setLayout(new BorderLayout());
		keypad.add(clear, BorderLayout.NORTH);
		keypad.add(buttonGrid, BorderLayout.CENTER);
		keypad.add(equals, BorderLayout.SOUTH);
		
		// create multiplication button
		JButton multiply = createButton("*", OP_MULTIPLY, Color.BLACK, Color.CYAN, handler);
		multiply.setPreferredSize(OP_SIZE);
		
		// create division button
		JButton divide = createButton("/", OP_DIVIDE, Color.BLACK, Color.CYAN, handler);
		divide.setPreferredSize(OP_SIZE);
		
		// create container for multiply/divide buttons
		JPanel prodCol = new JPanel();
		prodCol.setBorder(OP_BORDER_PROD);
		prodCol.setLayout(new GridLayout(OP_ROWS, OP_COLS, OP_GAP, OP_GAP));
		prodCol.setBackground(Color.BLACK);
		prodCol.add(multiply);
		prodCol.add(divide);
		
		// create plus button
		JButton plus = createButton("+", OP_PLUS, Color.BLACK, Color.CYAN, handler);
		plus.setPreferredSize(OP_SIZE);
		
		// create minus button
		JButton minus = createButton("-", OP_MINUS, Color.BLACK, Color.CYAN, handler);
		minus.setPreferredSize(OP_SIZE);
		
		// create container for multiply/divide buttons
		JPanel sumCol = new JPanel();
		sumCol.setLayout(new GridLayout(OP_ROWS, OP_COLS, OP_GAP, OP_GAP));
		sumCol.setBackground(Color.BLACK);
		sumCol.setBorder(OP_BORDER_SUM);
		sumCol.add(plus);
		sumCol.add(minus);
		
		// create body panel for product buttons, grid buttons, and sum buttons
		JPanel body = new JPanel();
		body.setLayout(new BorderLayout());
		body.setBorder(BODY_BORDER);
		body.add(prodCol, BorderLayout.WEST);
		body.add(keypad, BorderLayout.CENTER);
		body.add(sumCol, BorderLayout.EAST);
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createMatteBorder(5, 6, 5, 6, Color.BLACK));
		this.add(head, BorderLayout.NORTH);
		this.add(body, BorderLayout.CENTER);
	}
	
	private JButton createButton( String text, String ac, Color fg, Color bg, ActionListener handler ) {
		JButton button = new JButton(text);
		
		button.setActionCommand(ac);
		button.setForeground(fg);
		button.setBackground(bg);
		button.addActionListener(handler);
		
		return button;
	}
}
