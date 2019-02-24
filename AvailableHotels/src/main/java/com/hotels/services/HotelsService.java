package main.java.com.hotels.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.common.collect.TreeMultimap;

import main.java.com.hotels.beans.HotelBean;
import main.java.com.hotels.beans.HotelResponseBean;
import main.java.com.hotels.beans.HotelsList;
import main.java.com.hotels.control.ConfigMain;
import main.java.com.hotels.control.HotelsConfig;
import main.java.com.hotels.utility.Utilities;

@Path(HotelsService.SERVICE_NAME)
public class HotelsService {

	public static final String SERVICE_NAME = "AvailableHotels";
	public static final String SEARCH = "search";
	public static final String FROM_DATE = "fromDate";
	public static final String TO_DATE = "toDate";
	public static final String CITY = "city";
	public static final String NUMBER_OF_ADULYS = "numberOfAdults";

	@GET
	@Path(SEARCH)
	@Produces(MediaType.APPLICATION_JSON)
	public HotelsList getHotels(@QueryParam(FROM_DATE) LocalDate fromDate, @QueryParam(TO_DATE) LocalDate toDate,
			@QueryParam(CITY) String city, @QueryParam(NUMBER_OF_ADULYS) Integer numberOfAdults) {

		HotelBean hotelBean = new HotelBean(fromDate, toDate, city, numberOfAdults);
		HotelsList hotelsList = getAvailableHotels(hotelBean);
		return hotelsList;
	}

	/**
	 * Returns all hotels from all providers in a HotelsList bean.
	 * 
	 * @param numberOfAdults
	 * @param city
	 * @param toDate
	 * @param fromDate
	 * @return
	 */
	private HotelsList getAvailableHotels(HotelBean hotelBean) {

		TreeMultimap OrdredAllHotels = TreeMultimap.create(Comparator.<Integer> naturalOrder(),
				Comparator.naturalOrder());
		ArrayList<ConfigMain> hotelsConfig = HotelsConfig.getHotelsConfig();
		Iterator<ConfigMain> iterator = hotelsConfig.iterator();
		while (iterator.hasNext()) {
			ConfigMain config = iterator.next();
			RequestOperations requestOperations = new RequestOperations();
			Response response = requestOperations.invoke(config, hotelBean);
			OrdredAllHotels.putAll(handleResponse(response, config));
		}
		return getHotelsListBean(OrdredAllHotels);
	}

	/**
	 * Reads the response as a json object, then creates a hotel response bean
	 * which will hold the json object for my service and orders the result
	 * based on the Rate.
	 * 
	 * @param response
	 * @param config
	 * @return
	 */
	private TreeMultimap handleResponse(Response response, ConfigMain config) {

		TreeMultimap OrdredAllHotels = TreeMultimap.create(Comparator.<Integer> naturalOrder(),
				Comparator.naturalOrder());
		byte[] hotelsInfoArray = response.readEntity(String.class).getBytes();
		JSONArray jsonArray = new JSONArray(hotelsInfoArray);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String hotelName = jsonObject.getString(config.getHotelName());
			Integer rate = getRate(config, jsonObject);
			double fare = getFare(config, jsonObject);
			String[] amenities = getAmenities(config, jsonObject);

			HotelResponseBean hotelBean = new HotelResponseBean();
			hotelBean.setHotelName(hotelName);
			hotelBean.setFare(String.valueOf(fare) + " $");
			hotelBean.setRate(rate);
			hotelBean.setProvider(config.getProvider());
			hotelBean.setAmenities(amenities);

			OrdredAllHotels.put(rate, hotelBean);
		}
		return OrdredAllHotels;
	}

	/**
	 * Creates a HotelsList bean and set all hotels in it in order.
	 * 
	 * @param OrdredAllHotels
	 * @return
	 */
	private HotelsList getHotelsListBean(TreeMultimap OrdredAllHotels) {
		ArrayList<HotelResponseBean> hotelResponseBeans = new ArrayList<>();
		hotelResponseBeans.addAll(OrdredAllHotels.values());

		HotelsList hotelsList = new HotelsList();
		hotelsList.setHotelsList(hotelResponseBeans);
		return hotelsList;
	}

	/**
	 * Reads the amenities from JSON object as a string, then convert it to
	 * array of String,
	 * 
	 * @param config
	 * @param jsonObject
	 * @return
	 */
	private String[] getAmenities(ConfigMain config, JSONObject jsonObject) {

		JSONObject amenities = (JSONObject) jsonObject.get(config.getAmenities());
		String amenitiesStr = amenities.toString();
		if (Utilities.isEmptyString(amenitiesStr)) {
			return null;
		}
		amenitiesStr = amenitiesStr.trim();
		if (amenitiesStr.startsWith("{") && amenitiesStr.endsWith("}")) {
			int beginIndex = amenitiesStr.indexOf("{") + 1;
			int endIndex = amenitiesStr.lastIndexOf("}");
			if (beginIndex < endIndex) {
				amenitiesStr = amenitiesStr.substring(beginIndex, endIndex);
			}
		}
		return amenitiesStr.split(",");
	}

	/**
	 * Returns the fare after discount if available.
	 * 
	 * @param config
	 * @param jsonObject
	 * @return
	 */
	private double getFare(ConfigMain config, JSONObject jsonObject) {
		double fare = jsonObject.getDouble(config.getFare());
		if (Utilities.isEmptyString(config.getDiscount())) {
			Double discount = jsonObject.getDouble(config.getDiscount());
			if (discount != null) {
				fare = fare - (fare * discount);
			}
		}
		return fare;
	}

	/**
	 * Returns the rate for the hotel as an integer, this method will return the
	 * length of the string if it contains a group of (*).
	 * 
	 * @param config
	 * @param jsonObject
	 * @return
	 */
	private Integer getRate(ConfigMain config, JSONObject jsonObject) {
		Integer rate;
		if (config.getRateType().equalsIgnoreCase("string")) {
			String stars = jsonObject.getString(config.getRate());
			rate = stars.length();
		} else {
			rate = jsonObject.getInt(config.getRate());
		}
		if (rate > 5) {
			rate = 5;
		}
		return rate;
	}

}
