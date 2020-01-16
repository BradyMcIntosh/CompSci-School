package dateNight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Formatter;

import java.nio.file.Paths;
import java.io.IOException;

import cst8132.restaurant.Appetizer;
import cst8132.restaurant.Drink;
import cst8132.restaurant.MenuItem;

/**
 * A restaurant bill, specifying ordered items and people to be billed.
 * 
 * @author Brady McIntosh
 * @author Angela Giddings
 */
public class Bill {

	private HashMap<String,ArrayList<MenuItem>> orders = new HashMap<String,ArrayList<MenuItem>>(4);
	
	private boolean isHappyHour = false;
	private double subtotal;
	private double hstRate = 0.15;
	private final int MAX_MENU_ITEM_LENGTH = 30;
	
	private Formatter output;
	
	/**
	 * Add an ordered item to the bill.
	 * 
	 * @param guest The guest to be billed.
	 * @param item The item to be orderd.
	 * @return Returns true.
	 */
	public boolean addOrderItem(String guest, MenuItem item) {
		
		ArrayList<MenuItem> o = orders.getOrDefault(guest, new ArrayList<MenuItem>(4));
		o.add(item);
		
		orders.put(guest, o);
		
		subtotal += item.getPrice();

		return true;
	}
	
	/**
	 * Sets whether or not it is happy hour.
	 * 
	 * @param isHappyHour The status of happy hour.
	 */
	public void setHappyHour(boolean isHappyHour) {
		
		this.isHappyHour = isHappyHour;
	}
	
	/**
	 * Returns the status of happy hour - active or inactive, true or false.
	 * 
	 * @return Returns happy hour's boolean status.
	 */
	public boolean getHappyHour() {
		
		return isHappyHour;
	}
	
	/**
	 * Returns a HashMap list of orders.
	 * 
	 * @return Returns the list of orders.
	 */
	public HashMap<String, ArrayList<MenuItem>> getOrders() {
		
		return orders;
	}
	
	/**
	 * Returns the bill's subtotal, not including discounts or tax.
	 * 
	 * @return Returns the subtotal.
	 */
	public double getSubtotal() {
		
		return subtotal;
	}
	
	/**
	 * Returns the amount of money in tax, taking discounts into account.
	 * 
	 * @return Returns the sum of tax.
	 */
	public double getHst() {
		
		return (getSubtotal() - getHappyHourDiscount()) * getHstRate();
	}
	
	/**
	 * Returns the rate of tax.
	 * 
	 * @return Returns the tax rate.
	 */
	public double getHstRate() {
		
		return hstRate;
	}
	
	/**
	 * Returns the bill's total, taking discounts and tax into account.
	 * 
	 * @return Returns the total.
	 */
	public double getTotal() {
		
		return getSubtotal() - getHappyHourDiscount() + getHst();
	}
	
	/**
	 * Returns the amount of money discounted by happy hour.
	 * 
	 * @return Returns the happy hour discount.
	 */
	public double getHappyHourDiscount() {
		
		double happyHourDiscount = 0;
		
		if (!isHappyHour)
			return 0;
		
		for (ArrayList<MenuItem> a : orders.values()) {
			
			for (MenuItem m : a) {
		
				if (m instanceof Drink) {
					happyHourDiscount += 2;
				}
				if (m instanceof Appetizer) {
					happyHourDiscount += m.getPrice() / 2;
				}
			}
		}
		
		return happyHourDiscount;
	}
	
	/**
	 * Returns all bill information in a formatted String.
	 */
	public String toString() {
		
		try {
			output = new Formatter("C:\\Users\\Brady\\eclipse-workspace\\Lab6Date\\src\\dateNight\\bill.txt");
		}
		catch (IOException ioe) {
			System.out.println("Error: bill.txt could not be created.");
		}
		
		String s = "";
		String format = "\t%-" + MAX_MENU_ITEM_LENGTH + "s \t $%6.2f%n";
		
		for (String o : orders.keySet()) {
			
			s += "Dinner Guest: " + o + "\n";
			output.format("%s%n", "Dinner Guest: " + o);
			
			for (MenuItem item : orders.get(o)) {
				
				s += String.format(format, item.getName(), item.getPrice());
				output.format(format, item.getName(), item.getPrice());
			}
		}
		
		s += String.format(format, "Subtotal", getSubtotal());
		output.format(format, "Subtotal", getSubtotal());
		
		s += String.format(format, "Happy Hour Discount", getHappyHourDiscount());
		output.format(format, "Happy Hour Discount", getHappyHourDiscount());
		
		s += String.format(format, "HST " + (int) (hstRate * 100) + "%", getHst());
		output.format(format, "HST " + (int) (hstRate * 100) + "%", getHst());
		
		s += String.format(format, "Total", getTotal());
		output.format(format, "Total", getTotal());
		
//		System.out.println("Formatter closed.");
		output.close();
		
		return s;
	}
	
}
