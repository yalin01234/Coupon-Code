package Java.Main;

import DB.Database;
import DBDAO.CompanyDBDAO;
import DBDAO.CustomerDBDAO;
import Java.JavaBean.Company;
import Java.JavaBean.Customer;

public class Test {

	public static void main(String[] args) throws Exception {

		Class.forName("org.apache.derby.jdbc.ClientDriver");

		Database.getDatabase();

		Company company = new Company(2, "Shoshana", "12345", "oriel@test.com");

		// Coupon coupon = new Coupon(4, "ConnPool", Utils.getDriverData(),
		// Utils.endDate(20), 7000, CouponType.CAMPING, "wtf",
		// 251, "image");

		Customer customer2 = new Customer(1, "Evi", "345345");
		Customer customer3 = new Customer(2, "Shay", "567567");
		Customer customer4 = new Customer(3, "Moahe", "345345");
		Customer customer5 = new Customer(4, "Eli", "334455");
		Customer customer6 = new Customer(5, "Ramon", "889977");

		// Coupon coupon1 = new Coupon(3, "Omer", Utils.getDate(), Utils.endDate(60),
		// 5555, CouponType.HEALTH, "wtf", 1251,
		// "image");

		Company company2 = new Company(3, "mPrest", "12345", "oriel@test.com");
		Company company3 = new Company(5, "Moshe", "12345", "Moshe@test.com");

		CompanyDBDAO companyDBDAO = new CompanyDBDAO();
		// companyDBDAO.createCompany(company3);
		// companyDBDAO.createCompany(company2);

		CustomerDBDAO customerDBDAO = new CustomerDBDAO();
		// customerDBDAO.createCustomer(customer3);
		// customerDBDAO.createCustomer(customer2);
		// customerDBDAO.createCustomer(customer4);
		// customerDBDAO.createCustomer(customer5);

		// customerDBDAO.getCustomer(1);
		// customerDBDAO.printAllCustmers();

		// customerDBDAO.createCustomer(customer1);
		// customerDBDAO.removeCustomer(customer1);
		// customerDBDAO.removeCustomer(customer2);
		// customerDBDAO.removeCustomer(customer3);
	}
}
