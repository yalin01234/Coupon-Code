package Java.DB.DAO;

import java.util.Set;

import Java.JavaBean.Coupon;

public interface CouponDAO {

	/**
	 * This interface defines all the methods are should implement in the
	 * CustomerDBDAO. It Contains : createCoupon removeCoupon updateCoupon getCoupon
	 * getAllCoupouns getCouponByType createCoupon
	 * 
	 * @throws Exception
	 */

	void createCoupon(Coupon coupon) throws Exception;

	void removeCoupon(Coupon coupon) throws Exception;

	void updateCoupon(Coupon coupon) throws Exception;

	Coupon getCoupon(long id) throws Exception;

	Set<Coupon> getAllCoupouns() throws Exception;

	Set<Coupon> getCouponByType(Coupon coupon) throws Exception;

	public void createCoupon(Coupon coupon, long id) throws Exception;

}
