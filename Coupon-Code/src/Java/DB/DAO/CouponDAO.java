package Java.DB.DAO;

import java.util.Set;

import Java.JavaBean.Coupon;

/**
 * @Author - Oriel
 */

public interface CouponDAO {

	void createCoupon(Coupon coupon) throws Exception;

	void removeCoupon(Coupon coupon) throws Exception;

	void updateCoupon(Coupon coupon) throws Exception;

	Coupon getCoupon(long id) throws Exception;

	Set<Coupon> getAllCoupouns() throws Exception;

}