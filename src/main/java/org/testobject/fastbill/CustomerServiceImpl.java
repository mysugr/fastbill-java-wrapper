package org.testobject.fastbill;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.testobject.fastbill.jersey.RequestBuilder;
import org.testobject.fastbill.jersey.ResponseReader;

import com.google.common.base.Preconditions;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

class CustomerServiceImpl implements CustomerService {

	private WebResource endpointResource;

	public CustomerServiceImpl(WebResource endpointResource) {
		this.endpointResource = endpointResource;
	}

	public Customer create(String ownId, CustomerType customerType, String organization, String firstName, String lastName, Locale locale,
			String email) {

		Map<String, Object> request = new RequestBuilder("customer.create")
				.addData("CUSTOMER_EXT_UID", ownId)
				.addData("CUSTOMER_TYPE", customerType.value)
				.addData("ORGANIZATION", organization)
				.addData("FIRST_NAME", firstName)
				.addData("LAST_NAME", lastName)
				.addData("COUNTRY_CODE", locale.getCountry())
				.addData("LANGUAGE_CODE", locale.getLanguage())
				.addData("EMAIL", email).build();

		ResponseReader response = new ResponseReader(endpointResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, request));

		Long customerId = ((Number) response.getData("CUSTOMER_ID")).longValue();
		String dashBoardUrl = response.getData("DASHBOARD_URL");
		String changeDataUrl = response.getData("CHANGEDATA_URL");
		String hash = response.getData("HASH");
		
		return new Customer(customerId, ownId, customerType, organization, firstName, lastName, locale, email, dashBoardUrl, changeDataUrl,
				null, hash);
	}

	@Override
	public Customer get(long customerId) {
		Map<String, Object> request = new RequestBuilder("customer.get")
				.addFilter("CUSTOMER_ID", customerId).build();

		ResponseReader response = new ResponseReader(endpointResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, request));

		List<Map<String, Object>> customers = response.getData("CUSTOMERS");
		if(customers.isEmpty()){
			return null;
		}
		Preconditions.checkArgument(customers.size() == 1, "found %s customers but expected only one for customerId", customers.size(),
				customerId);

		Map<String, Object> customer = customers.get(0);
		String ownId = (String) customer.get("CUSTOMER_EXT_UID");
		CustomerType customerType = CustomerType.valueByString((String) customer.get("CUSTOMER_TYPE"));
		String organization = (String) customer.get("ORGANIZATION");
		String firstName = (String) customer.get("FIRST_NAME");
		String lastName = (String) customer.get("LAST_NAME");
		Locale locale = new Locale(Locale.ENGLISH.toString(), (String) customer.get("COUNTRY_CODE"));
		String email = (String) customer.get("EMAIL");
		int paymentType = Integer.parseInt((String) customer.get("PAYMENT_TYPE"));
		String dashBoardUrl = (String) customer.get("DASHBOARD_URL");
		String changeDataUrl = (String) customer.get("CHANGEDATA_URL");
		String hash = (String) customer.get("HASH");

		return new Customer(customerId, ownId, customerType, organization, firstName, lastName, locale, email, dashBoardUrl, changeDataUrl,
				PaymentType.valueById(paymentType), hash);
	}
	
	@Override
	public void delete(long customerId) {
		
		Map<String, Object> request = new RequestBuilder("customer.delete").addData("CUSTOMER_ID", customerId).build();
		ResponseReader response = new ResponseReader(endpointResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, request));
		
		Preconditions.checkState("success".equals(response.getData("STATUS")));
	}

}
