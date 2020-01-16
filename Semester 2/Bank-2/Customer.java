package lab5;

/**
 * Contains information about and methods for an account-holding customer.
 * 
 * @author Brady McIntosh
 * @author Angela Giddings
 */
public class Customer implements Comparable<Customer> {

	private String firstName;
	private String lastName;
	private String email;
	private long phoneNum;

	/**
	 * Standard four-args constructor. Initializes first name, last name, email
	 * address and phone number.
	 * 
	 * @param firstName
	 *            The customer's first name.
	 * @param lastName
	 *            The customer's last name.
	 * @param email
	 *            The customer's email address.
	 * @param phoneNum
	 *            The customer's phone number.
	 */
	public Customer(String firstName, String lastName, String email, long phoneNum) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phoneNum;
	}

	/**
	 * Returns the first and last of the customer, separated by a space.
	 * 
	 * @return Returns the customer's full name.
	 */
	public String getName() {

		return firstName + " " + lastName;
	}

	/**
	 * Returns all the customer's information in three lines.
	 */
	public String toString() {

		return "Name:  " + firstName + " " + lastName + "\n" + "Email: " + email + "\n" + "Phone: " + phoneNum;
	}

	/**
	 * Compares this customer to another customer, given as a parameter.
	 * 
	 * @param customer
	 *            The customer to be compared to.
	 */
	public int compareTo(Customer customer) {

		return getName().compareTo(customer.getName());
	}
}
