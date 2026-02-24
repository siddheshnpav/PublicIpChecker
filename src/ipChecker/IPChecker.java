package ipChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IPChecker {

	public static String getPublicIP() {

		String publicIP = null;

		try (java.util.Scanner s = new java.util.Scanner(
				java.net.URI.create(TelegramAPIBot.getCredentials("ipcheckurl")).toURL().openStream(), "UTF-8")
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

	public static StringBuffer getPublicIP2() throws IOException {

		URL url = java.net.URI.create("https://ipinfo.io/ip").toURL();
		HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
		urlcon.setRequestMethod("GET");

		String response = urlcon.getResponseMessage();
		System.out.println("Response is " + response);

		int status = urlcon.getResponseCode();

		System.out.println("Status code is " + status);

		if (status == 200) {

			BufferedReader in = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				StringBuffer publicIP = content.append(inputLine);
				System.out.println(content.append(inputLine));
				return publicIP;
			}
			in.close();
		}
		return null;
	}

}
