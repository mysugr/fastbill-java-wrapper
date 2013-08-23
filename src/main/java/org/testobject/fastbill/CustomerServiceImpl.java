package org.testobject.fastbill;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.google.common.base.Preconditions;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CustomerServiceImpl implements CustomerService{
	
	private WebResource endpointResource;

	public CustomerServiceImpl(WebResource endpointResource) {
		this.endpointResource = endpointResource;
	}

	public Customer create(String ownId, CustomerType customerType, String organization, String firstName, String lastName, Locale locale,
			String email) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("CUSTOMER_EXT_UID", ownId);
		data.put("CUSTOMER_TYPE", customerType.value);
		data.put("ORGANIZATION", organization);
		data.put("FIRST_NAME", firstName);
		data.put("LAST_NAME", lastName);
		data.put("CUSTOMER_EXT_UID", locale.getCountry());
		data.put("CUSTOMER_EXT_UID", email);
		
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("SERVICE", "customer.create");
		request.put("DATA", data);
		
		ClientResponse response = endpointResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, request);
		Preconditions.checkState(response.getStatus() == 200, "create customer failed for user " + ownId);
		
		Map<String, Object> entity = response.getEntity(Map.class);
		Map<String, Object> responseEntity = (Map<String, Object>) entity.get("RESPONSE");
		Preconditions.checkState(responseEntity.get("STATUS").equals("success"), "create customer failed for user " + ownId);
		
		Long customerId = ((Integer) responseEntity.get("CUSTOMER_ID")).longValue();
		String dashBoardUrl = (String) responseEntity.get("DASHBOARD_URL");
		String changeDataUrl = (String) responseEntity.get("CHANGEDATA_URL");
		return new Customer(customerId, ownId, customerType, organization, firstName, lastName, locale, email, dashBoardUrl, changeDataUrl);
	}

}
