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
			}

		}
		return null;
	}

	public static void checkandUpdateNewIP() {
		
		if (!Utils.txtFileExist()) {
			Utils.txtFileCreator();
		}

		if (IPChecker.getPublicIP()==null) {

			Utils.writeToLog("No Internet Connection Available..!");
		}

		else if (getOldIp() != null && getOldIp().equals(IPChecker.getPublicIP())) {

			Utils.writeToLog("Ips are same" + " " + getOldIp() + " , " + IPChecker.getPublicIP());
		} else {

			try {
				FileWriter myWriter = new FileWriter("C:\\ipchecker\\publicip.txt", false);
				BufferedWriter br = new BufferedWriter(myWriter);
				br.write(IPChecker.getPublicIP());
				Utils.writeToLog("New IP Found : " + IPChecker.getPublicIP());
				br.close();
				myWriter.close();
				TelegramAPIBot.sendTelegram(IPChecker.getPublicIP());
			} catch (IOException e) {
				System.out.println("An error occurred while writing public ip.");
				e.printStackTrace();
			}

		}
	}

}
