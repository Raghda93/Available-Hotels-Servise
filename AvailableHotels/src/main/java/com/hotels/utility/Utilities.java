package main.java.com.hotels.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Contains a utilities methods that can be used from all the project.
 *
 */
public class Utilities {

	/**
	 * Returns true if the passed string is null or empty.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {

		if (str != null && !str.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a formatted date based on the passed date format.
	 * 
	 * @param date
	 * @param dateFormatter
	 * @return
	 */
	public static LocalDate getFormattedDate(LocalDate localDate, String dateFormatter) {

		DateTimeFormatter formatter = getDateFormatter(dateFormatter);
		String strDate = localDate.format(formatter);
		return LocalDate.parse(strDate, formatter);
	}

	/**
	 * Returns the date formatter instance.
	 * 
	 * @param dateFormatter
	 * @return
	 */
	public static DateTimeFormatter getDateFormatter(String dateFormatter) {

		DateTimeFormatter formatter = null;
		switch (dateFormatter) {
		case "ISO_OFFSET_DATE":
			formatter = DateTimeFormatter.ISO_OFFSET_DATE;
			break;
		case "ISO_DATE":
			formatter = DateTimeFormatter.ISO_DATE;
			break;
		case "ISO_ORDINAL_DATE":
			formatter = DateTimeFormatter.ISO_ORDINAL_DATE;
			break;
		case "ISO_INSTANT":
			formatter = DateTimeFormatter.ISO_INSTANT;
			break;
		case "ISO_WEEK_DATE":
			formatter = DateTimeFormatter.ISO_WEEK_DATE;
			break;
		case "ISO_LOCAL_DATE_TIME":
			formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			break;
		case "ISO_OFFSET_DATE_TIME":
			formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
			break;
		case "ISO_ZONED_DATE_TIME":
			formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
			break;
		case "ISO_DATE_TIME":
			formatter = DateTimeFormatter.ISO_DATE_TIME;
			break;
		default:
			formatter = DateTimeFormatter.ISO_LOCAL_DATE;
		}
		return formatter;
	}
	
}
