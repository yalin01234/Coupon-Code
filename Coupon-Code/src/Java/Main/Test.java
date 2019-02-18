package Java.Main;

import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import Facade.CompanyFacade;
import Java.JavaBean.Company;
import Java.JavaBean.Coupon;
import Java.JavaBean.CouponType;
import Java.JavaBean.Customer;

public class Test {

	public static void main(String[] args) throws Exception {

		// Database.getDatabase();

		Company company1 = new Company(1, "Fedrik", "12345", "moshe@fedrkik.com");
		Company company2 = new Company(2, "The Eucalyptus", "123456", "yaakov@theeucalyptus.com");
		Company company3 = new Company(3, "Checkpoint", "12345678", "yalinarie@checkpoint.com");

		Coupon coupon1 = new Coupon(100, "Fedrik 50% less", Utils.getCurrentDate(), Utils.getExpiredDate(), 500,
				CouponType.RESTURANTS, "first coupon", 5632, "image.png", 1);
		Coupon coupon2 = new Coupon(200, "Fedrik 50% less", Utils.getCurrentDate(), Utils.getExpiredDate(), 500,
				CouponType.RESTURANTS, "second coupon", 32141, "image.png", 1);
		Coupon coupon3 = new Coupon(300, "The Eucalyptus 20% less", Utils.getCurrentDate(), Utils.getExpiredDate(), 500,
				CouponType.RESTURANTS, "second coupon", 64536, "image.png", 2);

		Customer customer = new Customer(1, "Moshe", "123456");
		Customer customer1 = new Customer(2, "Ruben", "1234567");
		Customer customer2 = new Customer(3, "David", "12345678");

		CouponDBDAO couponDBDAO = new CouponDBDAO();
		CompanyFacade cf = new CompanyFacade();
		CompanyDBDAO companyDBDAO = new CompanyDBDAO();
		companyDBDAO.createCompany(company1);
		companyDBDAO.createCompany(company2);
		companyDBDAO.createCompany(company3);

		// AdminFacade adminfacade = new AdminFacade();
		// adminfacade.createCompany(company1);
		// adminfacade.createCompany(company2);
		// adminfacade.createCompany(company3);

		// System.out.println(coupon1.getType());
		// cf.createCompany(company1);
		// cf.createCompany(company2);
		// cf.createCompany(comapny3);

		// Sysstem.out.println(Utils.getCurrentDate());
		// System.out.println( Utils.getExpiredDate());
		// cff.createCompany(company1);
		// couponDBDAO.createCoupon(coupon1);
		// couponDBDAO.createCoupon(coupon2);
		// couponDBDAO.createCoupon(coupon3);

		// cf.createCompany(c1);

	}

}
