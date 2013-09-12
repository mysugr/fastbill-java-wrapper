package org.testobject.fastbill;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Ignore;
import org.junit.Test;
import org.testobject.fastbill.SubscriptionService.Subscription;

@Ignore
public class CreateSubscriptionTest {
	
	private final SubscriptionService subscriptionService = FastBill.Factory.create("aluedeke@testobject.com", "fea1de7de547714c91333e96691ef13eXuJUb9ADglQWwZgf7BaFCWDx2n65RPaR").getSubscriptionService();
	
	@Ignore @Test
	public void createSubscription(){
		long customerId = 298020;
		long productId = 4;
		
		long subscriptionId = subscriptionService.createSubscription(customerId, productId);
		Subscription subscription = subscriptionService.getSubscription(subscriptionId);
		System.out.println(subscription);
	}
	
	@Test
	public void getSubscription(){
		long customerId = 298020;
		long productId = 4;
		
		long subscriptionId = subscriptionService.createSubscription(customerId, productId);
		Subscription subscription = subscriptionService.getSubscription(subscriptionId);
		System.out.println(subscription);
	}
	
	@Test
	public void updateSubscription(){
		
		long customerId = 298467;
		long productId = 200;
		
		
		long subscriptionId = subscriptionService.createSubscription(customerId, productId);
		Subscription subscription = subscriptionService.getSubscription(subscriptionId);
		Subscription original = subscriptionService.getSubscription(subscriptionId);
		
		Calendar cal = new GregorianCalendar(2014 + (int)Math.random() * 100, 00, 9);
		long next = cal.getTimeInMillis();
		
		subscription.setNext(next);
		subscriptionService.updateSubscription(subscription);
		
		subscription = subscriptionService.getSubscription(subscriptionId);
		assertThat(subscription.getId(), is(original.getId()));
		assertThat(subscription.getCustomerId(), is(original.getCustomerId()));
		assertThat(subscription.getLast(), is(original.getLast()));
		assertThat(subscription.getProduct(), is(original.getProduct()));
		assertThat(subscription.getStatus(), is(original.getStatus()));
		assertThat(subscription.getStart(), is(original.getStart()));
		
		
		System.out.println(subscription);
	}

}
