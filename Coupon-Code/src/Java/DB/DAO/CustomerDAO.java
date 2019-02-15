package Java.DB.DAO;

import java.util.Set;

import Java.JavaBean.Coupon;
import Java.JavaBean.Customer;

/**
 * @Author -
 */

public interface CustomerDAO {

	void insertCustomer(Customer customer) throws Exception;

	void removeCustomer(Customer customer) throws Exception;

	void updateCustomer(Customer customer) throws Exception;

	Coupon getCustomer(long id) throws Exception;

	Set<Coupon> getAllCustomers() throws Exception;

}