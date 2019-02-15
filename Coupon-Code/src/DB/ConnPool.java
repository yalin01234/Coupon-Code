package DB;

public class ConnPool {

	/*
	 * @Author -
	 */

	private static ConnPool instance = new ConnPool();

	private ConnPool() {
	}

	public static ConnPool getInstance() {
		return instance;
	}

}