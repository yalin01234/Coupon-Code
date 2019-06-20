package Java.Main;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MyLogger {
	private static FileHandler fh = null;

	private static MyLogger instance = new MyLogger();

	private MyLogger() {
	}

	public MyLogger getInstance() {
		return instance;
	}

	public static void logToFile(java.util.logging.Level level, String message) throws SecurityException, IOException {
		String stackTraceElements = Thread.currentThread().getStackTrace().getClass().getName();
		Logger logger = Logger.getLogger(stackTraceElements);
		FileHandler fh = new FileHandler("C:\\\\\\\\Directory2\\\\\\\\Sub2\\\\\\\\Sub-Sub2\\\\\\\\log1.txt", true);
		logger.addHandler(fh);
		logger.log(level, message);
		fh.close();

		// fh = new
		// FileHandler("C:\\\\\\\\Directory2\\\\\\\\Sub2\\\\\\\\Sub-Sub2\\\\\\\\log1.txt");
		// fh.setErrorManager(message);

	}

}
