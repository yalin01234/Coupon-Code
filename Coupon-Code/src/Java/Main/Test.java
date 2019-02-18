package Java.Main;

import DBDAO.CompanyDBDAO;
import Java.JavaBean.Company;
import Java.JavaBean.Customer;

public class Test {

	public static void main(String[] args) throws Exception {

		// Database.getDatabase();
		// Class.forName("org.apache.derby.jdbc.ClientDriver");

		Company company = new Company(2, "Shoshana", "12345", "oriel@test.com");

		// oupon coupon = new Coupon(4, "ConnPool", Utils.getDriverData(),
		// Utils.endDate(20), 7000, CouponType.CAMPING, "wtf",
		// 251, "image");

		Customer customer = new Customer(3, "Snir", "1234");

		Customer customer2 = new Customer(4, "Evi", "221284");

		// Coupon coupon1 = new Coupon(3, "Omer", Utils.getDate(), Utils.endDate(60),
		// 5555, CouponType.HEALTH, "wtf", 1251,
		// "image");

		Company company2 = new Company(3, "mPrest", "12345", "oriel@test.com");

		CompanyDBDAO companyDBDAO = new CompanyDBDAO();
		companyDBDAO.createCompany(company2);
	}
}
