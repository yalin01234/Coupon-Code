package Java.Main;

import java.io.File;

public class FileCreation { // SingleTone to create file

	public static String PathFile;
	public static String PathFolfer;
	// public static String PathFolfer = "C:\\Directory2\\Sub2\\Sub-Sub2";
	// public static String PathFile =
	// "C:\\Directory2\\Sub2\\Sub-Sub2\\testFile1.txt";

	private static FileCreation instance = new FileCreation(PathFile, PathFile);

	private FileCreation(String PathFile, String PathFolfer) {

		this.PathFolfer = PathFolfer;
		this.PathFile = PathFile;
	}

	public static FileCreation getFileCreaiton() {

		return instance;

	}

	public static void createFile() throws Exception {
		new File("C:\\Directory2\\Sub2\\Sub-Sub2").mkdirs();
		File file = new File("C:\\\\\\\\Directory2\\\\\\\\Sub2\\\\\\\\Sub-Sub2\\\\\\\\DBBackup.txt");
		File file1 = new File("C:\\\\\\\\Directory2\\\\\\\\Sub2\\\\\\\\Sub-Sub2\\\\\\\\Logs.txt");
		// System.out.println(PathFile);
		System.out.println("testyayayayayay");

		file.createNewFile();
		// Create the file
		if (file.createNewFile()) {

			System.out.println("File is created!");

		} else {

			System.out.println("File already exists.");

		}
		file1.createNewFile();
		// Create the file
		if (file.createNewFile()) {

			System.out.println("File is created!");

		} else {

			System.out.println("File already exists.");

		}

		// FileWriter fileWriter = new FileWriter(PathFile, true);

		// fileWriter.write("YalinArie Test 43");

		// BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		// bufferedWriter.write("YalinArie Test 43");

		// bufferedWriter.newLine();

		// bufferedWriter.flush();

		// bufferedWriter.close();

		// System.out.println("Successfully Copied Customer...");

	}
}