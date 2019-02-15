package Java.Exceptions;

import Java.JavaBean.Coupon;

/**
 * @author oriel
 */

// Exception may occur when trying to remove a specific coupon from DB

public class RemoveCouponException extends Exception {
	private Coupon coupon;

	public RemoveCouponException(Coupon coupon) {
		this.coupon = coupon;
	}

	@Override
	public String getMessage() {
		return "Removing Coupon " + coupon.getTitle() + " failed !";
	}
}