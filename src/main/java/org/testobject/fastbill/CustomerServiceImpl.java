package org.testobject.fastbill;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.testobject.fastbill.jersey.RequestBuilder;
import org.testobject.fastbill.jersey.ResponseReader;

import com.google.common.base.Preconditions;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

class CustomerServiceImpl implements CustomerService{
	
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
			.addData("CUSTOMER_EXT_UID", locale.getCountry())
			.addData("CUSTOMER_EXT_UID", email).build();
		
		ResponseReader response = new ResponseReader(endpointResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, request));
		
		Long customerId = ((Number) response.getData("CUSTOMER_ID")).longValue();
		String dashBoardUrl = response.getData("DASHBOARD_URL");
		String changeDataUrl = response.getData("CHANGEDATA_URL");
		return new Customer(customerId, ownId, customerType, organization, firstName, lastName, locale, email, dashBoardUrl, changeDataUrl);
	}

}
