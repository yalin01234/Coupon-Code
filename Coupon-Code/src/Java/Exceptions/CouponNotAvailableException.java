package Java.Exceptions;

import Java.JavaBean.Coupon;

// exception may occur when trying to get a specific coupon which is not in the DB

public class CouponNotAvailableException extends Exception {
	private Coupon coupon;

	public CouponNotAvailableException(Coupon coupon) {
		this.coupon = coupon;
	}

	@Override
	public String getMessage() {
		return "Coupon " + coupon.getTitle() + " not available !";
	}
}