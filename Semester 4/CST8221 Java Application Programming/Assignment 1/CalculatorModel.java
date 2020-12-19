package calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Formatter;

// TODO Make sure everything is commented & documented correctly

/**
 * Performs arithmetic operations according to internal settings.
 * 
 * @author 	Brady McIntosh
 * @version 1.0
 * @see 	
 * @since 	1.8
 */
public class CalculatorModel {
	
	private static final byte OP_PLUS = 0;
	private static final byte OP_MINUS = 1;
	private static final byte OP_MULTIPLY = 2;
	private static final byte OP_DIVIDE = 3;
	
	private static final boolean MODE_HEX = false;
	private static final boolean MODE_FLT = true;
	
	private static final byte PR_HEX = 0;
	private static final byte PR_1Z = 1;
	private static final byte PR_2Z = 2;
	private static final byte PR_SCI = 3;
	
	/** The first calculation input. */
	private String operand1;
	/** The second calculation input. */
	private String operand2;
	
	/** The current calculation operator.
	 * Can be one of { 0, 1, 2, 3 }.
	 * 0 means plus,
	 * 1 means minus,
	 * 2 means multiply,
	 * 3 means divide. */
	private byte operator;
	
	/** The current operational mode.
	 * "true" means numbers are decimal floats,
	 * "false" means numbers are hexadecimal integers. */
	private boolean mode = true;
	
	/** The current error state.
	 * "true" means an input or operation was in error,
	 * "false" means no error was found. */
	private boolean errorState = false;
	
	/** The current numerical precision.
	 * Can be one of { 0, 1, 2, 3 }.
	 * 0 means hex integer,
	 * 1 means decimal with one digit of precision,
	 * 2 means decimal with two digits of precision,
	 * 3 means scientific notation. */
	private byte precision = 2;
	
	// Set functions
	
	/** Sets the first calculation input. 
	 * Should not recieve whole decimal numbers with more than 1 zero after decimal point.
	 * Returns false if the argument is not a valid number. */
	boolean setOperand1(String operand1) {
		
		// test if the string is a valid number
		// hex int mode
		if(mode == MODE_HEX) {
			// test if the number is valid
			try {
				new BigInteger(operand1, 16);
			}
			catch (NumberFormatException nfe) {
				errorState = true;
				return false;
			}
		}
		// dec float mode
		else {
			// test if the number is valid
			try {
				new BigDecimal(operand1);
			}
			catch (NumberFormatException nfe) {
				errorState = true;
				return false;
			}
		}
		
		this.operand1 = operand1;
		
		System.out.println("Set operand1 to " + this.operand1);
		errorState = false;
		return true;
	}
	
	/** Sets the second calculation input. 
	 * Should not recieve whole decimal numbers with more than 1 zero after decimal point.
	 * Returns false if the argument is not a valid number. */
	boolean setOperand2(String operand2) {
		
		// test if the string is a valid number
		// hex int mode
		if(mode == MODE_HEX) {
			// test if the number is valid
			try {
				new BigInteger(operand2, 16);
			}
			catch (NumberFormatException nfe) {
				errorState = true;
				return false;
			}
		}
		// hex int mode
		else {
			// test if the number is valid
			try {
				new BigDecimal(operand2);
			}
			catch (NumberFormatException nfe) {
				errorState = true;
				return false;
			}
		}
		
		this.operand2 = operand2;
		
		System.out.println("Set operand2 to " + this.operand2);
		errorState = false;
		return true;
	}
	
	/** Sets the arithmetic operator. 
	 * Returns false if the arithmetic operator cannot be set. */
	boolean setArithOperator(String operator) {
		
		if(operator.equals(CalculatorViewController.OP_PLUS)) {
			this.operator = OP_PLUS;
		}
		else if(operator.equals(CalculatorViewController.OP_MINUS)) {
			this.operator = OP_MINUS;
		}
		else if(operator.equals(CalculatorViewController.OP_MULTIPLY)) {
			this.operator = OP_MULTIPLY;
		}
		else if(operator.equals(CalculatorViewController.OP_DIVIDE)) {
			this.operator = OP_DIVIDE;
		}
		else {
			errorState = true;
			return false;
		}
		
		System.out.println("Set operator to " + this.operator);
		errorState = false;
		return true;
	}

