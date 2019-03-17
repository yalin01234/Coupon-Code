package Facade;

import Java.Main.CouponSystem.clientType;

public interface CouponClientFacade { // Definition the Login method

	public Boolean login(String name, String password, clientType cType) throws Exception;

}