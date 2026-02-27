package ipChecker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {

	private static String DirectoryPath = "C:\\ipchecker";
	private static String logDirectoryPath = "C:\\ipchecker\\Logs";
	private static String publicIpFilePath = "C:\\ipchecker\\publicip.txt";
	private static String credentialsFilePath = "C:\\Sidh\\ipchecker\\credentials.txt";
	private static String IPCheckerStatusFilePath = "C:\\ipchecker\\IPCheckerStatus.ini";

	public static String getDirectoryPath() {
		return DirectoryPath;
	}

	public static String getLogDirectoryPath() {
		return logDirectoryPath;
	}

	public static String getPublicIpFilePath() {
		return publicIpFilePath;
	}

	public static String getCredentialsFilePath() {
		return credentialsFilePath;
	}

	private static void CreateDirectoryIfNotExists(String directoryPath) {
		Path path = Paths.get(directoryPath);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				System.out.println("An error occurred while creating directory: " + directoryPath);
				e.printStackTrace();
			}
		}
	}

	public static void writeToLog(String a) {
		CreateDirectoryIfNotExists(DirectoryPath);
		CreateDirectoryIfNotExists(logDirectoryPath);

		String logpath = String.format("%s\\%s.log", logDirectoryPath, getLocalDateTime.date());

		try {
			FileWriter myWriter = new FileWriter(logpath, true);
			BufferedWriter br = new BufferedWriter(myWriter);
			if (a.startsWith("Starting IP Checker")) {
				br.write("\n" + getLocalDateTime.dateTime() + " 		" + a + "\n");
			}else{
				br.write(getLocalDateTime.dateTime() + " 		" + a + "\n");
			}
			br.close();
			myWriter.close();
			System.out.println(getLocalDateTime.dateTime() + " 		" + a + "\n");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static void writeToPublicipTxt(String a) {

		try {
			FileWriter myWriter = new FileWriter(publicIpFilePath, false);
			BufferedWriter br = new BufferedWriter(myWriter);
			br.write(a);
			br.close();
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred while writing to publicip.txt.");
			e.printStackTrace();
		}

	}

	public static synchronized void UpdateIPCheckerStatusINI(String key, String value) {

		Properties prop = new Properties();
		File file = new File(IPCheckerStatusFilePath);

		if (file.exists()) {
			try (FileInputStream fis = new FileInputStream(file)) {
				prop.load(fis);
			} catch (IOException e) {
				System.out.println("Error loading IPCheckerStatus.ini");
				e.printStackTrace();
				return; 
			}
		}

		prop.setProperty(key, value);

		try (FileOutputStream fos = new FileOutputStream(file)) {
			prop.store(fos, "Public IP Checker");
		} catch (IOException e) {
			System.out.println("Error writing to IPCheckerStatus.ini");
			e.printStackTrace();
		}
	}

	public static String getCredentials(String a) {

		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(credentialsFilePath);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
			Utils.writeToLog("Credentials file not found.");
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop.getProperty(a);

	}

}
