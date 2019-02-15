package Facade;

import Java.DB.DAO.CouponDAO;
import Java.DB.DAO.CustomerDAO;
import Java.JavaBean.ClientType;
import Java.JavaBean.Customer;

public class CustomerFacade implements CouponClientFacade {

	private CustomerDAO customerDao;
	private CouponDAO couponDao;
	private Customer customer;

	/**
	 * @EmptyCtor
	 */
	public CustomerFacade() {
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CouponClientFacade login(String name, String password, Facade.ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CouponClientFacade login(String name, String password, Facade.ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

}
