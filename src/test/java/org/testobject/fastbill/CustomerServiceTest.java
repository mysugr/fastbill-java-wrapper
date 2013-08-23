package org.testobject.fastbill;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.testobject.fastbill.CustomerService.Customer;
import org.testobject.fastbill.CustomerService.CustomerType;

public class CustomerServiceTest {
	
	private final CustomerService customerService = FastBill.Factory.create("aluedeke@testobject.com", "fea1de7de547714c91333e96691ef13eXuJUb9ADglQWwZgf7BaFCWDx2n65RPaR").getCustomerService();

	@Ignore @Test
	public void test() {
		String ownId = "testUser";
		CustomerType customerType = CustomerType.CONSUMER;
		String organization = null;
		String firstName = "firstname";
		String lastName = "lastname";
		Locale locale = Locale.GERMANY;
		String email = "sample@testobject.org";
		
		Customer customer = customerService.create(ownId, customerType, organization, firstName, lastName, locale, email);
		Assert.assertNotNull(customer);
	}

}
