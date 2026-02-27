package ipChecker;

public class Main {

	public static void main(String[] args) {

		Utils.writeToLog("Starting IP Checker...");

		Utils.UpdateIPCheckerStatusINI("LastRunDateTime", getLocalDateTime.dateTime());
		
		CheckOldandNewIP.checkandUpdateNewIP();

		Utils.writeToLog("IP Checker finished.");

	}
}
