package main.java.com.hotels.beans;

import java.time.LocalDate;

/**
 * This bean used to hold data for hotels request.
 *
 */
public class HotelBean {
	
	public static final String FROM_DATE = "fromDate";
	public static final String TO_DATE = "toDate";
	public static final String CITY = "city";
	public static final String NUMBER_OF_ADULTS = "numberOfAdults";

	private LocalDate fromDate;

	private LocalDate toDate;

	private String city;

	private Integer numberOfAdults;
	
	
	public HotelBean(LocalDate fromDate, LocalDate toDate, String city, Integer numberOfAdults) {

		this.fromDate = fromDate;
		this.toDate = toDate;
		this.city = city;
		this.numberOfAdults = numberOfAdults;
	}


	public LocalDate getFromDate() {
		return fromDate;
	}


	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}


	public LocalDate getToDate() {
		return toDate;
	}


	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public Integer getNumberOfAdults() {
		return numberOfAdults;
	}


	public void setNumberOfAdults(Integer numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

}
