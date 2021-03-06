package DBDAO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DB.ConnPool;
import Java.DB.DAO.CompanyDAO;
import Java.JavaBean.Company;
import Java.JavaBean.Coupon;
import Java.JavaBean.CouponType;
import Java.Main.MyLogger;

public class CompanyDBDAO implements CompanyDAO {

	/**
	 * 
	 * This class is implemented basic methods between the application level to the
	 * DB in particular the connection to DB
	 * 
	 * 
	 * /*************************************** Attributes
	 *******************************************/

	private Connection conn;

	private Company company;

	CouponDBDAO couponDBDAO = new CouponDBDAO();

	/*******************************************
	 * CTOR
	 *********************************************/

	public CompanyDBDAO() {

		// TODO Auto-generated constructor stub

	}

	/******************************************
	 * Methods
	 *******************************************/

	@Override

	public void createCompany(Company company) throws Exception {

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// Define the Execute query

		String sql = "INSERT INTO COMPANY (COMP_NAME,PASSWORD,EMAIL)  VALUES(?,?,?)";

		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, company.getCompName());

			pstmt.setString(2, company.getPassword());

			pstmt.setString(3, company.getEmail());

			pstmt.executeUpdate();
			MyLogger.logToFile(Level.SEVERE, "Close DB for creation company in DB ");

		} catch (Exception e) {

			e.printStackTrace();
			e.getMessage();

			MyLogger.logToFile(Level.WARNING, e.getMessage());

		} finally {

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
		MyLogger.logToFile(Level.SEVERE, "Creating Customer was sucsssed ");
	}

	@Override
	public void removeCompany(Company company) throws Exception {

		// retrieve the PK by the company name

		Company companyLocal = new Company();

		try {

			conn = ConnPool.getInstance().getConnection();
		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// Define the Execute query

		String sql = "DELETE FROM COMPANY WHERE id=?";

		PreparedStatement pstmt = null;

		// System.out.println(companyLocal);

		try {

			pstmt = conn.prepareStatement(sql);

			conn.setAutoCommit(false);// turn off auto-commit

			pstmt.setLong(1, companyLocal.getId()); // Sets the designated parameter to the given Java long value

			pstmt.executeUpdate();

			MyLogger.logToFile(Level.SEVERE, "Remove Company Done");

			conn.commit();// Commit the changes,If there is no error.

		} catch (SQLException e) {

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

		JFrame frame = new JFrame("JOptionPane showMessageDialog example");

		JOptionPane.showMessageDialog(frame, "Removed company " + company.getCompName() + " successfully");

	}

	public void removeCompanyCoupons(Company company) throws Exception { // This method is remove company coupon from
																			// join tables

		Set<Coupon> allCoupons = new HashSet<Coupon>();

		allCoupons = getCompanyCoupons(company);

		long id;

		// Open a connection

		try {

			conn = ConnPool.getInstance().getConnection();
		} catch (SQLException e2) {

			throw new Exception("The Connection is faild");

		}

		String sql = "DELETE FROM COMPANY_COUPON WHERE COUPON_ID=?";

		PreparedStatement pstmt = null;

		try {

			// Remove all the company coupons from the join table

			Iterator<Coupon> itr = allCoupons.iterator();

			while (itr.hasNext()) {

				pstmt = conn.prepareStatement(sql);

				conn.setAutoCommit(false);// turn off auto-commit

				id = itr.next().getId();

				pstmt.setLong(1, id);

				pstmt.executeUpdate();
				MyLogger.logToFile(Level.SEVERE, "Remove Couoin Company Done");

				conn.commit();

			}

			// Remove all the company coupons from coupon table

			Iterator<Coupon> itr2 = allCoupons.iterator();

			while (itr2.hasNext()) {

				couponDBDAO.removeCoupon(itr2.next());

			}

		} catch (SQLException e) {

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

	public void updateCompany(Company company) throws Exception {

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// create the Execute query String

		PreparedStatement pstms = null;

		String sqlString = "UPDATE COMPANY SET PASSWORD = ?, EMAIL = ?  WHERE ID = ? ";

		try {

			// create PreparedStatement and build the SQL query

			pstms = conn.prepareStatement(sqlString);

			pstms.setString(1, company.getPassword());

			pstms.setString(2, company.getEmail());

			pstms.setLong(3, company.getId());

			pstms.executeUpdate();
			MyLogger.logToFile(Level.SEVERE, "Update  Company Done");

			System.out.println(company);

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

		JOptionPane.showMessageDialog(frame, "Updated company " + company.getCompName() + " successfully");

	}

	@Override
	public Company getCompanybyPW(String password) throws Exception {
		Company company = new Company();
		try {
			conn = ConnPool.getInstance().getConnection();
		} catch (Exception e) {
			throw new Exception("The Connection is faild");
		}

		PreparedStatement pstms = null;
		try {

			String sql1 = "SELECT * FROM COMPANY WHERE PASSWORD= ?"; // Fetch from DB according to PW

			pstms = conn.prepareStatement(sql1);
			pstms.setString(1, company.getPassword());
			ResultSet resultSet = pstms.executeQuery();

			MyLogger.logToFile(Level.SEVERE, "Getting Company by PW Done");

			resultSet.next();

			company.setId(resultSet.getLong(1));

			company.setCompName(resultSet.getString(2));

			company.setPassword(resultSet.getString(3));

			company.setEmail(resultSet.getString(4));

			// TODO - Add the coupons list from the ArrayCollection

		} catch (SQLException e1) {

			throw new Exception("get company failed with Password=" + password);

		} finally {

			// finally block used to close resources

			try {

				if (pstms != null) {
					ConnPool.getInstance().returnConnection(conn);
				}

			} catch (Exception e1) {

				throw new Exception("The close connection action faild");

			}

			try {

				if (conn != null) {
					ConnPool.getInstance().returnConnection(conn);
				}

			} catch (Exception e2) {

				throw new Exception("The close connection action faild");

			}
		}
		return company;

	}

	@Override

	public Company getCompany(long id) throws Exception {

		Company company = new Company();

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// Define the Execute query

		PreparedStatement pstms = null;

		try {

			String sql1 = "SELECT * FROM COMPANY WHERE ID= ?";

			pstms = conn.prepareStatement(sql1);

			pstms.setLong(1, company.getId());

			// Set the results from the database

			ResultSet resultSet = pstms.executeQuery();
			MyLogger.logToFile(Level.SEVERE, "Getting  Company Done");

			// constructor the object, retrieve the attributes from the results

			resultSet.next();

			company.setId(resultSet.getLong(1));

			company.setCompName(resultSet.getString(2));

			company.setPassword(resultSet.getString(3));

			company.setEmail(resultSet.getString(4));

			// TODO - Add the coupons list from the ArrayCollection

		} catch (SQLException e) {

			throw new Exception("get company failed with id=" + id);

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

		return company;

	}

	@Override

	public synchronized Set<Company> getAllCompanies() throws Exception {

		Set<Company> companies = new HashSet<Company>();

		try {

			// conn = DriverManager.getConnection(getDBUrl());
			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// Define the Execute query

		PreparedStatement pstms = null;

		try {

			String sql = "SELECT * FROM COMPANY";
			pstms = conn.prepareStatement(sql);

			// Set the results from the database

			ResultSet resultSet = pstms.executeQuery();

			// constructor the object, retrieve the attributes from the results

			while (resultSet.next()) {
				Company company = new Company();
				company.setId(resultSet.getLong(1));
				company.setCompName(resultSet.getString(2));
				company.setPassword(resultSet.getString(3));
				company.setEmail(resultSet.getString(4));

				companies.add(company);
				System.out.println(companies);
				MyLogger.logToFile(Level.SEVERE, "Getting Company Done");

				String PathFile = "C:\\\\\\\\Directory2\\\\\\\\Sub2\\\\\\\\Sub-Sub2\\\\\\\\DBBackup.txt";
				FileWriter fileWriter = new FileWriter(PathFile, true);
				fileWriter.write((int) company.getId());
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write((int) company.getId());
				bufferedWriter.newLine();

				FileWriter fileWriter1 = new FileWriter(PathFile, true);
				fileWriter1.write(company.getCompName());
				BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
				bufferedWriter1.write(company.getCompName());
				bufferedWriter1.newLine();

				FileWriter fileWriter2 = new FileWriter(PathFile, true);
				fileWriter1.write(company.getPassword());
				BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
				bufferedWriter2.write(company.getPassword());
				bufferedWriter2.newLine();

				FileWriter fileWriter3 = new FileWriter(PathFile, true);
				fileWriter1.write(company.getEmail());
				BufferedWriter bufferedWriter3 = new BufferedWriter(fileWriter3);
				bufferedWriter2.write(company.getEmail());
				bufferedWriter2.newLine();

				bufferedWriter.close();
				bufferedWriter1.close();
				bufferedWriter2.close();
				bufferedWriter3.close();
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

		return companies;

	}

	public void printAllCompanies() throws Exception {

		Company company = new Company();

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// Define the Execute query

		PreparedStatement pstms = null;

		try {
			String sql = "SELECT * FROM COMPANY";
			pstms = conn.prepareStatement(sql);

			// Set the results from the database

			ResultSet resultSet = pstms.executeQuery();

			// constructor the object, retrieve the attributes from the results

			while (resultSet.next()) {

				company.setId(resultSet.getLong(1));

				;

				company.setCompName(resultSet.getString(2));

				company.setPassword(resultSet.getString(3));

				company.setEmail(resultSet.getString(4));

				System.out.println(company);

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

	}

	// public Boolean login(String compName, String password) throws Exception {

	// TODO Auto-generated method stub

	// return null;

	// }

	public Company getCompany(String comp_name) throws Exception {

		Company company = new Company();

		// Open a connection from the connection pool class

		try {

			conn = ConnPool.getInstance().getConnection();

		} catch (Exception e) {

			throw new Exception("The Connection is faild");

		}

		// Define the Execute query

		// java.sql.Statement stmt = null;
		PreparedStatement pstms = null;

		try {
			String sql = "SELECT * FROM COMPANY";
			pstms = conn.prepareStatement(sql);

			ResultSet resultSet = pstms.executeQuery();
			MyLogger.logToFile(Level.SEVERE, "Getting Company Done");

			// constructor the object, retrieve the attributes from the results

			while (resultSet.next()) {

				if (resultSet.getString(2).equals(comp_name)) {

					company.setId(resultSet.getLong(1));

					company.setCompName(resultSet.getString(2));

					company.setPassword(resultSet.getString(3));

					company.setEmail(resultSet.getString(4));

				}

			}

		} catch (SQLException e) {

			// Handle errors for JDBC

			throw new Exception("get Company from Database failed");

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

		return company;

	}

	public synchronized Set<Coupon> getCompanyCoupons(Company company) throws Exception {

		Set<Coupon> coupons = new HashSet<Coupon>();

		ArrayList<Long> couponIDs = new ArrayList<Long>();

		// Retrieve the PK of the company by name

		Company companyLocal = new Company();

		companyLocal = getCompany(company.getCompName());

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
			String sql1 = "SELECT * FROM COUPON";

			String sql2 = "SELECT * FROM COMPANY_COUPON";
			pstms = conn.prepareStatement(sql1);

			pstms1 = conn.prepareStatement(sql2);

			// build The SQL query

			// Set the results from the database,

			ResultSet resultSet = pstms.executeQuery();

			ResultSet resultSet2 = pstms1.executeQuery();

			// constructor the object, retrieve the attributes from the results

			while (resultSet2.next()) {

				if (resultSet2.getLong(1) == companyLocal.getId()) {

					couponIDs.add(resultSet2.getLong(2));

				}

			}

			while (resultSet.next()) {

				if (couponIDs.contains(resultSet.getLong(1))) {

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
					MyLogger.logToFile(Level.SEVERE, "Gettin g  Company Coupon Done");

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

}