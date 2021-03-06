package Facade;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DBDAO.CompanyDBDAO;
import DBDAO.CustomerDBDAO;
import Java.JavaBean.Company;
import Java.JavaBean.Customer;
import Java.Main.CouponSystem.clientType;
import Java.Main.MyLogger;

public class AdminFacade implements CouponClientFacade {

	/**
	 * This class implements the client level of the system. The user login to the
	 * system and the instance will be according to the type of the client. This
	 * level should uses the DAO level( CompanyDBDAO, CustomerDBDAO ) In this level
	 * we will implement the logic of the program. It Contains : Login createCompany
	 * removeCompany updateCoupon getCoupon getAllCoupons
	 */
	/**************************************
	 * Attributes
	 *****************************************/
	private CompanyDBDAO compDAO = new CompanyDBDAO();
	private CustomerDBDAO custDAO = new CustomerDBDAO();
	private final String name = "admin";
	private final String pass = "12346";
	// private String PathFile;
	// private String createDirectoriesPath;

	/****************************************
	 * CTRO
	 *********************************************/
	public AdminFacade() {

		// TODO Auto-generated constructor stub
	}

	/***************************************
	 * Methods
	 *******************************************/
	@Override
	public Boolean login(String name, String password, clientType cType) throws Exception {
		// TODO Auto-generated method stub
		if (name.equals(this.name) && password.equals(this.pass)) {
			MyLogger.logToFile(Level.SEVERE, "Login Customer Done");
			return true;
			// MyLogger.logToFile(Level.SEVERE, "Update Customer Done");
		}

		return false;
		// MyLogger.logToFile(Level.SEVERE, "Update Customer Done");
	}

	public void createCompany(Company company) throws Exception {

		Set<Company> allCompanies = new HashSet<Company>();
		allCompanies = compDAO.getAllCompanies();
		// System.out.println(allCompanies); - Option to print all the companies to
		// console

		Iterator<Company> itr = allCompanies.iterator();

		while (itr.hasNext()) {
			Company company3 = new Company();
			company3 = itr.next();
			if (company3 instanceof Company && company3.getCompName().equals(company.getCompName())) {
				// comparing the creating of the new company if it is a company object and also
				// if it already existing in the system
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame, "Company " + company.getCompName() + "Already Exist");
				MyLogger.logToFile(Level.SEVERE, "Create Company Done");
				return;

			}

		}
		compDAO.createCompany(company);
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		JOptionPane.showMessageDialog(frame, "Company " + company.getCompName() + " Created");

	}

	public Boolean compeareCustomerExsitingAndEqeul(Customer customer) throws Exception {
		Set<Customer> allCustomer = new HashSet<Customer>();
		allCustomer = custDAO.getAllCustomer();

		Iterator<Customer> itr = allCustomer.iterator();

		while (itr.hasNext()) {
			Customer customer3 = new Customer();
			customer3 = itr.next();
			if (customer3 instanceof Customer && customer3.getCustomerName().equals(customer.getCustomerName())) {

				return true;

			}
		}
		return false;
	}

	public void removeCompany(Company company) throws Exception {

		// Update the join Table Company_Coupon and remove the company coupons , first
		// we have to remove the sub-table and the main
		compDAO.removeCompanyCoupons(company);
		// remove the company
		compDAO.removeCompany(company);
		MyLogger.logToFile(Level.SEVERE, "Remove Company Done");
	}

	public void updateCompany(Company company, String newPassword, String newEmail) throws Exception {

		/* Retrieve the company object with the PK by company name */
		Company companyLocaly;
		companyLocaly = compDAO.getCompany(name);
		/* Update the company details except the company name */
		company.setId(companyLocaly.getId());
		company.setPassword(newPassword);
		company.setEmail(newEmail);
		compDAO.updateCompany(company);
		MyLogger.logToFile(Level.SEVERE, "Update Company Done");
	}

	public Company getCompany(long id) throws Exception {
		MyLogger.logToFile(Level.SEVERE, "Get Company Done");
		return compDAO.getCompany(id);

	}

	public Company getCompanybyPW(String password) throws Exception {
		MyLogger.logToFile(Level.SEVERE, "Get Company Done");
		return compDAO.getCompanybyPW(password);

	}

	public Set<Company> getAllCompanies() throws Exception {
		MyLogger.logToFile(Level.SEVERE, "GetAllCompanies Company Done");
		return compDAO.getAllCompanies();

	}

	public void createCustomer(Customer customer) throws Exception {
		Set<Customer> allCustomers = new HashSet<Customer>();
		allCustomers = custDAO.getAllCustomer();
		Iterator<Customer> itrIterator = allCustomers.iterator();

		while (itrIterator.hasNext()) {
			Customer customer2 = new Customer();
			customer2 = itrIterator.next();
			if (customer2 instanceof Customer && customer2.getCustomerName().equals(customer.getCustomerName())) {
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame, "Customer " + customer.getCustomerName() + " Already Exist");
				return;
			}
		}
		custDAO.createCustomer(customer);
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		JOptionPane.showMessageDialog(frame, "Customer " + customer.getCustomerName() + "Created");
		MyLogger.logToFile(Level.SEVERE, "Create Customer Done");
	}

	public void removeCustomer(Customer customer) throws Exception {

		// Update Customer coupons in CUSTOMER_COUPON Table
		custDAO.removeCustomerCoupons(customer);
		// Remove Customer from CUSTOMER Table
		custDAO.removeCustomer(customer);
		MyLogger.logToFile(Level.SEVERE, "Remove  Customer Done");

	}

	public void updateCustomer(Customer customer) throws Exception {

		custDAO.updateCustomer(customer);
		MyLogger.logToFile(Level.SEVERE, "Update Company Done");
	}

	public Customer getCustomer(long id) throws Exception {
		MyLogger.logToFile(Level.SEVERE, "Get Company Done");
		return custDAO.getCustomer(id);

	}

	public Set<Customer> getAllCustomers() throws Exception {
		MyLogger.logToFile(Level.SEVERE, "GetAllCompany Company Done");
		return custDAO.getAllCustomer();

	}

}