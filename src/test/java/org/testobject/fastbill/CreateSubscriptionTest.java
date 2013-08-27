package org.testobject.fastbill;

import org.junit.Test;
import org.testobject.fastbill.SubscriptionService.Subscription;

public class CreateSubscriptionTest {
	
	private final SubscriptionService subscriptionService = FastBill.Factory.create("aluedeke@testobject.com", "fea1de7de547714c91333e96691ef13eXuJUb9ADglQWwZgf7BaFCWDx2n65RPaR").getSubscriptionService();
	
	@Test
	public void creatSubscription(){
		long customerId = 298020;
		long productId = 4;
		
		long subscriptionId = subscriptionService.createSubscription(customerId, productId);
		Subscription subscription = subscriptionService.getSubscription(subscriptionId);
		System.out.println(subscription);
	}

}
