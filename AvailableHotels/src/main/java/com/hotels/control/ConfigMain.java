package main.java.com.hotels.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import main.java.com.hotels.utility.Utilities;

/**
 * This class used to read the properties. The properties file contains the
 * "keys" which represent the "AvailableHotels" parameters name, and the values
 * which represent the "Providers" parameters name.
 * 
 * ex.: in my service "AvailableHotels", the price parameter called : (fare),
 * but in "BestHotels" called :(hotelFare) and in "CrazyHotels" called :
 * (price), and the Format for dates are different, in my service it
 * accepts"ISO_LOCAL_DATE", but in "CrazyHotels" it accepts "ISO_INSTANT".
 * 
 * This class used to make the project scalable, since you can add any number of
 * providers, by adding a new properties file in (WebContent/WEB-INF/properties)
 * folder[ you can use "providerHotelsConfig.properties.copy" cope and remove
 * the extension ".copy" , then fill it with the provider parameters name and types
 * and add a new instance in HotelsConfig class.
 *
 */
public class ConfigMain {

	public static final String PROVIDER_PROPERTY_NAME = "provider";
	public static final String PROVIDER_URL_PROPERTY_NAME = "providerURL";

	public static final String FROM_DATE_PROPERTY_NAME = "fromDate";
	public static final String FROM_DATE_FORMAT_PROPERTY_NAME = "fromDateFormat";
	public static final String TO_DATE_PROPERTY_NAME = "toDate";
	public static final String TO_DATE_FORMAT_PROPERTY_NAME = "toDateFormat";
	public static final String CITY_PROPERTY_NAME = "city";
	public static final String NUMBER_OF_ADULTS_PROPERTY_NAME = "numberOfAdults";

	public static final String HOTEL_NAME_PROPERTY_NAME = "hotelName";
	public static final String FARE_PROPERTY_NAME = "fare";
	public static final String DISCOUNT_PROPERTY_NAME = "discount";
	public static final String AMENITIES_PROPERTY_NAME = "amenities";
	public static final String RATE_PROPERTY_NAME = "rate";
	public static final String RATE_TYPE_PROPERTY_NAME = "rate";

	private String filePath;

	private String provider;
	private String providerURL;
	private String fromDate;
	private String fromDateFormat;
	private String toDate;
	private String toDateFormat;
	private String hotelName;
	private String fare;
	private String discount;
	private String amenities;
	private String rate;
	private String rateType;
	private String city;
	private String numberOfAdults;

	private static Logger logger = Logger.getLogger(ConfigMain.class);

	public ConfigMain(String propertiesFilePath) {
		
		if(Utilities.isEmptyString(propertiesFilePath)){
			return;
		}
		this.filePath = propertiesFilePath;
		Properties propertiesFile = getPropertiesFile();
		init(propertiesFile);
	}

	/**
	 * Used to load properties file.
	 * 
	 * @throws IOException
	 */
	public Properties getPropertiesFile() {
		Properties propsFile = new Properties();
		File file = new File(getFilePath());
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			propsFile.load(fileInputStream);

		} catch (IOException e) {
			propsFile = null;
			logger.error("Faild to load file :" + filePath + " : " + e.getMessage());
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				logger.error("Faild to close connection for :" + getFilePath());
			}
		}

		return propsFile;
	}

	public String getFilePath() {
		return filePath;
	}

	/**
	 * This method used to initialize the properties variable.
	 * @param propertiesFile 
	 * 
	 * @throws IOException
	 */
	public void init(Properties propertiesFile) {

		if (propertiesFile == null) {
			return;
		}
		provider = getProperties(propertiesFile, PROVIDER_PROPERTY_NAME);
		providerURL = getProperties(propertiesFile, PROVIDER_URL_PROPERTY_NAME);
		fromDate = getProperties(propertiesFile, FROM_DATE_PROPERTY_NAME);
		fromDateFormat = getProperties(propertiesFile, FROM_DATE_FORMAT_PROPERTY_NAME);
		toDate = getProperties(propertiesFile, TO_DATE_PROPERTY_NAME);
		toDateFormat = getProperties(propertiesFile, TO_DATE_FORMAT_PROPERTY_NAME);
		city = getProperties(propertiesFile, CITY_PROPERTY_NAME);
		numberOfAdults = getProperties(propertiesFile, NUMBER_OF_ADULTS_PROPERTY_NAME);

		hotelName = getProperties(propertiesFile, HOTEL_NAME_PROPERTY_NAME);
		fare = getProperties(propertiesFile, FARE_PROPERTY_NAME);
		discount = getProperties(propertiesFile, DISCOUNT_PROPERTY_NAME);
		amenities = getProperties(propertiesFile, AMENITIES_PROPERTY_NAME);
		rate = getProperties(propertiesFile, RATE_PROPERTY_NAME);
		rateType = getProperties(propertiesFile, RATE_TYPE_PROPERTY_NAME);
	}

	/**
	 * returns the property value for the passed property name.
	 * 
	 * @param propertiesFile
	 * @param propertyName
	 * @return
	 */
	private String getProperties(Properties propertiesFile, String propertyName) {

		String propertyValue = propertiesFile.getProperty(propertyName);
		if (Utilities.isEmptyString(propertyValue)) {
			return propertyValue.trim();
		}
		return null;
	}

	public String getProviderURL() {
		return providerURL;
	}

	public void setProviderURL(String providerURL) {
		this.providerURL = providerURL;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getFromDateFormat() {
		return fromDateFormat;
	}

	public void setFromDateFormat(String fromDateFormat) {
		this.fromDateFormat = fromDateFormat;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getToDateFormat() {
		return toDateFormat;
	}

	public void setToDateFormat(String toDateFormat) {
		this.toDateFormat = toDateFormat;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(String numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

}
