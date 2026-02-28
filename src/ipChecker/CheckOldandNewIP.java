package ipChecker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CheckOldandNewIP {

public static String getOldIp() {

    File file = new File(Utils.getPublicIpFilePath());

    if (!file.exists()) {
        return null;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        return reader.readLine().trim();
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}

	public static void checkandUpdateNewIP() {

		String publicIP = FetchPublicIP.getPublicIP();
		String oldIp = getOldIp();

		if (publicIP == null) {
			Utils.writeToLog("No Internet Connection Available..!");
			Utils.UpdateIPCheckerStatusINI("LastRunStatus", "No Internet Connection Available");
			return;
		}

		if(publicIP.equals("#InvalidIP#")) {
			Utils.writeToLog("Received Invalid Public IP..!");
			Utils.UpdateIPCheckerStatusINI("LastRunStatus", "Received Invalid Public IP from IP Check Service");
			return;
		}

		if (oldIp != null && oldIp.equals(publicIP)) {
			Utils.writeToLog("Ips are same" + " " + oldIp + " , " + publicIP);
			Utils.UpdateIPCheckerStatusINI("LastRunStatus", "No IP Change Detected");
			return;
		}

		try {
			FileWriter myWriter = new FileWriter(Utils.getPublicIpFilePath(), false);
			BufferedWriter br = new BufferedWriter(myWriter);
			br.write(publicIP);
			Utils.writeToLog("New IP Found : " + publicIP);
			br.close();
			myWriter.close();
			TelegramAPIBot.sendTelegram(publicIP);
			Utils.UpdateIPCheckerStatusINI("lastUpdatedIP", publicIP);
			Utils.UpdateIPCheckerStatusINI("lastIPUpdatedDateTime", getLocalDateTime.dateTime());
			Utils.UpdateIPCheckerStatusINI("LastRunStatus", "IP Change Detected and Telegram Alert Sent");
			
		} catch (IOException e) {
			Utils.writeToLog("An error occurred while writing public ip.");
			e.printStackTrace();
		}
	}

}
