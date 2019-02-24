package main.java.com.hotels.beans;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This will represents the JSON object that will be returned from my service.
 * Array list of "HotelResponseBean".
 *
 */
public class HotelsList {

	public static final String HOTELS_LIST = "hotelsList";

	
	@JsonProperty(HOTELS_LIST)
	private ArrayList<HotelResponseBean> hotelsList;


	/**
	 * get list of hotels.
	 * @return
	 */
	@JsonProperty(HOTELS_LIST)
	public ArrayList<HotelResponseBean> getHotelsList() {
		return hotelsList;
	}

	/**
	 * set list of hotels
	 * @param hotelsList
	 */
	@JsonProperty(HOTELS_LIST)
	public void setHotelsList(ArrayList<HotelResponseBean> hotelsList) {
		this.hotelsList = hotelsList;
	}
}
