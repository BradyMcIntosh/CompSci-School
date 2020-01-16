package dateNight;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.NoSuchElementException;

import cst8132.restaurant.Restaurant;
import cst8132.restaurant.Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Date simulator. Contains logic and GUI to prompt user for a list of names, 
 * choose a random number of these names and put on a date of appropriate magnitude.
 * 
 * @author Brady McIntosh
 */
public class DoubleDate extends JFrame {
	
	private ArrayList<String> guests;
	private String[] movies;
	private String movieTitle;
	private int movieTime;
	private Restaurant restaurant;
	private Menu menu;
	private Bill bill;
	
	private JPanel inputPanel;
	private JPanel guestList;
	private JLabel addGuestPrompt;
	private JLabel guestListHeader;
	private JTextField newGuestName;
	private JButton addGuest;
	private JButton letsGo;
	
	private static Random random = new Random();
	
	/**
	 * No-arg constructor for this class. Defines GUI and member variables,
	 * including guest list, menu and bill.
	 */
	public DoubleDate() {
		
		super("Double Date");
		
		setLayout(new GridLayout(2,2));
		
		// creating and adding a text prompt for guest names
		addGuestPrompt = new JLabel("Enter a guest name: ");
		addGuestPrompt.setBorder(BorderFactory.createLineBorder(Color.black));
		add(addGuestPrompt);
		
		// creating and adding a header for the list of guests
		guestListHeader = new JLabel("Guest List");
		guestListHeader.setBorder(BorderFactory.createLineBorder(Color.black));
		add(guestListHeader);
		
		// creating an input panel for holding buttons and other inputs
		inputPanel = new JPanel(new FlowLayout());
		inputPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// creating a field for entering the name of guests
		newGuestName = new JTextField(20);
		// adding it to the input panel
		inputPanel.add(newGuestName);
		
		// creating a button to add the specified guest to the list
		addGuest = new JButton("Add Guest to List");
		// adding an event handler to the button
		addGuest.addActionListener(new AddGuestHandler());
		// adding it to the input panel
		inputPanel.add(addGuest);
		
		// creating a button to initiate the double date
		letsGo = new JButton("Let's Go Out!");
		// really messy anonymous class definition for action listener
		letsGo.addActionListener( new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				goOnDate((DoubleDate) SwingUtilities.getRoot(
						(Component) e.getSource()));
				setVisible(false);
				dispose();
			}
		} );
		// adding it to the input panel
		inputPanel.add(letsGo);
		
		// making the initiate button invisible
		letsGo.setVisible(false);
		
		// adding the input panel
		add(inputPanel);
		
		// creating and adding a list of guests
		guestList = new JPanel(new FlowLayout());
		guestList.setBorder(BorderFactory.createLineBorder(Color.black));
		add(guestList);
		
		// setting close operation and size 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 250);
		
		// adding guest names to the list
		guests = new ArrayList<String>(4); // delete?
		
		// initializing other objects
		restaurant = Restaurant.getInstance();
		menu = restaurant.getMenu();
		addMenuItems();
		bill = new Bill();
		
		// creates a short list of (very, very bad) movies
		movies = new String[]{"Bee Movie", "Emoji Movie", "Angry Birds Movie",
				"The Spongebob Squarepants Movie", "Fred: The Movie"};
	}
	
	/**
	 * Takes a String array and returns a random value from that array.
	 * 
	 * @param movies The given array. 
	 * 
	 * @return Returns a random value from the given array.
	 */
	public String pickAMovie(String[] movies) {
		
		// returns a random movie title from the movies list
		return movies[random.nextInt(movies.length)];
	}
	
	/**
	 * Randomly returns either 6 or 10.
	 * 
	 * @return Returns 6 or 10, randomly.
	 */
	public int getShowing() {
		
		// pick either 6 or 10 randomly
		return (random.nextInt(2) * 4) + 6;
	}
	
	/**
	 * Adds various food items to the menu.
	 */
	public void addMenuItems() {
		
		// define a new Scanner for file input
		Scanner input = null;
		
		// try to initialize the Scanner with menu.txt as the source file
		try {
			input = new Scanner(Paths.get("C:\\Users\\Brady\\eclipse-workspace\\Lab6Date\\src\\dateNight\\menu.txt"));
		}
		catch (IOException ioe) {
			System.out.println("Error: menu.txt does not exist.");
		}
		
		// 2d array containing details on menu items
		String[][] menuItems = new String[16][3];
		
		// if the Scanner is initialized
		if(input != null) {
			int i = 0;
			
			// parse each line of menu.txt and set the corresponding array elements
			try {
				while (input.hasNextLine() && i < 16) {
					String tempLine = input.nextLine();
					int x1 = tempLine.indexOf(',');
					String tempL1 = tempLine.substring(0,x1);
					int x2 = tempLine.indexOf(',', x1+1);
					String tempL2 = tempLine.substring(x1+1,x2);
					String tempL3 = tempLine.substring(x2+1);
					
					menuItems[i][0] = tempL1;
					menuItems[i][1] = tempL2;
					menuItems[i][2] = tempL3;
					
					i++;
				}
			}
			catch (NoSuchElementException nse) {
				System.out.println("Error: No such element at line " + i);
			}
			catch (IllegalStateException ise) {
				System.out.println("Error: Illegal state at line " + i);
			}
			catch (Exception e) {
				System.out.println("Error: Unknown error at line " + i);
			}
		}
		
		// cycle through the temporary array of items and add each to the menu
		for (int i = 0; i < menuItems.length; i++) {
			try {
			menu.addMenuItem(menuItems[i][0], menuItems[i][1], Double.valueOf(menuItems[i][2]));
			}
			catch (NumberFormatException nfe) {
				System.out.printf("Error: Invalid double @ menuItem[%d][2]%n",i);
			}
		}
	}
	
	/**
	 * Picks a random menu item of the given type and adds it to the given guest's bill. 
	 * Returns the return value of the bill's addOrderItem method.
	 * 
	 * @param guest The given guest.
	 * @param itemType The given item type.
	 * @return Returns the return value of bill.addOrderItem().
	 */
	public boolean placeOrder(String guest, String itemType) {
		
		// adds a random item from the menu to the specified guest's order
		return bill.addOrderItem(guest, menu.getRandomMenuItem(itemType));
	}
	
	/**
	 * Contains logic and method calls relating to date activities. 
	 * 
	 * @param date The given date.
	 */
	public void goOnDate(DoubleDate date) {

		// set the movie title and time
		date.movieTitle = date.pickAMovie(date.movies);
		date.movieTime = date.getShowing();
		
		// set happy hour to true, if the movie time allows it
		date.bill.setHappyHour(movieTime == 10);
		
		// loop through all guests (including host) to determine orders
		for(int i = 0; i < date.guests.size(); i++) {
			
			// the first guest (host) orders an appetizer
			if(i == 0) {
				date.placeOrder(date.guests.get(i), "Appetizers");
			}
			
			// everyone orders an entree and a drink
			date.placeOrder(date.guests.get(i), "Entrees");
			date.placeOrder(date.guests.get(i), "Drinks");
			
			// the first guest (host) orders a dessert
			if(i == 0) {
				date.placeOrder(date.guests.get(i), "Desserts");
			}
			// if there are 3 or more total guests, the third guest orders a dessert
			if(date.guests.size() > 2) {
				if(i == 2) {
					date.placeOrder(date.guests.get(i), "Desserts");
				}
			}
		}
		
		System.out.println(date);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	// not sure if I should even add documentation for this, since it's private?
	/**
	 * Nested ActionListener class to handle adding guests via a button.
	 * 
	 * @author Brady McIntosh
	 */
	private class AddGuestHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			// only does anything if the guest name field has text in it
			if(newGuestName.getText().length() > 0) {
				
				// gets the value of the text entered in newGuestName field
				String newGuest = newGuestName.getText();
				
				// adds this text to the logical guest list and resets the text field
				guests.add(newGuest);
				newGuestName.setText("");
				
				// adds a corresponding entry to the graphical guest list
				guestList.add(new JLabel(newGuest));
				
				letsGo.setVisible(true);
			
				addGuest.setVisible(guests.size() < 4);
				
				guestList.validate();
				guestList.repaint();
			}
		}
	}

	/**
	 * Main method. Constructs an instance of this class, makes the GUI visible,
	 * then prints the date information to console once the date commences.
	 * 
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		
		// construct a GUI and guest-less date
		DoubleDate date = new DoubleDate();
		
		date.setVisible(true);
	}
	
	/**
	 * Prints out all date information like guest list, menu, bill, etc. 
	 * 
	 * @return Returns a String containing formatted date information.
	 */
	@Override
	public String toString() {
		
		String ret = new String();
		
		ret = ret + "Movie options: ";
		
		// adds all movie titles, with commas between them
		for(int i = 0; i < movies.length; i++) {
			ret += movies[i];
			if(i < movies.length - 1) {
				ret += ", ";
			}
			// stops the list from getting too long horizontally
			if(i > 0 && i % 2 == 0) {
				ret += "\n\t";
			}
		}
		ret += "\n";
		
		// ex: "Selected movie: Bee Movie"
		ret += String.format("Selected movie: %s\n", movieTitle);
		
		// ex: "Show time: 6:00pm"
		ret += String.format("Show time: %d:00pm\n", movieTime);
		
		// adds name of restaurant and whether dinner will be 
		// before the movie or vice-versa
		ret += "Arriving at Rigucci's Pizza and Grill ";
		if(movieTime == 6) {
			ret += "after the movie.";
		}
		else {
			ret += "before the movie.";
		}
		ret += "\n";
		
		// adds a message if happy hour is on or off
		if(bill.getHappyHour()) {
			ret += "It's happy hour! $2 off drinks, and 1/2 price appetizers!\n";
		}
		else {
			ret += "We'll be missing happy hour, but we'll still be happy!\n";
		}
		
		// adds toStrings of other associated classes
		ret += menu.toString();
		ret += bill.toString();
		
		return ret;
	}
}
