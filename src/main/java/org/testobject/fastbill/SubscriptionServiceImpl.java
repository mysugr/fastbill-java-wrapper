package org.testobject.fastbill;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.testobject.fastbill.jersey.RequestBuilder;
import org.testobject.fastbill.jersey.ResponseReader;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

class SubscriptionServiceImpl implements SubscriptionService {
	
	private final WebResource endpointResource;

	public SubscriptionServiceImpl(WebResource endpointResource) {
		this.endpointResource = endpointResource;
	}

	@Override
	public long createSubscription(long customerId, long productId) {
		Map<String, Object> request = new RequestBuilder("subscription.create")
			.addData("CUSTOMER_ID", customerId)
			.addData("ARTICLE_NUMBER", productId).build();
		
		ResponseReader response = new ResponseReader(endpointResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, request));
		
		return ((Number)response.getData("SUBSCRIPTION_ID")).longValue();
	}

}
