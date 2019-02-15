package DBDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import Java.DB.DAO.CustomerDAO;
import Java.JavaBean.Coupon;
import Java.JavaBean.Customer;
import Java.Main.Utils;

/**
 * @Author
 */

public class CustomerDBDAO implements CustomerDAO {

	// Attributes

	private Connection conn;

	// Methods that DBDAO Must use from DAO

	public void insertCustomer(Customer customer) throws Exception {

		conn = DriverManager.getConnection(Utils.getDBUrl());
		String query = "INSERT INTO CUSTOMER (CUST_NAME,PASSWORD) VALUES (?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customer.getCustomerName());
			pstmt.setString(2, customer.getPassword());
			pstmt.executeUpdate();
			System.out.println("Customer " + customer.getCustomerName() + " inserted successfully");
		} catch (SQLException e) {
			throw new Exception("Customer creation faild");
		} finally {
			conn.close();
		}
	}

	public void removeCustomer(Customer customer) throws Exception {
		conn = DriverManager.getConnection(Utils.getDBUrl());
		try {
			String query = "DELETE FROM Customer_Coupon WHERE COUPON_ID=?;"
					+ "DELETE FROM Company_Coupon WHERE COUPON_ID=?;" + "DELETE FROM Coupon WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, customer.getId());
			pstmt.setLong(2, customer.getId());
			pstmt.setLong(3, customer.getId());
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Customer was removed from all 3 tables.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.close();
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Coupon getCustomer(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Coupon> getAllCustomers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Java.DB.DAO.Coupon getCustomer(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCustomer(Java.DB.DAO.Customer customer) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCustomer(Java.DB.DAO.Customer customer) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCustomer(Java.DB.DAO.Customer customer) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Java.DB.DAO.Coupon getCustomer(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}