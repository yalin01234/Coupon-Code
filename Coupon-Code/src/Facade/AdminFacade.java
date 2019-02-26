package Facade;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DBDAO.CompanyDBDAO;
import DBDAO.CustomerDBDAO;
import Java.JavaBean.ClientType;
import Java.JavaBean.Company;
import Java.JavaBean.Customer;

public class AdminFacade implements CouponClientFacade {

	/**
	 * 
	 * This class implements the client level of the system. The user login to the
	 * 
	 * system and the instance will be according to the type of the client. This
	 * 
	 * level should uses the DAO level( CompanyDBDAO, CustomerDBDAO ) In this level
	 * 
	 * we will implement the logic of the program. It Contains : Login createCompany
	 * 
	 * removeCompany updateCoupon getCoupon getAllCoupons
	 * 
	 * 
	 * 
	 * /**************************************
	 * 
	 * Attributes
	 * 
	 *****************************************/

	private CompanyDBDAO compDAO = new CompanyDBDAO();

	private CustomerDBDAO custDAO = new CustomerDBDAO();

	private final String name = "admin";

	private final String pass = "1234";

	/****************************************
	 * 
	 * CTRO
	 * 
	 *********************************************/

	public AdminFacade() {

		// TODO Auto-generated constructor stub

	}

	/***************************************
	 * 
	 * Methods
	 * 
	 *******************************************/

	// public Boolean login(String name, String password, ClientType cType) throws
	// Exception {

	// TODO Auto-generated method stub

	// if (name.equals(this.name) && password.equals(this.pass)) {

	// return true;

	// }
	// return false;

	// }

	public void createCompany(Company company) throws Exception {

		Set<Company> allCompanies = new HashSet<Company>(); // generate array that checks there is only a unique value
															// in the
															// array SET is interface that implemented a HashSET class

		allCompanies = compDAO.getAllCompanies();// Fetch all the company from the DB

		Iterator<Company> itr = allCompanies.iterator();// Fetch the value from the Hasset collection one by one

		while (itr.hasNext()) {

			Company company2 = new Company();

			company2 = itr.next();

			System.out.println("testcompany" + company2);

			if (company2 instanceof Company && company2.getCompName().equals(company.getCompName())) {

				JFrame frame = new JFrame("JOptionPane showMessageDialog example");

				JOptionPane.showMessageDialog(frame, "Company " + company.getCompName() + " Already Exsiting");

				return;

			}

		}

		compDAO.createCompany(company);

		JFrame frame = new JFrame("JOptionPane showMessageDialog example");

		JOptionPane.showMessageDialog(frame, "Company " + company.getCompName() + " Created");

	}

	public void removeCompany(Company company) throws Exception {

		// Update the join Table Company_Coupon and remove the company coupons

		Set<Company> allCompanies = new HashSet<Company>();
		allCompanies = compDAO.getAllCompanies();
		Iterator<Company> itr = allCompanies.iterator();
		while (itr.hasNext()) {

			Company company2 = new Company();

			company2 = itr.next();
			if (company2 instanceof Company && company2.getCompName().equals(company.getCompName())) {

				compDAO.removeCompanyCoupons(company);

				// remove the company

				compDAO.removeCompany(company);

			}
		}
	}

	public void updateCompany(Company company, String newPassword, String newEmail) throws Exception {

		/* Retrieve the company object with the PK by company name */

		Company companyLocaly;

		companyLocaly = compDAO.getCompany(name);

		if (companyLocaly instanceof Company && companyLocaly.equals(company.getCompName())) {

			/* Update the company details except the company name */

			company.setId(companyLocaly.getId());

			company.setPassword(newPassword);

			company.setEmail(newEmail);

			compDAO.updateCompany(company);

		}
	}

	public Company getCompany(long id) throws Exception {

		return compDAO.getCompany(id);

	}

	public Set<Company> getAllCompanies() throws Exception {

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

	}

	public void removeCustomer(Customer customer) throws Exception {

		// Update Customer coupons in CUSTOMER_COUPON Table

		custDAO.removeCustomerCoupons(customer);

		// Remove Customer from CUSTOMER Table

		custDAO.removeCustomer(customer);

	}

	public void updateCustomer(Customer customer) throws Exception {

		custDAO.updateCustomer(customer);

	}

	public Customer getCustomer(long id) throws Exception {

		return custDAO.getCustomer(id);

	}

	public Set<Customer> getAllCustomers() throws Exception {

		return custDAO.getAllCustomer();

	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

}
