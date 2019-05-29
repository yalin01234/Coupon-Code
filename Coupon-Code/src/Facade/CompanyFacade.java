package Facade;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import Java.JavaBean.Company;
import Java.JavaBean.Coupon;
import Java.JavaBean.CouponType;
import Java.Main.CouponSystem.clientType;

public class CompanyFacade implements CouponClientFacade { // implement the CouponClientFacade Interface for the Login
															// Purpose.

	/**
	 * This class implements the client level of the system. The user login to the
	 * system and the instance will be according to the type of the client. This
	 * level should uses the DAO level ( couponDBDAO, CompanyDBDAO ) In this level
	 * we will implement the logic of the program. It Contains : Login createCoupon
	 * removeCoupon updateCoupon getCoupon getAllCoupons
	 */

	/**************************************
	 * Attributes
	 *****************************************/
	private Company company = new Company(); // Initiation of the Company class
	private Connection conn;
	private long ID_comp;
	private String compName = null;
	private String pass = null;
	private CouponDBDAO couponDBDAO = new CouponDBDAO(); // Initiation of the Coupon DBDAO class
	private CompanyDBDAO companyDBDAO = new CompanyDBDAO(); // Initiation of the Company DBDAO class

	/***************************************
	 * CTRO Partial Constructor
	 ***************************************/
	public CompanyFacade() {

	}

	/**************************************
	 * Methods
	 * 
	 * @throws DBException
	 *******************************************/
	@Override
	public Boolean login(String name, String password, clientType cType) throws Exception {

		String compName = name;
		String pass = password;
		// System.out.println(compName);
		// Create instance locally of company
		company = getCompany(compName); //
		System.out.println(company);

		// Authentication of the password and company name

		if (company.getCompName().equals(this.compName) && company.getPassword().equals(this.pass) && company != null) {
			return true;
		} else {
			return false;
		}

	}

	public void createCoupon(Coupon coupon) throws Exception {
		Set<Coupon> allCoupons = new HashSet<Coupon>();
		allCoupons = getAllCoupons();
		Iterator<Coupon> itr = allCoupons.iterator();
		while (itr.hasNext()) {

			Coupon coupon2 = new Coupon();
			coupon2 = itr.next();
			if (coupon2 instanceof Coupon && coupon2.getTitle().equals(coupon.getTitle())) {
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame, "Coupon " + coupon.getTitle() + " Already Exist");
				return;
			}

		}

		couponDBDAO.createCoupon(coupon, company.getId());
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		JOptionPane.showMessageDialog(frame, "Created coupon " + coupon.getTitle() + " successfully");
	}

	public void removeCoupon(Coupon coupon) throws Exception {

		// Remove and update Customer_Coupon Table
		couponDBDAO.removeCustomerCoupon(coupon);
		// Remove and update Company_Coupon table
		couponDBDAO.removeCompanyCoupon(coupon);
		// Remove coupon from Coupon table
		couponDBDAO.removeCoupon(coupon);

	}

	public void updateCoupon(Coupon coupon) throws Exception {

		couponDBDAO.updateCoupon(coupon);

	}

	public Coupon getCoupon(long id) {
		return null;

	}

	public Set<Coupon> getAllCoupons() throws Exception

	{
		Set<Coupon> allCoupons = new HashSet<Coupon>();
		allCoupons = couponDBDAO.getAllCoupouns();

		return allCoupons;

	}

	public Company getCompany(String compName) throws Exception {

		Company companyLocaly = new Company(); // Initiation of the Company object
		Set<Company> allCompanies = new HashSet<Company>(); // Initiation SET collection if HashSET
		allCompanies = companyDBDAO.getAllCompanies(); // Invoke the getAllCompanies method from Company DBDAO.
		System.out.println(allCompanies);

		Iterator<Company> itr = allCompanies.iterator(); // Initiation Iterator to verify

		while (itr.hasNext()) {
			companyLocaly = itr.next();
			if (companyLocaly.getCompName().equals(compName)) { // Comparing the company from DataBase if it equals to
																// company name
				return companyLocaly;

			}

		}
		return null;

	}

	public Company getCompnay(String pass) throws Exception {
		Company companyLocalypass = new Company();
		Set<Company> allCompaniespass = new HashSet<Company>();
		allCompaniespass = companyDBDAO.getAllCompanies();
		Iterator<Company> itr = allCompaniespass.iterator();

		while (itr.hasNext()) {
			companyLocalypass = itr.next();
			if (companyLocalypass.getPassword().equals(pass)) {
				return companyLocalypass;
			}
		}
		return null;

	}

	public Set<Coupon> getCompanyCoupons(Company company) throws Exception {

		Set<Coupon> allCoupons = new HashSet<Coupon>();
		allCoupons = companyDBDAO.getCompanyCoupons(company);

		if (!(allCoupons.isEmpty())) {

			return allCoupons;
		} else {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "To a comapny, " + company.getCompName() + " hasn't coupons");
			return null;
		}

	}

	public synchronized Set<Coupon> getCouponByType(CouponType type) throws Exception {

		Set<Coupon> coupons2 = new HashSet<Coupon>();
		Set<Coupon> coupons = new HashSet<Coupon>();
		coupons = getCompanyCoupons(company);
		Iterator<Coupon> itr = coupons.iterator();

		while (itr.hasNext()) {
			Coupon coupon2 = new Coupon();
			coupon2 = itr.next();
			// Check the type of the Coupon
			if (type.equals(coupon2.getType())) {

				coupons2.add(coupon2);

			}

		}
		if (coupons2.isEmpty()) {

			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame,
					"To comapny, " + company.getCompName() + " hasn't coupons in type" + type.name());
			return null;
		}

		return coupons2;
	}

	public synchronized Set<Coupon> getCouponsByPrice(Double priceLimt) throws Exception {

		Set<Coupon> coupons2 = new HashSet<Coupon>();
		Set<Coupon> coupons = new HashSet<Coupon>();
		coupons = getCompanyCoupons(company);
		Iterator<Coupon> itr = coupons.iterator();

		while (itr.hasNext()) {
			Coupon coupon2 = new Coupon();
			coupon2 = itr.next();
			// Check the type of the Coupon
			if (priceLimt >= coupon2.getPrice()) {

				coupons2.add(coupon2);

			}

		}
		if (coupons2.isEmpty()) {

			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame,
					"To comapny , " + company.getCompName() + " hasn't coupons that cost " + priceLimt);
			return null;
		}

		return coupons2;

	}

	public synchronized Set<Coupon> getCouponsByExpiredDate(java.util.Date date) throws Exception {

		Set<Coupon> coupons2 = new HashSet<Coupon>();
		Set<Coupon> coupons = new HashSet<Coupon>();
		coupons = getCompanyCoupons(company);
		Iterator<Coupon> itr = coupons.iterator();// using with Iterator to fetch the data from the DB.

		while (itr.hasNext()) {
			Coupon coupon2 = new Coupon();
			coupon2 = itr.next();
			// Check the type of the Coupon
			if (date.compareTo(coupon2.getEndDate()) > 0) {

				coupons2.add(coupon2);

			}

		}
		if (coupons2.isEmpty()) {

			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame,
					"To comapny , " + company.getCompName() + " hasn't coupons before date" + date);
			return null;
		}

		return coupons2;
	}

}