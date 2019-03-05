package DB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import Java.Main.Utils;

public class ConnPool {

	/**
	 * The Connection pooling is a well-known data access pattern,whose main purpose
	 * is to reduce the overhead involved in performing database connections and
	 * read/write database operations. a connection pool is, at the most basic
	 * level, a database connection cache implementation It Contains:
	 * BlockingQueue<Connection> - A Queue that additionally supports operations
	 * that wait for the queue to become non-empty when retrieving an element, and
	 * wait for space to become available in the queue when storing an element.
	 * methods : Insert - offer(e) Remove -poll() Examine - peek()
	 */

	/****************************************************
	 * Attributes
	 **********************************/
	private static ConnPool instance;
	private final int MaxConNumber = 10;
	private BlockingQueue<Connection> conQ = new LinkedBlockingDeque<>(MaxConNumber);

	private ConnPool() throws Exception {

		try {
			Class.forName(Utils.getDriverData());
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}

		Connection con = DriverManager.getConnection(Utils.getDBUrl());
		DatabaseMetaData metaData;
		ResultSet tabelResultSet;
		java.sql.Statement stmtStatement;

		/**
		 * Retrieves a DatabaseMetaData object that contains metadata about the database
		 * to which this Connection object represents a connection
		 **/
		metaData = con.getMetaData();
		stmtStatement = con.createStatement();
		/**
		 * getTables(String catalog, String schemaPattern, String tableNamePattern,
		 * String[] types) Retrieves a description of the tables available in the given
		 * catalog
		 */
		tabelResultSet = metaData.getTables(con.getCatalog(), null, "COMPANY", null);
		if (!tabelResultSet.next()) {

			Database.createTables(con);
		}

		con.close();
		while (conQ.size() < MaxConNumber) {
			con = DriverManager.getConnection(Utils.getDBUrl());
			conQ.offer(con);
		}
	}

	public static ConnPool getInstance() throws Exception {

		if (instance == null) {
			try {
				instance = new ConnPool();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return instance;
	}

	public synchronized Connection getConnection() throws Exception {
		try {
			Connection c = conQ.poll();
			c.setAutoCommit(true);
			return c;
		} catch (SQLException e) {

			throw new Exception("failed to connect server");
		}
	}

	public synchronized void returnConnection(Connection connection) {
		conQ.offer(connection);

	}

	public void closeAllConnections() {
		Connection connection;
		while (conQ.peek() != null) {
			connection = conQ.poll();
			try {
				connection.close();
			} catch (SQLException e) {
				// throw new CouponException("Unable to close all connections");
				System.out.println(e.getMessage());
			}
		}
	}

	public int printTheAvilabelConnections() {
		System.out.println("The avilable connections: " + this.conQ.size());
		return this.conQ.size();
	}

}