package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Database {

	/*
	 * 
	 * Singleton class
	 * 
	 */

	private static Database instance = new Database();

	static Connection conn;

	private Database() {

		try {
			System.out.println("test1");
			Database.createTables(DriverManager.getConnection(getDBUrl()));

		} catch (Exception e) {

			System.out.println("Database couldn't be created");

		}

	}

	public static Database getDatabase() {

		return instance;

	}

	// JDBC driver name and database URL

	public static String getDriverData() {

		return "org.apache.derby.jdbc.ClientDriver";

	}

	public static String getDBUrl() {

		return "jdbc:derby://localhost:3301/MyDB;create=true";

		// create a database by supplying a new database name in the connection URL and
		// specifying create=true

		// the local Port 3301

		// Name of database - MyDB

	}

	public static void createTables(Connection conn) throws Exception, SQLException {

		// Connection

		conn = DriverManager.getConnection(getDBUrl());

		String sql;

		// Table 1 creation (Company)

		try {

			java.sql.Statement stmt = conn.createStatement();

			sql = "create table Company("

					+ "ID bigint NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1), "

					+ "COMP_NAME varchar(30) not null, "

					+ "PASSWORD varchar(30) not null,"

					+ "EMAIL varchar(30) not null)";

			stmt.executeUpdate(sql);

			System.out.println("success:" + sql);

		} catch (SQLException e) {

			// TODO: handle exception

			System.out.println(e.getMessage());

		}

		// Table 2 creation (Customer)

		try {

			java.sql.Statement stmt2 = conn.createStatement();

			sql = "create table Customer("

					+ "ID bigint not null primary key generated always as identity(start with 1, increment by 1), "

					+ "CUST_NAME varchar(30) not null, "

					+ "PASSWORD varchar(30) not null)";

			stmt2.executeUpdate(sql);

			System.out.println("success:" + sql);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		// Table 3 creation (Coupon)

		try {

			java.sql.Statement stmt3 = conn.createStatement();

			sql = "create table Coupon("

					+ "ID bigint not null primary key generated always as identity(start with 1, increment by 1), "

					+ "TITLE varchar(30) not null, "

					+ "START_DATE DATE not null, "

					+ "END_DATE DATE not null,"

					+ "AMOUNT INTEGER not null,"

					+ "TYPE varchar(10) not null,"

					+ "MESSAGE varchar(30) not null,"

					+ "PRICE double not null,"

					+ "IMAGE varchar(200) not null)";

			stmt3.executeUpdate(sql);

			System.out.println("success:" + sql);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		// Table 4 creation (Customer_Coupon - Join table)

		try {

			java.sql.Statement stmt4 = conn.createStatement();

			sql = "create table Customer_Coupon("

					+ "CUST_ID bigint not null REFERENCES Customer(ID),"

					+ "COUPON_ID bigint not null REFERENCES Coupon(ID))";

			stmt4.executeUpdate(sql);

			System.out.println("success:" + sql);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		// Table 5 creation (Company_Coupon - Join table)

		try {

			java.sql.Statement stmt5 = conn.createStatement();

			sql = "create table Company_Coupon("

					+ "COMP_ID bigint not null REFERENCES Company(ID),"

					+ "COUPON_ID bigint not null REFERENCES Coupon(ID))";

			stmt5.executeUpdate(sql);

			System.out.println("success:" + sql);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		finally {

			conn.close();

		}

	}

	public static void selectTableCompany() throws SQLException {

		conn = DriverManager.getConnection(getDBUrl());

		java.sql.Statement stmt = conn.createStatement();

		java.sql.ResultSet results = stmt.executeQuery("SELECT * FROM COMPANY");

		ResultSetMetaData rsmd = results.getMetaData();

		int numberCols = rsmd.getColumnCount();

		for (int i = 1; i <= numberCols; i++) {
			System.out.print(rsmd.getColumnLabel(i) + "\t\t");
		}

		System.out.println("\n-----------------------------------------------------------------------------------");

		while (results.next()) {

			int id = results.getInt(1);

			String restName = results.getString(2);

			String cityName = results.getString(3);

			String email = results.getString(4);

			System.out.println(id + "\t\t" + restName + "\t\t\t" + cityName + "\t\t\t" + email);

		}

		results.close();

		conn.close();

	}

}