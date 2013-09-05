package org.testobject.fastbill;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.testobject.fastbill.CustomerService.Customer;

public class GetCustomerServiceTest {
	
	private final CustomerService customerService = FastBill.Factory.create("aluedeke@testobject.com", "fea1de7de547714c91333e96691ef13eXuJUb9ADglQWwZgf7BaFCWDx2n65RPaR").getCustomerService();

	@Ignore @Test
	public void test() {
		Customer customer = customerService.get(298359l);
		Assert.assertNotNull(customer);
	}

}
