package main.java.com.hotels.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import main.java.com.hotels.beans.HotelBean;
import main.java.com.hotels.control.ConfigMain;
import main.java.com.hotels.utility.Utilities;

/**
 * This class used to prepare a request to other services. Creates a uri builder
 * by adding the required parameters as a query parameters.
 *
 */
public class RequestOperations {

	/**
	 * Sends request to services provided by config (config.getProviderURL()),
	 * with request parameters (city, fromDate, toDate and numberOfAdults) and
	 * returns a response.
	 * 
	 * @param config
	 * @param hotelBean
	 * @return
	 */
	public Response invoke(ConfigMain config, HotelBean hotelBean) {
		Client client = createClient();
		WebTarget webTarget = createWebTarget(client, hotelBean, config);
		Builder requestBuilder = createRequestBuilder(webTarget);
		return invoke(requestBuilder, HttpMethod.GET);
	}

	/**
	 * Start creating a client request.
	 * 
	 * @param clientConfig
	 * @return
	 */
	private Client createClient() {

		ClientConfig clientConfig = new ClientConfig();
		return ClientBuilder.newClient(clientConfig);
	}

	/**
	 * Build a new web resource target.
	 * 
	 * @param client
	 * @return
	 */
	private WebTarget createWebTarget(Client client, HotelBean hotelBean, ConfigMain config) {
		UriBuilder uriBuilder = getUriBuilder(hotelBean, config);
		return client.target(uriBuilder);
	}

	/**
	 * Creates UriBuilder and adds the query parameter to it.
	 * 
	 * @param config
	 * @param hotelBean
	 * @return
	 */
	private UriBuilder getUriBuilder(HotelBean hotelBean, ConfigMain config) {

		UriBuilder uriBuilder = UriBuilder.fromPath(config.getProviderURL());
		Map<String, List<Object>> queryParameters = new HashMap<>();
		List<Object> list = new ArrayList<>();

		list.add(hotelBean.getCity());
		queryParameters.put(config.getCity(), list);

		list = new ArrayList<>();
		LocalDate fromDate = Utilities.getFormattedDate(hotelBean.getFromDate(), config.getFromDateFormat());
		list.add(fromDate);
		queryParameters.put(config.getFromDate(), list);

		list = new ArrayList<>();
		LocalDate toDate = Utilities.getFormattedDate(hotelBean.getToDate(), config.getToDateFormat());
		list.add(toDate);
		queryParameters.put(config.getToDate(), list);

		list = new ArrayList<>();
		list.add(hotelBean.getNumberOfAdults());
		queryParameters.put(config.getNumberOfAdults(), list);

		applyQueryParameters(uriBuilder, queryParameters);
		return uriBuilder;
	}


	/**
	 * Used to add a query parameters to UriBuilder.
	 * 
	 * @param uriBuilder
	 * @param queryParameters
	 */
	private void applyQueryParameters(UriBuilder uriBuilder, Map<String, List<Object>> queryParameters) {
		if (queryParameters.isEmpty()) {
			return;
		}
		for (Entry<String, List<Object>> entry : queryParameters.entrySet()) {
			String paramName = entry.getKey();
			List<Object> values = entry.getValue();
			if (values == null || values.isEmpty()) {
				continue;
			}
			uriBuilder.queryParam(paramName, values.toArray());
		}
	}

	/**
	 * Building a request to the targeted web resource.
	 * 
	 * @param webTarget
	 * @return
	 */
	private Builder createRequestBuilder(WebTarget webTarget) {
		return webTarget.request();
	}

	/**
	 * Send request using the http method and return a response.
	 * 
	 * @param requestBuilder
	 * @param httpMethod
	 * @return
	 */
	private Response invoke(Builder requestBuilder, String httpMethod) {
		return requestBuilder.method(httpMethod);
	}

}
