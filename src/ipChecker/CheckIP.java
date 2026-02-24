package ipChecker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CheckIP {

	public static String getOldIp() {

		String oldip = null;

		File f = new File("c:\\ipchecker\\publicip.txt");

		if (f.exists()) {

			try {
				Scanner sc = new Scanner(f);
				while (sc.hasNext()) {

					oldip = sc.next();
				}
				sc.close();
				return oldip;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			
		}
		return null;
	}

	public static void checkandUpdateNewIP() {

		String publicIP = IPChecker.getPublicIP();

		if (publicIP == null) {

			Utils.writeToLog("No Internet Connection Available..!");
			return;
		}

		if (getOldIp() != null && getOldIp().equals(publicIP)) {

			Utils.writeToLog("Ips are same" + " " + getOldIp() + " , " + publicIP);
			return;
		}

		try {
			FileWriter myWriter = new FileWriter("C:\\ipchecker\\publicip.txt", false);
			BufferedWriter br = new BufferedWriter(myWriter);
			br.write(publicIP);
			Utils.writeToLog("New IP Found : " + publicIP);
			br.close();
			myWriter.close();
			TelegramAPIBot.sendTelegram(publicIP);
			return;
		} catch (IOException e) {
			Utils.writeToLog("An error occurred while writing public ip.");
			e.printStackTrace();
			return;
		}
	}

}
