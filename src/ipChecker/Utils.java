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

	private static String DirectoryPath = System.getenv("ProgramData")+"\\IpChecker";
	private static String logDirectoryPath = System.getenv("ProgramData")+"\\IpChecker\\Logs";
	private static String publicIpFilePath = DirectoryPath+"\\publicip.txt";
	private static String IpCheckConfigINIFilePath = "C:\\Sidh\\IpChecker\\IpCheckConfig.ini";
	private static String IPCheckerStatusFilePath = DirectoryPath+"\\IPCheckerStatus.ini";

	public static String getDirectoryPath() {
		return DirectoryPath;
	}

	public static String getLogDirectoryPath() {
		return logDirectoryPath;
	}

	public static String getPublicIpFilePath() {
		return publicIpFilePath;
	}

	public static String getIpCheckConfigINIFilePath() {
		return IpCheckConfigINIFilePath;
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
		CreateDirectoryIfNotExists(logDirectoryPath);

		String logpath = String.format("%s\\%s.log", logDirectoryPath, getLocalDateTime.date());

		try {
			FileWriter myWriter = new FileWriter(logpath, true);
			BufferedWriter br = new BufferedWriter(myWriter);
			if (a.startsWith("Starting IP Checker")) {
				br.write("\n" + getLocalDateTime.dateTime() + " 		" + a + "\n");
			} else {
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

	public static String getIPCheckerStatus(String key) {

		Properties prop = new Properties();
		FileInputStream fis = null;

		File file = new File(IPCheckerStatusFilePath);

		if (file.exists()) {
			try {
				fis = new FileInputStream(file);
				prop.load(fis);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return prop.getProperty(key);

		} else {

			Utils.writeToLog("IPCheckerStatus.ini File not found. " + IPCheckerStatusFilePath);
			return null;
		}
	}

	public static String getCredentials(String a) {

		Properties prop = new Properties();
		FileInputStream fis = null;

		File file = new File(IpCheckConfigINIFilePath);

		if (file.exists()) {
			try {
				fis = new FileInputStream(file);
				prop.load(fis);

			} catch (Exception e) {
				e.printStackTrace();
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

		} else {

			Utils.writeToLog("IpCheckConfig.ini File not found. " + IPCheckerStatusFilePath);
			return null;

		}
	}
}
