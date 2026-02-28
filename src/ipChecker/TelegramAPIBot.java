package ipChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

public class TelegramAPIBot {

	public static void sendTelegram(String publicip) throws IOException {

		String message = TelegramAlertTemplate(publicip);
		String encodedMessage = URLEncoder.encode(message, "UTF-8");
		String[] command = {
				"curl",
				"-s",
				"-X",
				"POST",
				String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s&parse_mode=Markdown",
						Utils.getCredentials("token"),
						Utils.getCredentials("userid"),
						encodedMessage)
		};

		ProcessBuilder processBuilder = new ProcessBuilder(command);
		Process process = processBuilder.start();

		// Read response
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			response.append(line);
		}

		String jsonResponse = response.toString();

		if (jsonResponse.contains("\"ok\":true")) {
			Utils.writeToLog("Message sent successfully via Telegram Bot.");
			Utils.UpdateIPCheckerStatusINI("LastSuccessTelegramPost", getLocalDateTime.dateTime());
		} else {
			Utils.writeToLog("Failed to send message via Telegram.");
		}
	}

	public static String TelegramAlertTemplate(String publicIp) {

		String machine = SystemInfo.getMachineName();
		String user = SystemInfo.getUsername();
		String osName = SystemInfo.getOsName();
		String timestamp = getLocalDateTime.dateTime();

		return String.format(
				"*PUBLIC IP CHANGE DETECTED*%n%n" +
						"*New IP:* *%s*%n%n" +
						"*PC Name:* %s%n" +
						"*User:* %s%n" +
						"*OS:* %s%n" +
						"*Timestamp:* %s",
				publicIp,
				machine,
				user,
				osName,
				timestamp

		);
	}
}