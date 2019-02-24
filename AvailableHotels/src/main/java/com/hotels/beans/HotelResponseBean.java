package main.java.com.hotels.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the hotel info that will be returned from this service.
 *
 */
public class HotelResponseBean {

	public static final String PROVIDER = "provider";
	public static final String HOTEL_NAME = "hotelName";
	public static final String FARE = "fare";
	public static final String AMENITIES = "amenities";

	@JsonProperty(PROVIDER)
	private String provider;

	@JsonProperty(HOTEL_NAME)
	private String hotelName;

	@JsonProperty(FARE)
	private String fare;

	@JsonProperty(AMENITIES)
	private String[] amenities;
	
	private int rate;

	
	/**
	 * get provider
	 * @return
	 */
	@JsonProperty(PROVIDER)
	public String getProvider() {
		return provider;
	}

	/**
	 * set provider
	 * @param provider
	 */
	@JsonProperty(PROVIDER)
	public void setProvider(String provider) {
		this.provider = provider;
	}

	
	/**
	 * get hotel name
	 * @return
	 */
	@JsonProperty(HOTEL_NAME)
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * set hotel name
	 * @param hotelName
	 */
	@JsonProperty(HOTEL_NAME)
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	
	/**
	 * get fare
	 * @return
	 */
	@JsonProperty(FARE)
	public String getFare() {
		return fare;
	}

	/**
	 * set fare
	 * @param fare
	 */
	@JsonProperty(FARE)
	public void setFare(String fare) {
		this.fare = fare;
	}

	
	/**
	 * get amenities
	 * @return
	 */
	@JsonProperty(AMENITIES)
	public String[] getAmenities() {
		return amenities;
	}

	/**
	 * set amenities
	 * @param amenities
	 */
	@JsonProperty(AMENITIES)
	public void setAmenities(String[] amenities) {
		this.amenities = amenities;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	
	@Override
	public String toString() {
		return "[provider = " + provider + ", hotelName = " + hotelName + ", fare = " + fare + ", amenities = " + amenities + "]";
	}
}
