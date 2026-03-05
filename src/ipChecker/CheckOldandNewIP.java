package ipChecker;

import java.io.IOException;

public class CheckOldandNewIP {

	public static String getOldIp() {

		String oldIp = Utils.getIPCheckerStatus("lastUpdatedIP");

		if (oldIp != null) {
			return oldIp;
		} else {
			return null;
		}
	}

	public static void checkandUpdateNewIP() {

		String publicIP = FetchPublicIP.getPublicIP();
		String oldIp = getOldIp();
		String isSuccessTelegramPostStatus = Utils.getIPCheckerStatus("isSuccessTelegramPost");

		if (publicIP != null && publicIP.equals("ipcheckurlnotconfigured")) {
			return;
		}

		if (publicIP == null) {
			Utils.writeToLog("No Internet Connection Available..!");
			Utils.UpdateIPCheckerStatusINI("LastRunStatus", "No Internet Connection Available");
			return;
		}

		if (publicIP.equals("#InvalidIP#")) {
			Utils.writeToLog("Received Invalid Public IP..!");
			Utils.UpdateIPCheckerStatusINI("LastRunStatus", "Received Invalid Public IP from IP Check Service");
			return;
		}

		if (oldIp != null && oldIp.equals(publicIP)) {
			Utils.writeToLog("Ips are same" + " " + oldIp + " , " + publicIP);
			Utils.writeToLog("No IP Change Detected..! Checking Telegram Alert Status");
			Utils.UpdateIPCheckerStatusINI("LastRunStatus", "No IP Change Detected , Checking Telegram Alert Status");
			if(isSuccessTelegramPostStatus == null || isSuccessTelegramPostStatus.equals("FALSE")) {
				try {
					Utils.writeToLog("Previous Telegram Alert was not successful. Attempting to resend alert for current IP: " + publicIP);
					Utils.UpdateIPCheckerStatusINI("LastRunStatus", "No IP Change Detected but Previous Telegram Alert was not successful, Attempting to Resend Alert");
					TelegramAPIBot.sendTelegramAlert(publicIP);
					
				} catch (IOException e) {
					Utils.writeToLog("Error while sending Telegram Alert");
					Utils.UpdateIPCheckerStatusINI("LastRunStatus", "IP Change Detected but Failed to Send Telegram Alert");
					Utils.writeToLog(e.getMessage());
				}
			} 

			if(isSuccessTelegramPostStatus != null && isSuccessTelegramPostStatus.equals("TRUE")) {
				Utils.writeToLog("Previous Telegram Alert was successful. No need to resend alert for current IP: " + publicIP);
				Utils.UpdateIPCheckerStatusINI("LastRunStatus", "No IP Change Detected and Previous Telegram Alert was successful, No need to Resend Alert");
			}
			
			return;
		}

		try {
			Utils.UpdateIPCheckerStatusINI("lastUpdatedIP", publicIP);
			Utils.writeToLog("New IP Found : " + publicIP);
			TelegramAPIBot.sendTelegramAlert(publicIP);
			Utils.UpdateIPCheckerStatusINI("lastIPUpdatedDateTime", getLocalDateTime.dateTime());
			Utils.UpdateIPCheckerStatusINI("LastRunStatus", "IP Change Detected and Telegram Alert Sent");

		} catch (IOException e) {
			Utils.writeToLog("Error while sending Telegram Alert");
			Utils.UpdateIPCheckerStatusINI("LastRunStatus", "IP Change Detected but Failed to Send Telegram Alert");
			e.printStackTrace();
		}
	}

}
