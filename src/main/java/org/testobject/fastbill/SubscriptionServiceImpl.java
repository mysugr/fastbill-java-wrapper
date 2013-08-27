package org.testobject.fastbill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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

		ResponseReader response = new ResponseReader(endpointResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, request));

		return ((Number) response.getData("SUBSCRIPTION_ID")).longValue();
	}

	@Override
	public Subscription getSubscription(long subscriptionId) {
		Map<String, Object> request = new RequestBuilder("subscription.get")
				.addFilter("SUBSCRIPTION_ID", subscriptionId)
				.build();

		ResponseReader response = new ResponseReader(endpointResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, request));

		List<Map<String, Object>> data = response.getData("SUBSCRIPTIONS");
		if (data.isEmpty()) {
			return null;
		}
		if (data.size() > 1) {
			throw new IllegalArgumentException("expected one result bu was " + data.size());
		}

		Map<String, Object> subscription = data.get(0);

		String customerId = (String) subscription.get("CUSTOMER_ID");
		return new Subscription(subscriptionId, customerId, Util.secondsToTimestamp((String) subscription.get("START")),
				Util.secondsToTimestamp((String) subscription.get("NEXT_EVENT")), Util.secondsToTimestamp((String) subscription.get("LAST_EVENT")),
				subscription.get("STATUS").toString());
	}

}
