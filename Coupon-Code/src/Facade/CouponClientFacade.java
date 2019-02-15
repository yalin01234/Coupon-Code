package Facade;

import Java.JavaBean.ClientType;

public interface CouponClientFacade {

	// determine which login type ( Administrator / User/ Company) -
	// to return the correct Facade

	public CouponClientFacade login(String name, String password, ClientType clientType);

}
