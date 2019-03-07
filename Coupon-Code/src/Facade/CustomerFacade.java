package Facade;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DBDAO.CustomerDBDAO;
import Java.JavaBean.Coupon;
import Java.JavaBean.CouponType;
import Java.JavaBean.Customer;
import Main.CouponSystem.clientType;

public class CustomerFacade implements CouponClientFacade {

	/**
	 * This class implements the client level of the system. The user login to the
	 * system and the instance will be according to the type of the client. This
	 * level should uses the DAO level( CompanyDBDAO, CustomerDBDAO ) In this level
	 * we will implement the logic of the program. It Contains : Login
	 * purchaseCoupon getAllPurchasedCoupons getAllPurchasedCouponsByType
	 * getAllPurchasedCouponsByPrice getAllCoupons
	 */

	/***************************************
	 * Attributes
	 *****************************************/
	private Customer customerLocaly = new Customer();
	private String CUST_NAME = null;
	private String pass = null;
	private clientType clientType = null;
	private Connection conn;
	private CustomerDBDAO customerDBDAO = new CustomerDBDAO();

	/**************************************
	 * CTOR
	 ***********************************************/
	public CustomerFacade() {
		// TODO//

	}

	/*************************************
	 * Methods
	 ********************************************/
	public Boolean login(String name, String password, clientType cType) throws Exception {
		this.CUST_NAME = name;
		this.pass = password;
		this.clientType = cType;
		this.customerLocaly = customerDBDAO.getCustomer(CUST_NAME);
		// Authentication of the password and company name
		if (customerLocaly.getCustomerName().equals(this.CUST_NAME) && customerLocaly.getPassword().equals(this.pass)
				&& customerLocaly != null) {
			return true;
		} else {
			return false;
		}
	}

	public void purchaseCoupon(Coupon coupon) throws Exception {

		Set<Coupon> allCoupons = new HashSet<Coupon>();
		allCoupons = customerDBDAO.getCustomerCoupons(customerLocaly);
		Iterator<Coupon> itr = allCoupons.iterator();

		while (itr.hasNext()) {
			Coupon coupon2 = new Coupon();
			coupon2 = itr.next();
			System.out.println(coupon);
			System.out.println(coupon2);
			if (coupon2.getTitle().equals(coupon.getTitle())) {
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame, "The Coupon " + coupon.getTitle() + " is already exist");
				return;
			}

		}

		customerDBDAO.purchaseCoupon(coupon, customerLocaly);

	}

	public Set<Coupon> getAllPurchasedCoupons() throws Exception {

		Set<Coupon> allCoupons = new HashSet<Coupon>();
		allCoupons = customerDBDAO.getCustomerCoupons(customerLocaly);

		if (!(allCoupons.isEmpty())) {

			return allCoupons;
		} else {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "To Customer," + customerLocaly.getCustomerName() + " hasn't coupons");
			return null;
		}

	}

	public Set<Coupon> getAllPurchasedCouponsByType(CouponType cType) throws Exception {

		Set<Coupon> allCoupons = new HashSet<Coupon>();
		allCoupons = customerDBDAO.getCustomerCoupons(customerLocaly);
		Set<Coupon> allCouponsByType = new HashSet<Coupon>();
		Coupon coupon = new Coupon();
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");

		if (!(allCoupons.isEmpty())) {
			Iterator<Coupon> itr = allCoupons.iterator();
			while (itr.hasNext()) {
				coupon = itr.next();
				if (coupon.getType().equals(cType)) {

					allCouponsByType.add(coupon);
				}
			}
			if (allCouponsByType.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "To Customer," + customerLocaly.getCustomerName()
						+ " hasn't coupons with type " + cType.name());
				return null;
			} else {
				return allCouponsByType;
			}

		} else {

			JOptionPane.showMessageDialog(frame,
					"To Customer," + customerLocaly.getCustomerName() + " hasn't coupons ");
			return null;
		}
	}

	public Set<Coupon> getAllPurchasedCouponsByPrice(double price) throws Exception {
		// Get all customer coupons
		Set<Coupon> allCoupons = new HashSet<Coupon>();
		allCoupons = customerDBDAO.getCustomerCoupons(customerLocaly);
		Set<Coupon> allPurchasedCouponsByType = new HashSet<Coupon>();
		Coupon coupon = new Coupon();
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");

		if (!(allCoupons.isEmpty())) {
			Iterator<Coupon> itr = allCoupons.iterator();

			while (itr.hasNext()) {
				coupon = itr.next();
				if (coupon.getPrice() <= price) {

					allPurchasedCouponsByType.add(coupon);
				}
			}
			if (allPurchasedCouponsByType.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "To Customer," + customerLocaly.getCustomerName()
						+ " hasn't coupons with price lower than " + price + "[NIS]");
				return null;
			} else {
				return allPurchasedCouponsByType;
			}

		} else {

			JOptionPane.showMessageDialog(frame,
					"To Customer," + customerLocaly.getCustomerName() + " hasn't coupons ");
			return null;
		}
	}

}