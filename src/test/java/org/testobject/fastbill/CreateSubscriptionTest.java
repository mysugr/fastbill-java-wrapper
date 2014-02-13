package org.testobject.fastbill;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testobject.fastbill.CustomerService.Customer;
import org.testobject.fastbill.CustomerService.CustomerType;
import org.testobject.fastbill.SubscriptionService.Subscription;


public class CreateSubscriptionTest {
	
	private final String USER = System.getenv("FASTBILL_USER");
	private final String TOKEN = System.getenv("FASTBILL_TOKEN");
	
	private final FastBill FASTBILL = FastBill.Factory.create(USER, TOKEN);
	
	private final CustomerService customerService = FASTBILL.getCustomerService();
	private final SubscriptionService subscriptionService = FASTBILL.getSubscriptionService();
	
	private Customer customer;
	private long createdSubscription;
	
	@Before
	public void setUp(){
		String ownId = "testUser12";
		CustomerType customerType = CustomerType.BUSINESS;
		String organization = "a company";
		String firstName = "firstname1";
		String lastName = "lastname2";
		Locale locale = Locale.US;
		String email = "company@test.testobject.org";
		String currencyCode = "EUR";
		
		customer = customerService.create(ownId, customerType, organization, firstName, lastName, locale, email, currencyCode);
	}
	
	@After
	public void tearDown(){
		customerService.delete(customer.getCustomerId());
		subscriptionService.cancelSubscription(createdSubscription);
	}
	
	@Test
	public void createSubscription(){
		String productId = "100";
		createdSubscription = subscriptionService.createSubscription(customer.getCustomerId(), productId);
		
		assertNotNull(createdSubscription);
	}
	
	@Test
	public void getSubscription(){
		// !! For this test to work, you need an existing product in fastbill with "100" as the productId!!
		String productId = "100";
		
		createdSubscription = subscriptionService.createSubscription(customer.getCustomerId(), productId);
		Subscription subscription = subscriptionService.getSubscription(createdSubscription);
		
		assertNotNull(subscription);
		assertNotNull(subscription.getId());
		assertNotNull(subscription.getLast());
		assertNotNull(subscription.getNext());
		assertNotNull(subscription.getStart());
		//assertEquals("trial", subscription.getStatus()); // not sure where to set this in fastbill when creating the product!
		assertEquals(productId, subscription.getProduct());
		assertEquals(customer.getCustomerId(), subscription.getCustomerId());
	}
	
	@Test
	public void updateSubscription(){
		
		String productId = "200";
		
		createdSubscription = subscriptionService.createSubscription(customer.getCustomerId(), productId);
		Subscription subscription = subscriptionService.getSubscription(createdSubscription);
		Subscription original = subscriptionService.getSubscription(createdSubscription);
		
		Calendar cal = new GregorianCalendar(2014 + (int)Math.random() * 100, 00, 9);
		long next = cal.getTimeInMillis();
		
		subscription.setNext(next);
		subscriptionService.updateSubscription(subscription);
		
		subscription = subscriptionService.getSubscription(createdSubscription);
		assertThat(subscription.getId(), is(original.getId()));
		assertThat(subscription.getCustomerId(), is(original.getCustomerId()));
		assertThat(subscription.getLast(), is(original.getLast()));
		assertThat(subscription.getProduct(), is(original.getProduct()));
		assertThat(subscription.getStatus(), is(original.getStatus()));
		assertThat(subscription.getStart(), is(original.getStart()));
	}

}
