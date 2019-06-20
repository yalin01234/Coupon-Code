package Java.Main;

import java.util.logging.Level;

import DB.Database;
import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
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
		Company company5 = new Company(4, "IHG", "121212", "Migdal@gmail.com");
		Company company6 = new Company(4, "MaromGolan", "444555", "Arie@gmail.com");
		Company company7 = new Company(4, "Alma", "887776", "Numi333@gmail.com");
		Company company8 = new Company(4, "HSBC", "345345", "Numi111@gmail.com");
		Company company9 = new Company(4, "HSBC", "3433434", "Numi22@gmail.com");

		Customer customer1 = new Customer(1, "Evi", "345345");
		Customer customer2 = new Customer(2, "Shay", "567567");
		Customer customer3 = new Customer(3, "Moahe", "345345");
		Customer customer4 = new Customer(4, "kobi", "12123334");
		Customer customer5 = new Customer(5, "Roi", "2323232");
		Customer customer6 = new Customer(6, "Meitaly", "4546555");
		Customer customer7 = new Customer(7, "Jango", "566666565");

		Coupon coupon1 = new Coupon(1, "Omer", Utils.getDate(), Utils.endDate(60), 1111, CouponType.HEALTH, "wtf", 1251,
				"image");
		Coupon coupon2 = new Coupon(2, "Eli", Utils.getDate(), Utils.endDate(90), 2222, CouponType.HEALTH, "wtf11",
				1122, "imageAndShare");
		Coupon coupon3 = new Coupon(3, "Amir", Utils.getDate(), Utils.endDate(120), 3333, CouponType.HEALTH, "wtf1122",
				3232, "imageAndShareAndLove");
		Coupon coupon4 = new Coupon(4, "Yuval", Utils.getDate(), Utils.endDate(10), 4444, CouponType.HEALTH, "wtf2323",
				9996, "imageAndShareAndLove");
		Coupon coupon5 = new Coupon(5, "Reut", Utils.getDate(), Utils.endDate(1), 5555, CouponType.HEALTH, "wtf3434",
				9997, "imageAndShareAndLove");
		Coupon coupon6 = new Coupon(6, "Roi", Utils.getDate(), Utils.endDate(33), 6666, CouponType.HEALTH, "wtf56565",
				9998, "imageAndShareAndLove");
		Coupon coupon7 = new Coupon(7, "Nirira ", Utils.getDate(), Utils.endDate(40), 7777, CouponType.HEALTH,
				"wtf8888", 9999, "imageAndShareAndLove");

		/****************************************************************************************************/

		/********* Admin **************/
		AdminFacade adminFacade2 = new AdminFacade();
		adminFacade2 = (AdminFacade) CouponSystem.getCouponSystem().login("admin", "12346", clientType.Admin);
		if (adminFacade2 != null) {

			adminFacade2.createCompany(company8);
			adminFacade2.createCustomer(customer5);
			adminFacade2.getAllCompanies();
			adminFacade2.getAllCustomers();
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
			companyfacade.createCoupon(coupon6);
			companyfacade.createCoupon(coupon7);
			companyfacade.createCoupon(coupon5);
			companyfacade.getAllCoupons();
			companyfacade.getCompanyCoupons(company2);
			companyfacade.getCoupon(1);

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

		CustomerFacade CustomerFacade = new CustomerFacade();
		CustomerFacade.login("Evi", "345345", clientType.Customer);
		// CouponSystem.getCouponSystem().login("Shay", "567567", clientType.Customer);
		if (CustomerFacade != null) {
			CustomerFacade.getAllPurchasedCoupons("Evi");

		}
		// System.out.println(coupon3);

		CustomerFacade.purchaseCoupon(coupon4, "Evi");
		CustomerFacade.purchaseCoupon(coupon5, "Evi");
		CustomerFacade.getAllPurchasedCoupons("Evi");

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
