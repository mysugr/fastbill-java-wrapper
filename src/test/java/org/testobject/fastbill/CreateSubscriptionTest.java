package org.testobject.fastbill;

import org.junit.Test;

public class CreateSubscriptionTest {
	
	private final SubscriptionService subscriptionService = FastBill.Factory.create("aluedeke@testobject.com", "fea1de7de547714c91333e96691ef13eXuJUb9ADglQWwZgf7BaFCWDx2n65RPaR").getSubscriptionService();
	
	@Test
	public void creatSubscription(){
		long customerId = 297966;
		long productId = 4;
		
		long subscriptionId = subscriptionService.createSubscription(customerId, productId);
	}

}
