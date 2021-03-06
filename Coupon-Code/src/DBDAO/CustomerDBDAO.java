package DBDAO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DB.ConnPool;
import Java.DB.DAO.CustomerDAO;
import Java.JavaBean.Coupon;
import Java.JavaBean.CouponType;
import Java.JavaBean.Customer;
import Java.Main.MyLogger;

public class CustomerDBDAO implements CustomerDAO {

	/**
	 * 
	 * This class implements basic methods between the application level to the DB
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	/*****************************************
	 * 
	 * Attributes
	 * 
	 ********************************************/

	Connection conn;

	/********************************************
	 * 
	 * CTOR
	 * 
	 ***********************************************/

	public CustomerDBDAO() {

		// TODO Auto-generated constructor stub

	}

	/*******************************************
	 * 
	 * Methods
	 * 
	 *********************************************/

	public void createCustomer(Customer customer) throws Exception {

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// Define the Execute query

		String sql = "INSERT INTO CUSTOMER (CUST_NAME,PASSWORD) VALUES (?,?)";

		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, customer.getCustomerName());

			pstmt.setString(2, customer.getPassword());

			pstmt.executeUpdate();
			// Logger logger = new Logger("Sucess to create Customer");
			// logger.info("info msg");
			// FileLog.doLogging();
			MyLogger.logToFile(Level.SEVERE, "Create Customer was sucusssed ");

		} catch (SQLException e) {

			throw new Exception("Customer creation faild");

		} finally {

			// finally block used to close resources

			try {

				if (pstmt != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);

				}
				MyLogger.logToFile(Level.SEVERE, "Close DB for creation customer in DB ");
			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

		// System.out.println("Customer " + customer.getCustomerName() + " inserted
		// successfully");
		MyLogger.logToFile(Level.SEVERE, "Close DB for creation customer in DB ");
	}

	@Override

	public void removeCustomer(Customer customer) throws Exception {

		// retrieve the PK by the customer name

		Customer customerLocaly = new Customer();

		customerLocaly = getCustomer(customer.getCustomerName());

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// Create the SQL query

		String sql = "DELETE FROM CUSTOMER WHERE id=?";

		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(sql);

			conn.setAutoCommit(false);// turn off auto-commit

			pstmt.setLong(1, customerLocaly.getId()); // Sets the designated parameter to the given Java long value

			pstmt.executeUpdate();
			MyLogger.logToFile(Level.SEVERE, "Remove Customer Done");

			conn.commit();// Commit the changes,If there is no error.

		} catch (SQLException e) {

			try {

				conn.rollback();// roll back updates to the database , If there is error

			} catch (SQLException e1) {

				throw new Exception("The Rollback connection failed");

			}

		} finally {

			// finally block used to close resources

			try {

				if (pstmt != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

		JFrame frame = new JFrame("JOptionPane showMessageDialog example");

		JOptionPane.showMessageDialog(frame, "Removed customer " + customer.getCustomerName() + " successfully");

	}

	public void removeCustomerCoupons(Customer customer) throws Exception {

		Set<Coupon> allCoupons = new HashSet<Coupon>();

		allCoupons = getCustomerCoupons(customer); // Fetch Customer Coupons from getCustomerCoupon Method according to
													// Customer

		long id;

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// Create the SQL query

		String sql = "DELETE FROM CUSTOMER_COUPON WHERE COUPON_ID=?";

		PreparedStatement pstmt = null;

		try {

			// Remove all the company coupons from the join table

			java.util.Iterator<Coupon> itr = allCoupons.iterator();

			while (itr.hasNext()) {

				pstmt = conn.prepareStatement(sql);

				conn.setAutoCommit(false);// turn off auto-commit

				id = itr.next().getId();

				pstmt.setLong(1, id);

				System.out.println(id);

				pstmt.executeUpdate();
				MyLogger.logToFile(Level.SEVERE, "Remove Customer Coupon Done");

				conn.commit();

			}

		}

		catch (SQLException e) {

			try {

				conn.rollback();// roll back updates to the database , If there is error

			} catch (SQLException e1) {

				throw new Exception(e1.getMessage());

			}

		} finally {

			// finally block used to close resources

			try {

				if (pstmt != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

	}

	@Override

	public void updateCustomer(Customer customer) throws Exception {

		// retrieve the customer details from DB

		Customer customerLocaly = new Customer();

		customerLocaly = getCustomer(customer.getCustomerName()); // Fetch

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// create the Execute query

		PreparedStatement pstms = null;

		String sqlString = "UPDATE CUSTOMER SET  PASSWORD = ? WHERE ID = ? ";

		try {

			// create PreparedStatement and build the SQL query

			pstms = conn.prepareStatement(sqlString);

			pstms.setString(1, customer.getPassword());

			pstms.setLong(2, customerLocaly.getId());

			pstms.executeUpdate();
			MyLogger.logToFile(Level.SEVERE, "Update Customer Done");

		} catch (SQLException e) {

			throw new Exception("update customer failed");

		} finally {

			// finally block used to close resources

			try {

				if (pstms != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

		JFrame frame = new JFrame("JOptionPane showMessageDialog example");

		JOptionPane.showMessageDialog(frame, "Update customer " + customer.getCustomerName() + " successfully");

	}

	@Override
	public Customer getCustomer(long id) throws Exception {

		Customer customer = new Customer();

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		PreparedStatement pstms = null;
		// java.sql.Statement stmt = null;

		try {
			String sql = "SELECT * FROM CUSTOMER WHERE ID= ?";

			pstms = conn.prepareStatement(sql);
			pstms.setLong(1, customer.getId());

			// build The SQL query

			// String sql = "SELECT * FROM CUSTOMER WHERE ID=" + id;

			// Set the results from the database

			ResultSet resultSet = pstms.executeQuery();

			// constructor the object, retrieve the attributes from the results

			resultSet.next();

			customer.setId(resultSet.getLong(1));

			customer.setCustomerName(resultSet.getString(2));

			customer.setPassword(resultSet.getString(3));

			System.out.println("Result is " + resultSet.getLong(1) + resultSet.getString(2) + resultSet.getString(3));
			MyLogger.logToFile(Level.SEVERE, "Update Customer Done");
			// TODO - Add the coupons list from the ArrayCollection

		} catch (SQLException e) {

			throw new Exception("Getting customer failed");

		} finally {

			// finally block used to close resources

			try {

				if (pstms != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);
				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

		return customer;

	}

	public Set<Coupon> getCustomerCoupons(Customer customer) throws Exception {

		Set<Coupon> coupons = new HashSet<Coupon>();

		ArrayList<Long> couponIDs = new ArrayList<Long>();

		// Retrieve the PK of the customer by name

		Customer customerLocaly = new Customer();

		customerLocaly = getCustomer(customer.getCustomerName());// Approach to getCustomer with customer name

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// Define the Execute query

		PreparedStatement pstms = null;
		PreparedStatement pstms1 = null;
		// java.sql.Statement stmt = null;

		// java.sql.Statement stmt1 = null;

		try {
			String sql = "SELECT * FROM COUPON";

			String sql1 = "SELECT * FROM CUSTOMER_COUPON";

			pstms = conn.prepareStatement(sql);

			pstms1 = conn.prepareStatement(sql1);

			// build The SQL query

			// Set the results from the database,

			ResultSet resultSet = pstms.executeQuery(); // Fetch all Coupon

			ResultSet resultSet2 = pstms1.executeQuery();// Fetch all Customer_Coupon

			// constructor the object, retrieve the attributes from the results

			while (resultSet2.next()) { // Fetch all the Coupon ID from Customer Coupon Table

				if (resultSet2.getLong(1) == customerLocaly.getId()) {

					couponIDs.add(resultSet2.getLong(2));
					System.out.println("testCoupon" + couponIDs);

				}

			}

			while (resultSet.next()) { // Fetch all Coupon from Coupon table

				if (couponIDs.contains(resultSet.getLong(1))) {

					Coupon coupon = new Coupon(); // We have to set-up a new Coupon object in order to import the values
													// from DB.

					coupon.setId(resultSet.getLong(1));

					coupon.setTitle(resultSet.getString(2));

					coupon.setStartDate(resultSet.getDate(3));

					coupon.setEndDate(resultSet.getDate(4));

					coupon.setAmount(resultSet.getInt(5));

					CouponType type = CouponType.valueOf(resultSet.getString(6)); // Convert String to Enum

					coupon.setType(type);

					coupon.setMessage(resultSet.getString(7));

					coupon.setPrice(resultSet.getDouble(8));

					coupon.setImage(resultSet.getString(9));

					coupons.add(coupon);
					System.out.println(coupons);

				}

			}

		} catch (SQLException e) {

			throw new Exception("Retriev all the coupons failed");

		} finally {

			// finally block used to close resources

			try {

				if (pstms != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

		return coupons;

	}

	public Set<Coupon> getCoupons() throws Exception { // Fetch all Coupons for SET collection

		Set<Coupon> coupons = new HashSet<Coupon>();

		// Open a connection

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		java.sql.Statement stmt = null;

		try {

			stmt = conn.createStatement();

			// build The SQL query

			String sql = "SELECT * FROM COUPON";

			// Set the results from the database

			ResultSet resultSet = stmt.executeQuery(sql);

			// constructor the object, retrieve the attributes from the results

			while (resultSet.next()) {

				Coupon coupon = new Coupon();

				coupon.setId(resultSet.getLong(1));

				coupon.setTitle(resultSet.getString(2));

				coupon.setStartDate(resultSet.getDate(3));

				coupon.setEndDate(resultSet.getDate(4));

				coupon.setAmount(resultSet.getInt(5));

				CouponType type = CouponType.valueOf(resultSet.getString(6)); // Convert String to Enum

				coupon.setType(type);

				coupon.setMessage(resultSet.getString(7));

				coupon.setPrice(resultSet.getDouble(8));

				coupon.setImage(resultSet.getString(9));

				coupons.add(coupon);

				System.out.println(coupons);
				MyLogger.logToFile(Level.SEVERE, "Getting  Coupon Done");

			}

		} catch (SQLException e) {

			throw new Exception("Retriev all the coupons failed");

		} finally {

			// finally block used to close resources

			try {

				if (stmt != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

		return coupons;

	}

	public Boolean login(String custName, String password) throws Exception {

		// TODO Auto-generated method stub

		return null;

	}

	public Set<Customer> getAllCustomer() throws Exception {

		Set<Customer> customers = new HashSet<Customer>();

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		java.sql.Statement stmt = null;

		try {

			stmt = conn.createStatement();

			// build The SQL query

			String sql = "SELECT * FROM CUSTOMER";

			// Set the results from the database

			ResultSet resultSet = stmt.executeQuery(sql);

			// constructor the object, retrieve the attributes from the results

			while (resultSet.next()) {

				Customer customer = new Customer();

				customer.setId(resultSet.getLong(1));

				customer.setCustomerName(resultSet.getString(2));

				customer.setPassword(resultSet.getString(3));

				customers.add(customer);
				System.out.println(customers);

				String PathFile = "C:\\\\\\\\Directory2\\\\\\\\Sub2\\\\\\\\Sub-Sub2\\\\\\\\DBBackup.txt";
				FileWriter fileWriter = new FileWriter(PathFile, true);
				fileWriter.write((int) customer.getId());
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write((int) customer.getId());
				bufferedWriter.newLine();

				FileWriter fileWriter1 = new FileWriter(PathFile, true);
				fileWriter1.write(customer.getCustomerName());
				BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
				bufferedWriter1.write(customer.getCustomerName());
				bufferedWriter1.newLine();

				FileWriter fileWriter2 = new FileWriter(PathFile, true);
				fileWriter1.write(customer.getPassword());
				BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
				bufferedWriter2.write(customer.getPassword());
				bufferedWriter2.newLine();
				bufferedWriter.close();
				bufferedWriter1.close();
				bufferedWriter2.close();
				MyLogger.logToFile(Level.SEVERE, "Getting  All Customer Done");

			}

		} catch (

		SQLException e) {

			throw new Exception("Retriev all the coupons failed");

		} finally {

			// finally block used to close resources

			try {

				if (stmt != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

		return customers;

	}

	public void printAllCustmers() throws Exception {

		Customer customer = new Customer();

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		java.sql.Statement stmt = null;

		try {

			stmt = conn.createStatement();

			// build The SQL query

			String sql = "SELECT * FROM CUSTOMER";

			// Set the results from the database

			ResultSet resultSet = stmt.executeQuery(sql);

			// constructor the object, retrieve the attributes from the results

			while (resultSet.next()) {

				customer.setId(resultSet.getLong(1));

				customer.setCustomerName(resultSet.getString(2));

				customer.setPassword(resultSet.getString(3));

				System.out.println(customer);

			}

		} catch (SQLException e) {

			throw new Exception("Retriev all the coupons failed");

		} finally {

			// finally block used to close resources

			try {

				if (stmt != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

	}

	public Customer getCustomer(String CUST_NAME) throws Exception { // This method is returning only one customer

		Customer customer = new Customer();
		try {
			conn = ConnPool.getInstance().getConnection();
		} catch (Exception e) {
			new Exception("The Connection is Faild ");
		}
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM CUSTOMER";
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				if (resultSet.getString(2).equals(CUST_NAME)) {
					customer.setId(resultSet.getLong(1));
					customer.setCustomerName(resultSet.getString(2));
					customer.setPassword(resultSet.getString(3));
					System.out.println(
							"Result is " + resultSet.getLong(1) + resultSet.getString(2) + resultSet.getString(3));

					break;

				}

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

			throw new Exception("get customer failed");

		} finally {

			// finally block used to close resources

			try {

				if (stmt != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

		return customer;

	}

	public Customer getCustomer1(String PASSWORD) throws Exception {
		Customer customer = new Customer();

		try {
			conn = ConnPool.getInstance().getConnection();
		} catch (Exception e) {
			new Exception("The Connection is Faild ");
		}
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM CUSTOMER";
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				if (resultSet.getString(3).equals(PASSWORD)) {
					customer.setId(resultSet.getLong(1));
					customer.setCustomerName(resultSet.getString(2));
					customer.setPassword(resultSet.getString(3));
					// System.out.println("test99");
					System.out.println(
							"Result is " + resultSet.getLong(1) + resultSet.getString(2) + resultSet.getString(3));

					break;
				}
				MyLogger.logToFile(Level.SEVERE, "Get Customer Done");
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

			throw new Exception("get customer failed");

		} finally {

			// finally block used to close resources

			try {

				if (stmt != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

		return customer;

	}

	public void purchaseCoupon(Coupon coupon, Customer customer) throws Exception {
		// System.out.println(customer);
		long idPK = 0;

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		String sql1 = "SELECT * FROM COUPON";

		String sql2 = "INSERT INTO CUSTOMER_COUPON(CUST_ID,COUPON_ID) VALUES(?,?)";

		// Set the results from the database

		PreparedStatement pstmt = null;

		java.sql.Statement stmt = null;

		try {

			// Insert the new coupon to join table COMPANY_COUPON

			stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery(sql1);

			while (resultSet.next()) {

				if (coupon.getTitle().equals(resultSet.getString(2))) {

					// System.out.println(resultSet.getLong(1));
					idPK = resultSet.getLong(1);
					// System.out.println(idPK);

				}

			}

			pstmt = conn.prepareStatement(sql2);
			// pstmt.setLong(1, 1);
			pstmt.setLong(1, customer.getId());

			pstmt.setLong(2, idPK);
			pstmt.executeUpdate();
			// System.out.println("idpk" + idPK);

		} catch (SQLException e) {

			throw new Exception("Purchased Coupon failed");

		} finally {

			// finally block used to close resources

			try {

				if (pstmt != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);

				}

			} catch (Exception e) {

				throw new Exception("The close connection action faild");

			}

		}

		JFrame frame = new JFrame("JOptionPane showMessageDialog example");

		JOptionPane.showMessageDialog(frame, "Inserted coupon " + coupon.getTitle() + " successfully");

	}

	@Override
	public void insertCustomer(Customer customer) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Coupon> getAllCustomers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}