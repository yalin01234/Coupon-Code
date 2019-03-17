package Java.Main;

import DB.Database;
import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;
import Facade.AdminFacade;
import Facade.CompanyFacade;
import Java.JavaBean.Company;
import Java.JavaBean.Coupon;
import Java.JavaBean.CouponType;
import Java.JavaBean.Customer;
import Java.Main.CouponSystem.clientType;

public class Test {

	public static void main(String[] args) throws Exception {

		Class.forName("org.apache.derby.jdbc.ClientDriver");

		Database.getDatabase();
		// Company company12 = new Company(12, "Walla", "3344556677",
		// "Ruobem@test.com");

		// adminFacade.removeCompany(company12);

		// Company company12 = new Company(12, "Walla", "3344556677",
		// "Ruobem@test.com");

		// Company company = new Company(2, "Shoshana", "12345", "oriel@test.com");
		// Coupon coupon = new Coupon(4, "ConnPool", Utils.getDriverData(),
		// Utils.endDate(20), 7000, CouponType.CAMPING, "wtf",
		// 251, "image");

		// Customer customer1 = new Customer(1, "Evi", "345345");
		// Customer customer2 = new Customer(2, "Shay", "567567");
		// Customer customer3 = new Customer(3, "Moahe", "345345");
		// Customer customer4 = new Customer(4, "Eli", "334455");
		// Customer customer5 = new Customer(5, "Yossi", "889977");
		// Customer customer6 = new Customer(6, "Mosheyacov", "852852");
		// Customer customer7 = new Customer(7, "Uria", "0520520");
		// Customer customer8 = new Customer(8, "Leon", "0540540");
		// Customer customer9 = new Customer(9, "Sarge", "546546");
		// Customer customer10 = new Customer(10, "Jurgen", "868686");

		// Coupon coupon1 = new Coupon(1, "Omer", Utils.getDate(), Utils.endDate(60),
		// 1111, CouponType.HEALTH, "wtf", 1251,
		// "image");
		// Coupon coupon2 = new Coupon(2, "Oren", Utils.getDate(), Utils.endDate(60),
		// 2222, CouponType.HEALTH, "abc", 3356,
		// "image");
		// Coupon coupon3 = new Coupon(3, "Ramon", Utils.getDate(), Utils.endDate(60),
		// 3333, CouponType.HEALTH, "der",
		// 4455, "image");
		// Coupon coupon4 = new Coupon(4, "Sarge", Utils.getDate(), Utils.endDate(60),
		// 4444, CouponType.HEALTH, "nmmm",
		// 55562, "image");
		// Coupon coupon5 = new Coupon(5, "Moshe", Utils.getDate(), Utils.endDate(60),
		// 5555, CouponType.HEALTH, "111",
		// 8888, "image");
		// Coupon coupon7 = new Coupon(7, "Yuyuyuyu", Utils.getDate(),
		// Utils.endDate(60), 4545454, CouponType.HEALTH,
		// "222222", 789789, "image");
		// Coupon coupon8 = new Coupon(8, "uyuyuyuyuyu", Utils.getDate(),
		// Utils.endDate(60), 123123123, CouponType.HEALTH,
		// "13131313", 852852, "image");

		Company company1 = new Company(1, "mPrest", "12345", "oriel@test.com");
		Company company2 = new Company(2, "Checkpoint", "12345", "Moshe@test.com");
		Company company3 = new Company(3, "UTREEEE", "55662322", "Utr@test.com");
		Coupon coupon1 = new Coupon(1, "Omer", Utils.getDate(), Utils.endDate(60), 1111, CouponType.HEALTH, "wtf", 1251,
				"image");
		Coupon coupon2 = new Coupon(2, "Oren", Utils.getDate(), Utils.endDate(60), 2222, CouponType.HEALTH, "abc", 3356,
				"image");

		Customer customer1 = new Customer(1, "Evi", "898989");
		Customer customer2 = new Customer(2, "Shay", "567567");

		// AdminFacade adminFacade = new AdminFacade();

		CustomerDBDAO customerDBDAO = new CustomerDBDAO();

		/********* Admin **************/
		AdminFacade adminFacade2 = new AdminFacade();
		adminFacade2 = (AdminFacade) CouponSystem.getCouponSystem().login("admin", "12346", clientType.Admin);
		if (adminFacade2 != null) {
			// adminFacade2.createCompany(company3);
			adminFacade2.getAllCompanies();
			adminFacade2.getAllCustomers();
			// adminFacade2.getCompanybyPW("55662322");

		}

		// customerDBDAO.purchaseCoupon(coupon1, customer1);
		// customerDBDAO.getCustomer1("898989");

		// customerDBDAO.createCustomer(customer2);
		// customerDBDAO.getCustomer("Evi");
		// customerDBDAO.getCoupons();
		// customerDBDAO.getAllCustomer();
		// customerDBDAO.getCustomerCoupons(customer1);
		// customerDBDAO.printAllCustmers();

		// customerDBDAO.updateCustomer(customer1);
		// customerDBDAO.

		// adminFacade.createCompany(company1);
		// adminFacade.createCompany(company2);

		// adminFacade.removeCompany(company2);
		// adminFacade.removeCompany(company1);
		// adminFacade.getAllCompanies();
		// adminFacade.updateCustomer(customer1);
		// adminFacade.createCustomer(customer2);

		// adminFacade.updateCustomer(customer1);

		CompanyFacade companyFacade = new CompanyFacade();
		CouponDBDAO couponDBDAO = new CouponDBDAO();

		// couponDBDAO.createCoupon(coupon1, 1);
		// couponDBDAO.createCoupon(coupon2, 2);

		// adminFacade.removeCompany(company2);
		// couponDBDAO.removeCoupon(coupon1);
		// couponDBDAO.removeCustomerCoupon(coupon2);

		// couponDBDAO.createCoupon(coupon1, 1);
		// couponDBDAO.createCoupon(coupon2, 2);

		// companyFacade.createCoupon(coupon1);
		// companyFacade.createCoupon(coupon2);

		// adminFacade.createCompany(company1);
		// adminFacade.createCompany(company2);

		// Company company3 = new Company(3, "Checkmarx", "121212", "Uria@test.com");
		// Company company4 = new Company(4, "Greenlight", "445566", "Leon@test.com");
		// Company company5 = new Company(5, "Kobicom", "889944", "Meital@test.com");
		// Company company6 = new Company(6, "Amdocs", "525252", "Jurgen@test.com");
		// Company company7 = new Company(7, "Giga", "665588", "Serge@test.com");
		// Company company8 = new Company(8, "Huhuea", "889955", "Emual@test.com");
		// Company company9 = new Company(9, "Moshecom", "585858", "David@test.com");
		// Company company10 = new Company(10, "Yyuvalcom", "4454545",
		// "Ramon@test.com");

		// Company company11 = new Company(11, "Nanalcom", "55669988", "Oren@test.com");
		// Company company12 = new Company(12, "Walla", "3344556677",
		// "Ruobem@test.com");

		// CompanyDBDAO companyDBDAO = new CompanyDBDAO();

		// companyDBDAO.createCompany(company11);

		// CustomerDBDAO customerDBDAO = new CustomerDBDAO();
		// customerDBDAO.createCustomer(customer1);
		// customerDBDAO.createCustomer(customer2);
		// customerDBDAO.createCustomer(customer3);
		// customerDBDAO.createCustomer(customer4);
		// customerDBDAO.createCustomer(customer5);
		// customerDBDAO.createCustomer(customer6);
		// customerDBDAO.createCustomer(customer7);
		// customerDBDAO.createCustomer(customer8);
		// customerDBDAO.createCustomer(customer9);
		// customerDBDAO.createCustomer(customer10);

		// CouponDBDAO couponDBDAO = new CouponDBDAO();
		// couponDBDAO.createCoupon(coupon8, 1);

		// couponDBDAO.createCoupon(coupon1);
		// couponDBDAO.createCoupon(coupon2);
		// couponDBDAO.createCoupon(coupon3);
		// couponDBDAO.createCoupon(coupon4);
		// couponDBDAO.createCoupon(coupon5);

		// customerDBDAO.getCustomer(1);
		// customerDBDAO.printAllCustmers();

		// customerDBDAO.createCustomer(customer1);
		// customerDBDAO.removeCustomer(customer1);
		// customerDBDAO.removeCustomer(customer2);
		// customerDBDAO.removeCustomer(customer3);
	}
}
