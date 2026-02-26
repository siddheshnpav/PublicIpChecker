package ipChecker;

public class CalculatePublicIP {

	public static String getPublicIP() {

		String publicIP = null;
		String ipCheckUrl = Utils.getCredentials("ipcheckurl");

		try (java.util.Scanner s = new java.util.Scanner(
				java.net.URI.create(ipCheckUrl).toURL().openStream(), "UTF-8")
				.useDelimiter("\\A")) {
			if (s.hasNext()) {
				publicIP = s.next();
			}

		} catch (java.io.IOException e) {
			Utils.writeToLog("Error while checking public IP");
			return null;
		}
		Utils.writeToLog("Public Ip Found from Web : " + publicIP);
		return publicIP;
	}

}
