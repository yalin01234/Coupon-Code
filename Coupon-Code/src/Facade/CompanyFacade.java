package Facade;

import java.util.Set;

import DBDAO.CompanyDBDAO;
import Java.JavaBean.ClientType;
import Java.JavaBean.Company;
import Java.JavaBean.Coupon;

/**
 * @Author - Oriel
 */

public class CompanyFacade implements CouponClientFacade {

	private CompanyDBDAO compDAO = new CompanyDBDAO();
	private Company company;

	public CompanyFacade(Company c) {
		this.company = c;
	}

	public CompanyFacade() {
	}

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeCompany(Company company) throws Exception {
		compDAO.removeCompany(company);
	}

	public void updateCompany(Company company, String newName, String newPassword, String newEmail) throws Exception {
		company.setCompName(newName);
		company.setPassword(newPassword);
		company.setEmail(newEmail);
		compDAO.updateCompany(company);
	}

	public Company getCompany() {
		return company;
	}

	public Set<Coupon> getAllCompanies() throws Exception {

		return compDAO.getAllCompanies();
	}

}