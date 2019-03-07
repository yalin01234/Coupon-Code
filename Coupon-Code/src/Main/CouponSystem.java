package Main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DB.ConnPool;
import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CouponClientFacade;
import Facade.CustomerFacade;

public class CouponSystem {

	private static CouponSystem instance = new CouponSystem();

	/************************************** GET-DAO ******************************/

	public enum clientType {
		Admin, Customer, Company
	};

	private CouponSystem() {

	}

	public static CouponSystem getCouponSystem() {
		return instance;
	}

	public CouponClientFacade login(String name, String password, clientType cType) throws Exception {
		// TODO Auto-generated method stub

		switch (cType) {
		case Admin:
			AdminFacade adminFacade = new AdminFacade();
			if (adminFacade.login(name, password, cType)) {
				return adminFacade;
				// TODO - DailyCouponExpirationTask
			} else {
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame,
						"The login failed, the Name or Password of the Admin is not valid, please try again !!!!");
				return null;
			}

		case Customer:
			CustomerFacade customerFacade = new CustomerFacade();
			if (customerFacade.login(name, password, cType)) {
				return customerFacade;
				// TODO - DailyCouponExpirationTask
			} else {
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame,
						"The login failed, the Name or Password of the Customer is not valid, please try again !!!!");
				return null;
			}

		case Company:
			CompanyFacade companyFacade = new CompanyFacade();
			if (companyFacade.login(name, password, cType)) {
				return companyFacade;
				// TODO - DailyCouponExpirationTask
			} else {
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame,
						"The login failed, the Name or Password of the Company is not valid, please try again !!!!");
				return null;
			}

		}
		return null;

	}

	public void ShutDown() throws Exception {

		ConnPool.getInstance().closeAllConnections();
		// TODO - Stop the DailyTask
	}

}