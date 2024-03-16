package ipChecker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class getLocalDateTime {

	static String date = null;

	private static String getLocalDate() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	private static String getLocalTime() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("KK:mm a", Locale.ENGLISH);
		String time = LocalDateTime.now().format(formatter);
		return time;
	}

	public static String dateTime() {

		String dateTime = getLocalDate() + " " + getLocalTime();
		date = getLocalDate();

		return dateTime;

	}
	
	public static String date() {
		
		return getLocalDate();
	}

}
