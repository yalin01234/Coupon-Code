package Facade;

import Java.DB.DAO.CompanyDAO;
import Java.DB.DAO.CouponDAO;
import Java.DB.DAO.CustomerDAO;
import Java.JavaBean.ClientType;
import Java.JavaBean.Company;
import Java.JavaBean.Customer;

/**
 * @Author - Oriel
 */

public class AdminFacade implements CouponClientFacade {

	private CompanyDAO companyDAO;
	private CustomerDAO customerDao;
	private CouponDAO couponDao;
	private Customer customer;

	// Ctors
	// Empty Ctor

	public AdminFacade() {
	}

	// Ctor of Admin Facade refers to all DAO's (For all admin operations)
	public AdminFacade(CustomerDAO customerDao, CompanyDAO companyDao, CouponDAO couponDao) {
		this.customerDao = customerDao;
		this.companyDAO = companyDao;
		this.couponDao = couponDao;
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createCompany(Company company) throws Exception {
		companyDAO.createCompany(company);
	}

	// public void removeCompany(Company company) throws Exception

}
