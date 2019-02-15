package Java.JavaBean;

import java.util.Date;

/**
 * @author - Yalin
 */

public class Coupon {

	// Attributes

	private long id;
	private String title;
	private Date startDate;
	private Date endDate;
	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;
	private long companyId;

	/**
	 * @param no
	 * 			@param - empty Constructor
	 */

	public Coupon() {
	}

	/**
	 * @param id
	 *            - The Coupon ID
	 * @param title
	 *            - The title of the Coupon
	 * @param startDate
	 *            - The date the Coupon was bought
	 * @param endDate
	 *            - The date that the Coupon will be expired
	 * @param amount
	 *            - The amount that you get from buying the Coupon
	 * @param CouponType
	 *            - Gives you the specific type of the Coupon
	 * @param message
	 *            - The description of the Coupon
	 * @param price
	 *            - The price for buying the Coupon
	 * @param image
	 *            - The Local\URL path of the Coupon image
	 * @param CompanyID
	 *            - The ID of the company that created the coupon
	 */

	public Coupon(long id, String title, Date startdate, Date enddate, int amount, CouponType CouponType,
			String message, double price, String image, long companyId) {
		this.id = id;
		this.title = title;
		this.startDate = startdate;
		this.endDate = enddate;
		this.amount = amount;
		this.type = CouponType;
		this.message = message;
		this.price = price;
		this.image = image;
		this.companyId = companyId;

	}

	// Methods

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType couponType) {
		this.type = couponType;
	}

	public double getPrice() {
		return price;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	// ToString to display coupon's details
	public String toString() {
		return "Id:" + getId() + ", Title:" + getTitle() + ", Type:" + getType() + ", Start:" + getStartDate()
				+ ", End:" + getEndDate() + ", Amount:" + getAmount() + ", Price:" + getPrice();
	}

}