	/** Sets the operational mode.
	 * Returns false if the operational mode cannot be set. */
	boolean setOpMode(String mode) {
		
		if(mode.equals(CalculatorViewController.MODE_TEXT_HEX)) {
			this.mode = MODE_HEX;
		}
		else if(mode.equals(CalculatorViewController.MODE_TEXT_ONE_ZERO)) {
			this.mode = MODE_FLT;
		}
		else if(mode.equals(CalculatorViewController.MODE_TEXT_TWO_ZERO)) {
			this.mode = MODE_FLT;
		}
		else if(mode.equals(CalculatorViewController.MODE_TEXT_SCI)) {
			this.mode = MODE_FLT;
		}
		else {
			errorState = true;
			return false;
		}
		
		System.out.println("Set mode to " + this.mode);
		errorState = false;
		return true;
	}

	/** Sets the numerical precision. 
	 * Returns false if the operational mode cannot be set. */
	boolean setPrecision(String precision) {
		
		if(precision.equals(CalculatorViewController.MODE_TEXT_HEX)) {
			this.precision = PR_HEX;
		}
		else if(precision.equals(CalculatorViewController.MODE_TEXT_ONE_ZERO)) {
			this.precision = PR_1Z;
		}
		else if(precision.equals(CalculatorViewController.MODE_TEXT_TWO_ZERO)) {
			this.precision = PR_2Z;
		}
		else if(precision.equals(CalculatorViewController.MODE_TEXT_SCI)) {
			this.precision = PR_SCI;
		}
		else {
			errorState = true;
			return false;
		}
		
		System.out.println("Set precision to " + this.precision);
		errorState = false;
		return true;
	}
	
	// Get functions

	/** Returns the result of the calculation, if valid.
	 * Otherwise, returns an error notification string. */
	String getResult() {
		return calculate();
	}
	
	/** Returns the current error state of the calculation. */
	boolean getErrorState() {
		return errorState;
	}
	
	// Private calculate function
	
	/** Makes a calculation based on current settings and returns the result, if valid. 
	 * In the event of a math error, returns an error notification string. */
	private String calculate() {
		
		Formatter formatter = new Formatter(new StringBuilder());
		String numFormat = null;
		
		switch(precision) {
			case PR_HEX: numFormat = "%x";
				break;
			case PR_1Z: numFormat = "%.1f";
				break;
			case PR_2Z: numFormat = "%.2f";
				break;
			case PR_SCI:numFormat = "%e";
				break;
		}
		
		if (operand1.equals("0") || operand1.equals("0.0")) {
			errorState = true;
			return "Result is undefined";
		}
		
		if (operand2.equals("0") || operand2.equals("0.0")) {
			errorState = true;
			return "Cannot divide by zero";
		}
		
		// hex int mode
		if(mode == MODE_HEX) {
			
			BigInteger numOp1 = new BigInteger(operand1, 16);
			BigInteger numOp2 = new BigInteger(operand2, 16);
			BigInteger numResult = null;
			
			// perform calculation
			try {
				switch(operator) {
					case OP_PLUS: numResult = numOp1.add(numOp2);
						System.out.println(numOp1 + " + " + numOp2);
						break;
					case OP_MINUS: numResult = numOp1.subtract(numOp2);
						System.out.println(numOp1 + " - " + numOp2);
						break;
					case OP_MULTIPLY: numResult = numOp1.multiply(numOp2);
						System.out.println(numOp1 + " * " + numOp2);
						break;
					case OP_DIVIDE: numResult = numOp1.divide(numOp2);
						System.out.println(numOp1 + " / " + numOp2);
						break;
				}
			}
			catch (ArithmeticException ae) {
				// do nothing
			}
			
			System.out.println("= " + numResult);
			
			errorState = false;
			return formatter.format(numFormat, numResult).toString();
		}
		// decimal float mode
		else {
			
			BigDecimal numOp1 = new BigDecimal(operand1);
			BigDecimal numOp2 = new BigDecimal(operand2);
			BigDecimal numResult = null;
			
			// perform calculation
			try {
				switch(operator) {
					case OP_PLUS: numResult = numOp1.add(numOp2);
						System.out.println(numOp1 + " + " + numOp2);
						break;
					case OP_MINUS: numResult = numOp1.subtract(numOp2);
						System.out.println(numOp1 + " - " + numOp2);
						break;
					case OP_MULTIPLY: numResult = numOp1.multiply(numOp2);
						System.out.println(numOp1 + " * " + numOp2);
						break;
					case OP_DIVIDE: numResult = numOp1.divide(numOp2);
						System.out.println(numOp1 + " / " + numOp2);
						break;
				}
			}
			catch (ArithmeticException ae) {
				// do nothing
			}
			
			System.out.println("= " + numResult);
			
			errorState = false;
			return formatter.format(numFormat, numResult).toString();
		}
	}
}
