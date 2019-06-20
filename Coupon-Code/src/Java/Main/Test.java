package Java.Main;

import java.util.logging.Level;

import DB.Database;
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

		FileCreation.createFile();
		MyLogger.logToFile(Level.WARNING, " Welcome to LogFile  ");

		Company company1 = new Company(1, "Miccccc", "121212", "Avi@gmail.com");
		Company company2 = new Company(2, "Amdocs", "343434", "Moshe@gmail.com");
		Company company3 = new Company(3, "CocaPepes", "565656", "Dror@gmail.com");
		Company company4 = new Company(4, "HSBC", "787878", "Numi@gmail.com");

		Customer customer1 = new Customer(1, "Evi", "345345");
		Customer customer2 = new Customer(2, "Shay", "567567");
		Customer customer3 = new Customer(3, "Moahe", "345345");
		Customer customer4 = new Customer(4, "kobi", "12123334");

		Coupon coupon1 = new Coupon(1, "Omer", Utils.getDate(), Utils.endDate(60), 1111, CouponType.HEALTH, "wtf", 1251,
				"image");
		Coupon coupon2 = new Coupon(2, "Eli", Utils.getDate(), Utils.endDate(90), 2222, CouponType.HEALTH, "wtf11",
				1122, "imageAndShare");
		Coupon coupon3 = new Coupon(3, "Amir", Utils.getDate(), Utils.endDate(120), 3333, CouponType.HEALTH, "wtf1122",
				3232, "imageAndShareAndLove");
		Coupon coupon4 = new Coupon(4, "Yuval", Utils.getDate(), Utils.endDate(12), 1232, CouponType.HEALTH, "wtf7878",
				7788, "imageAndShareAndLove");

		/****************************************************************************************************/

		/********* Admin **************/
		AdminFacade adminFacade2 = new AdminFacade();
		adminFacade2 = (AdminFacade) CouponSystem.getCouponSystem().login("admin", "12346", clientType.Admin);
		if (adminFacade2 != null) {

			adminFacade2.getAllCustomers();
			adminFacade2.getAllCompanies();

		}

		// adminFacade2.createCompany(company2);
		// adminFacade2.createCompany(company3);
		// adminFacade2.createCompany(company4);

		// adminFacade2.createCustomer(customer1);
		// adminFacade2.createCustomer(customer2);
		// adminFacade2.createCustomer(customer3);
		// adminFacade2.createCustomer(customer4);

		// adminFacade2.createCustomer(customer1);
		// adminFacade2.getAllCompanies();
		// adminFacade2.getAllCustomers();

		/********* Company **************/
		CompanyFacade companyfacade = new CompanyFacade();
		companyfacade.login("Miccccc", "121212", clientType.Company);
		companyfacade = (CompanyFacade) CouponSystem.getCouponSystem().login("Miccccc", "121212", clientType.Company);
		System.out.println(companyfacade);

		if (companyfacade != null) {

			companyfacade.getAllCoupons();

		}

		// companyfacade.createCoupon(coupon2);
		// companyfacade.createCoupon(coupon3);
		// companyfacade.createCoupon(coupon4);

		// }
		// companyfacade.getAllCoupons();
		// companyfacade.getCompany("Checkpoint");
		// companyfacade.getCouponsByExpiredDate(Utils.getDate());

		// }

		/********* Customer **************/

		// CustomerFacade CustomerFacade = new CustomerFacade();
		// CustomerFacade.login("Evi", "345345", clientType.Customer);
		// CouponSystem.getCouponSystem().login("Shay", "567567", clientType.Customer);
		// if (CustomerFacade != null) {
		// CustomerFacade.getAllPurchasedCoupons();
		// System.out.println(coupon3);

		// CustomerFacade.purchaseCoupon(coupon4, "Evi");
		// CustomerFacade.getAllPurchasedCoupons("Evi");
		// CustomerFacade.getAllPurchasedCouponsByType("Evi", CouponType.HEALTH);
		// }

		/***********************************************/
		// adminFacade2.getCompanybyPW("55662322");

		// }

		// customerDBDAO.purchaseCoupon(coupon1, customer1);
		// customerDBDAO.getCustomer1("898989");

		// customerDBDAO.createCustomer(customer2);
		// customerDBDAO.getCustomer("Evi");

	}
}
