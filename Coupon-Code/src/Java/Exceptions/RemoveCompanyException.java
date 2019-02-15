package Java.Exceptions;

import Java.JavaBean.Company;

/**
 * @author oriel
 */

// Exception to indicate a company could not be removed

public class RemoveCompanyException extends Exception {
	private Company company;

	public RemoveCompanyException(Company company) {
		this.company = company;
	}

	@Override
	public String getMessage() {

		return "Remove company " + this.company.getCompName() + " failed !";
	}
}