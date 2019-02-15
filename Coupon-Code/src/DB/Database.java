package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Database {

	/**
	 * 
	 * @category Singleton class
	 */

	private static Database instance = new Database();
	static Connection conn;
	final static JPanel panel = new JPanel();

	private Database() {
		try {
			Database.createTables(DriverManager.getConnection(getDBUrl()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(panel, "Database couldn't be created!");
		}
	}

	public static Database getDatabase() {
		return instance;
	}

	public static String getDriverData() {
		return "org.apache.derby.jdbc.ClientDriver";
	}

	// the local Port 3301
	// Name of database - MyDB

	public static String getDBUrl() {
		return "jdbc:derby://localhost:3301/MyDB;create=true";
	}

	public static void createTables(Connection con) throws Exception {

		// Connection

		conn = DriverManager.getConnection(getDBUrl());
		String sql;

		// Table 1 creation (Companies)
		try {
			java.sql.Statement stmt = conn.createStatement();
			sql = "create table Companies("
					+ "ID bigint NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1), "
					+ "COMP_NAME varchar(30) not null, " + "PASSWORD varchar(30) not null,"
					+ "EMAIL varchar(30) not null)";
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(panel, "Table Company created successfully!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(panel, "Table Company already exist!");

		}

		// Table 2 creation (Customers)
		try {
			java.sql.Statement stmt2 = conn.createStatement();
			sql = "create table Customers("
					+ "ID bigint not null primary key generated always as identity(start with 1, increment by 1), "
					+ "CUST_NAME varchar(30) not null, " + "PASSWORD varchar(30) not null)";
			stmt2.executeUpdate(sql);
			JOptionPane.showMessageDialog(panel, "Table Customer created successfully!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(panel, "Table Customer already exist!");
		}

		// Table 3 creation (Coupon)
		try {
			java.sql.Statement stmt3 = conn.createStatement();

			sql = "create table Coupons("
					+ "ID bigint not null primary key generated always as identity(start with 1, increment by 1), "
					+ "TITLE varchar(30) not null, " + "START_DATE DATE not null, " + "END_DATE DATE not null,"
					+ "AMOUNT INTEGER not null," + "TYPE varchar(10) not null," + "MESSAGE varchar(30) not null,"
					+ "PRICE double not null," + "IMAGE varchar(200) not null," + "CompanyID bigint not null)";
			stmt3.executeUpdate(sql);
			JOptionPane.showMessageDialog(panel, "Table Coupon created successfully!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(panel, "Table Coupon already exist!");
		}

		// Table 4 creation (Customer_Coupon - Join table)
		try {
			java.sql.Statement stmt4 = conn.createStatement();
			sql = "create table Customer_Coupon(" + "CUST_ID bigint not null REFERENCES Customers(ID),"
					+ "COUPON_ID bigint not null REFERENCES Coupons(ID))";
			stmt4.executeUpdate(sql);
			JOptionPane.showMessageDialog(panel, "Table Customer_Coupon created successfully!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(panel, "Table Customer_Coupon already exist!");
		}

		// Table 5 creation (Company_Coupon - Join table)
		try {
			java.sql.Statement stmt5 = conn.createStatement();
			sql = "create table Company_Coupon(" + "COMP_ID bigint not null REFERENCES Companies(ID),"
					+ "COUPON_ID bigint not null REFERENCES Coupons(ID))";
			stmt5.executeUpdate(sql);
			JOptionPane.showMessageDialog(panel, "Table Company_Coupon created successfully!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(panel, "Table Company_Coupon already exist!");
		}

		finally {
			conn.close();
		}
	}

}