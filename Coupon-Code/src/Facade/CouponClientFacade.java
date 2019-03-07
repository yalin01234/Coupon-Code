package Facade;

import Main.CouponSystem.clientType;

public interface CouponClientFacade {

	public Boolean login(String name, String password, clientType cType) throws Exception;

}