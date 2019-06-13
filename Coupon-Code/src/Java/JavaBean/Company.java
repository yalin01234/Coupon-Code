package Java.JavaBean;

import java.util.ArrayList;

/**
 * @Author - Yalin
 */

public class Company {

	// Attributes

	private long id;
	private String compName;
	private String password;
	private String email;
	ArrayList<Coupon> coupons = new ArrayList<Coupon>();

	/**
	 * @param no
	 * @param -
	 *            an empty Constructor
	 */

	public Company() {
	}

	/**
	 * @param id
	 *            - The Company ID
	 * @param CompName
	 *            - The Company name
	 * @param password
	 *            - The password for login to CompanyUI
	 * @param email
	 *            - The email for login to CompanyUI
	 */

	public Company(long id, String compName, String password, String email) {
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;

	} // Full contractor

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", coupons=" + coupons + "]";
	}
}