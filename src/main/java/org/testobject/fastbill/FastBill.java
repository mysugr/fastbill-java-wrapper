package org.testobject.fastbill;

import static com.google.common.base.Preconditions.checkNotNull;

import org.testobject.fastbill.jersey.JsonClientFilter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.apache4.ApacheHttpClient4;

public interface FastBill {

	public static final String ENDPOINT = "https://my.fastbill.com/api/1.0/api.php";

	final class Factory {

		public static FastBill create(final String userName, final String token) {
			checkNotNull(userName);
			checkNotNull(token);
			
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = ApacheHttpClient4.create(clientConfig);
			client.addFilter(new HTTPBasicAuthFilter(userName, token));
			client.addFilter(new LoggingFilter(System.out));
			client.addFilter(new JsonClientFilter());

			final WebResource endpointResource = client.resource(ENDPOINT);

			return new FastBill() {
				public CustomerService getCustomerService() {
					return new CustomerServiceImpl(endpointResource);
				}
				
				@Override
				public SubscriptionService getSubscriptionService() {
					return new SubscriptionServiceImpl(endpointResource);
				}
				
				@Override
				public InvoiceService getInvoiceService() {
					return new InvoiceServiceImpl(endpointResource);
				}
			};
		}
	}

	public CustomerService getCustomerService();

	public SubscriptionService getSubscriptionService();
	
	public InvoiceService getInvoiceService();

}
