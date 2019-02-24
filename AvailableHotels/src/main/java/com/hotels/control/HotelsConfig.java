package main.java.com.hotels.control;

import java.util.ArrayList;

/**
 * Created this class to hold the providers properties file will config object
 * for each one
 *
 */
public class HotelsConfig {

	public static final String PROPERTIES_FOLDER_PATH = "WEB-INF/properties";
	public static final String SLASH_SEPARATOR = "/";

	public static final String BEST_HOTELS_PROPERTIES_FILE_PATH = PROPERTIES_FOLDER_PATH + SLASH_SEPARATOR
			+ "bestHotelsConfig.properties";

	public static final String CRAZY_HOTELS_PROPERTIES_FILE_PATH = PROPERTIES_FOLDER_PATH + SLASH_SEPARATOR
			+ "crazyHotelsConfig.properties";

	private static ConfigMain bestHostlsConfig = new ConfigMain(BEST_HOTELS_PROPERTIES_FILE_PATH);
	private static ConfigMain crazyHostlsConfig = new ConfigMain(CRAZY_HOTELS_PROPERTIES_FILE_PATH);

	
	/**
	 * Returns a list if Providers config data.
	 * 
	 * @return
	 */
	public static ArrayList<ConfigMain> getHotelsConfig() {
		ArrayList<ConfigMain> list = new ArrayList<>();
		list.add(bestHostlsConfig);
		list.add(crazyHostlsConfig);
		return list;
	}

}
