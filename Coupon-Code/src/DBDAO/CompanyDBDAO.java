package DBDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import javax.swing.JPanel;

import Java.Exceptions.RemoveCompanyException;
import Java.DB.DAO.CompanyDAO;
import Java.JavaBean.Company;
import Java.JavaBean.Coupon;
import Java.Main.Utils;

/**

*/

public class CompanyDBDAO implements CompanyDAO {

	// Attributes

	private Connection conn;
	private final JPanel panel = new JPanel();

	// Methods that DBDAO Must use from DAO

	@Override
	public void createCompany(Company company) throws Exception {

		conn = DriverManager.getConnection(Utils.getDBUrl());
		String query = "INSERT INTO Companies (COMP_NAME,PASSWORD,EMAIL)  VALUES(?,?,?)";
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());
			pstmt.executeUpdate();
			System.out.println("Company " + company.getCompName() + " inserted successfully");
		} catch (SQLException e) {
			throw new Exception("Company creation failed");
		} finally {
			conn.close();
		}
	}

	@Override

	// Remove method by ID
	public void removeCompany(Company company) throws RemoveCompanyException, Exception {
		// TODO Auto-generated method stub
		conn = DriverManager.getConnection(Utils.getDBUrl());
		String sql = "DELETE FROM companies WHERE id=?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			conn.setAutoCommit(false);
			pstmt.setLong(1, company.getId()); // Sets the designated parameter to the given Java long value
			pstmt.executeUpdate();
			conn.commit();
			System.out.println(company.getCompName() + " successfully Removed from the DB");
		} catch (SQLException e) {
			throw new RemoveCompanyException(company);
		}
		try {
			conn.rollback();
		} catch (SQLException e1) {
			throw new Exception(e1.getMessage());
		} finally {
			conn.close();
		}

	}

	@Override
	public void updateCompany(Company company) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Coupon getCompany(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Coupon> getAllCompanies() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createCompany(Company company) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCompany(Company company) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCompany(Company company) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Coupon getCompany(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}