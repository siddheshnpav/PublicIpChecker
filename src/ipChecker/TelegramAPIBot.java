package ipChecker;

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
//import java.util.Scanner;

public class TelegramAPIBot {

	public static void sendTelegram(String publicip) throws IOException {
	String[] command = {
		"curl",
		"-s",
		"-X",
		"POST",
		String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=New-Public-IP=%s",
			getCredentials("token"),
			getCredentials("userid"),
			publicip)
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
    } else {
        Utils.writeToLog("Failed to send message via Telegram.");
    }
}
	
	
	public static String getCredentials(String a) {
		
		Properties prop = new Properties();


        FileInputStream fis = null;
		try {
			fis = new FileInputStream("C:\\Sidh\\ipchecker\\credentials.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Utils.writeToLog("Credentials file not found.");
		}

        try {
			prop.load(fis);
			return prop.getProperty(a);
		} catch (IOException e) {
			e.printStackTrace();
			Utils.writeToLog("Error while fetching credentials from file.");
			return null;
		}
        
	}

}