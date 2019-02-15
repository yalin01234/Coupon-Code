package Java.Exceptions;

import Java.JavaBean.Customer;

/**

 */

// Exception may occur when trying to remove a customer

public class RemoveCustomerException extends Exception {
	private Customer customer;

	public RemoveCustomerException(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String getMessage() {
		return "Removing customer " + this.customer.getCustomerName() + " failed !";
	}
}