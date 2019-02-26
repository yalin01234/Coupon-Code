
package Java.Main;

import java.time.LocalDate;
import java.util.Date;

public class Utils {

	public static Date getDate() {

		LocalDate localDate = LocalDate.now();

		Date date = java.sql.Date.valueOf(localDate);

		return date;

	}

	public static Date endDate(int numDays)

	{

		LocalDate localDate = LocalDate.now().plusDays(numDays);

		Date date = java.sql.Date.valueOf(localDate);

		return date;

	}

	public static String getDriverData() {

		return "org.apache.derby.jdbc.ClientDriver";

	}

	public static String getDBUrl() {

		return "jdbc:derby://localhost:3301/MyDB;create=true";

	}

}

// (year, month, date, hrs, min, sec)