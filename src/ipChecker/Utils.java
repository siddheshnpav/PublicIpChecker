package ipChecker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

	/*
	 * public static void directoryCreator() {
	 * 
	 * File f = new File("C:\\ipchecker");
	 * 
	 * if (!f.exists()) {
	 * 
	 * f.mkdir(); }
	 * 
	 * }
	 */

	public static void directoryCreator() {

		Path path = Paths.get("C:\\ipchecker\\Logs");

		try {

			Files.createDirectories(path);
		} catch (IOException e)

		{
			e.printStackTrace();
		}
	}

	public static void writeToLog(String a) {

		String logpath = String.format("C:\\ipchecker\\Logs\\%s_publicip.log", getLocalDateTime.date());

		try {
			FileWriter myWriter = new FileWriter(logpath, true);
			BufferedWriter br = new BufferedWriter(myWriter);
			br.write(getLocalDateTime.dateTime()+" 		"+ a + "\n");
			br.close();
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void writeToTxt(String a) {

		try {
			FileWriter myWriter = new FileWriter("C:\\ipchecker\\publicip.txt", false);
			BufferedWriter br = new BufferedWriter(myWriter);
			br.write(a);
			br.close();
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void txtFileCreator() {

		File f = new File("c:\\ipchecker\\publicip.txt");

		if (!f.exists()) {

			try {
				f.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public static boolean txtFileExist() {

		directoryCreator();
		File f = new File("c:\\ipchecker\\publicip.txt");

		if (f.exists()) {

			return true;
		}

		return false;

	}

}
