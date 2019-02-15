package Java.DB.DAO;

import java.util.Set;

import Java.JavaBean.Company;
import Java.JavaBean.Coupon;

/**
 * @Author -
 */

public interface CompanyDAO {

	void createCompany(Company company) throws Exception;

	void removeCompany(Company company) throws Exception;

	void updateCompany(Company company) throws Exception;

	Coupon getCompany(long id) throws Exception;

	Set<Coupon> getAllCompanies() throws Exception;

}