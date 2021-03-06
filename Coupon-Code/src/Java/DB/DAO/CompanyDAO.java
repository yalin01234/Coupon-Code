package Java.DB.DAO;

import java.util.Set;

import Java.JavaBean.Company;

public interface CompanyDAO {

	void createCompany(Company company) throws Exception;

	void removeCompany(Company company) throws Exception;

	void updateCompany(Company company) throws Exception;

	Company getCompany(long id) throws Exception;

	Company getCompanybyPW(String password) throws Exception;

	Set<Company> getAllCompanies() throws Exception;

}