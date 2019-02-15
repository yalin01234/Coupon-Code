package Java.JavaBean;

import java.util.ArrayList;

/**
 * @Author - Yalin
 */

public class Customer {

	// Attributes

	private long id;
	private String CustomerName;
	private String password;
	private ArrayList<Coupon> coupons = new ArrayList<Coupon>();

	// Ctors

	/**
	 * @param no
	 * 			@param - an empty Constructor
	 */

	public Customer() {
	}

	/**
	 * @param id
	 *            - The Customer ID
	 * @param password
	 *            - The password for login to CustomerUI
	 * @param email
	 *            - The email for login to CustomerUI
	 */

	public Customer(long id, String name, String pass) {
		this.id = id;
		this.CustomerName = name;
		this.password = pass;

	}

	// Getters & Setters

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	// Other methods

	@Override
	public String toString() {
		return "Customer [id=" + id + ", CustomerName=" + CustomerName + ", password=" + password + ", coupons="
				+ coupons + "]";
	}

}