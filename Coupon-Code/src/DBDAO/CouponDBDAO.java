package DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Java.Exceptions.CouponNotAvailableException;
import Java.DB.DAO.CouponDAO;
import Java.JavaBean.Coupon;
import Java.JavaBean.CouponType;
import Java.Main.Utils;

/**

 */

public class CouponDBDAO implements CouponDAO {

	// Attributes

	private JPanel panel = new JPanel();
	private Connection conn;

	// Methods that DBDAO Must use from DAO

	@Override
	public void createCoupon(Coupon coupon) throws Exception {
		conn = DriverManager.getConnection(Utils.getDBUrl());
		String query = "INSERT INTO Coupons (TITLE,START_DATE,END_DATE,AMOUNT,TYPE,MESSAGE,PRICE,IMAGE,COMPANYID) VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, (Date) coupon.getStartDate());
			pstmt.setDate(3, (Date) coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getType().name());
			pstmt.setString(6, coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());
			pstmt.setLong(9, coupon.getCompanyId());

			pstmt.executeUpdate();
			pstmt.close();
			JOptionPane.showMessageDialog(panel,
					"Coupon " + coupon.getTitle() + "was created successfully on table Coupon");
			// Getting Coupon ID

			long id = 0;
			query = "SELECT ID FROM Coupons WHERE TITLE=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, coupon.getTitle());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getLong("ID");
			}
			// add an entry into Company_Coupon table
			query = "INSERT INTO Company_Coupon (COMP_ID,COUPON_ID) VALUES(?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, coupon.getCompanyId());
			pstmt.setLong(2, id);
			pstmt.executeUpdate();
			pstmt.close();
			JOptionPane.showMessageDialog(panel,
					"Coupon " + coupon.getTitle() + "was created successfully on table Company_Coupon");

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getMessage());
		} finally {
			conn.close();
		}
	}

	@Override
	public void removeCoupon(Coupon coupon) throws Exception {
		conn = DriverManager.getConnection(Utils.getDBUrl());
		try {
			String query = "DELETE FROM Customer_Coupon WHERE COUPON_ID=?;"
					+ "DELETE FROM Company_Coupon WHERE COUPON_ID=?;" + "DELETE FROM Coupons WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, coupon.getId());
			pstmt.setLong(2, coupon.getId());
			pstmt.setLong(3, coupon.getId());
			pstmt.executeUpdate();
			pstmt.close();
			JOptionPane.showMessageDialog(panel, "Coupon " + coupon.getTitle() + "wwas removed successfully");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn.close();
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Coupon> getAllCoupouns() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coupon getCoupon(long id) throws Exception {
		conn = DriverManager.getConnection(Utils.getDBUrl());
		Coupon coupon = new Coupon();
		try (Statement stm = conn.createStatement()) {
			String query = "SELECT * FROM Coupons WHERE ID=" + id;
			ResultSet rs = stm.executeQuery(query);
			rs.next();

			coupon.setId(rs.getLong(1));
			coupon.setTitle(rs.getString(2));
			coupon.setStartDate(rs.getDate(3));
			coupon.setEndDate(rs.getDate(4));
			coupon.setAmount(rs.getInt(5));
			coupon.setType(CouponType.valueOf(rs.getString("Type")));
			coupon.setMessage(rs.getString(7));
			coupon.setPrice(rs.getDouble(8));
			coupon.setImage(rs.getString(9));
			coupon.setCompanyId(rs.getLong(10));

		} catch (SQLException e) {

			throw new CouponNotAvailableException(coupon);
		} finally {
			conn.close();
		}
		return coupon;
	}

	@Override
	public Java.DB.DAO.Coupon getCoupon(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createCoupon(Java.DB.DAO.Coupon coupon) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCoupon(Java.DB.DAO.Coupon coupon) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCoupon(Java.DB.DAO.Coupon coupon) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Java.DB.DAO.Coupon getCoupon(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}