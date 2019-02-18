package Java.Main;

import java.time.LocalDate;
import java.util.Date;

public class Utils {

	public static Date getCurrentDate() {
		LocalDate localDate = LocalDate.now();
		Date date = java.sql.Date.valueOf(localDate);
		return date;
	}

	public static Date getExpiredDate() {

		LocalDate localDate = LocalDate.now();
		localDate = localDate.plusMonths(1);
		Date date = java.sql.Date.valueOf(localDate);

		return date;
	}

	public static String getDriverData() {
		return "org.apache.derby.jdbc.ClientDriver";
	}

	public static String getDBUrl() {
		return "jdbc:derby://localhost:3301/JBDB;create=true";
	}

}