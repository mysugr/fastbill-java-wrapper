package org.testobject.fastbill;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Locale;

import org.junit.After;
import org.junit.Test;
import org.testobject.fastbill.CustomerService.Customer;
import org.testobject.fastbill.CustomerService.CustomerType;

public class CustomerServiceTest {
	
	private final String USER = System.getenv("FASTBILL_USER");
	private final String TOKEN = System.getenv("FASTBILL_TOKEN");
	
	private final CustomerService customerService = FastBill.Factory.create(USER, TOKEN).getCustomerService();
	
	private Customer createdCustomer;
	
	@After
	public void tearDown(){
		if(createdCustomer != null){
			customerService.delete(createdCustomer.getCustomerId());
		}
	}
	
	@Test 
	public void testCustomerCreation() {
		String ownExtId = "testUser12";
		CustomerType customerType = CustomerType.CONSUMER;
		String organization = null;
		String firstName = "firstname";
		String lastName = "lastname";
		Locale locale = Locale.GERMANY;
		String email = "user@test.testobject.org";
		
		createdCustomer = customerService.create(ownExtId, customerType, organization, firstName, lastName, locale, email);
		assertNotNull(createdCustomer);
		assertNotNull(createdCustomer.getCustomerId());
	}
	
	@Test 
	public void testCustomerRead() {

		String ownId = "testUser12";
		CustomerType customerType = CustomerType.BUSINESS;
		String organization = "a company";
		String firstName = "firstname1";
		String lastName = "lastname2";
		Locale locale = Locale.US;
		String email = "company@test.testobject.org";
		
		createdCustomer = customerService.create(ownId, customerType, organization, firstName, lastName, locale, email);
		Customer customer = customerService.get(createdCustomer.getCustomerId());
		
		assertNotNull(customer);
		assertNotNull(customer.getCustomerId());
		assertNotNull(customer.getDashBoardUrl());
		assertNotNull(customer.getChangeDataUrl());
		assertNotNull(customer.getPaymentType());
		
		assertEquals(ownId, customer.getOwnId());
		assertEquals(customerType, customer.getCustomerType());
		assertEquals(organization, customer.getOrganization());
		assertEquals(firstName, customer.getFirstName());
		assertEquals(lastName, customer.getLastName());
		assertEquals(locale, customer.getLocale());
		assertEquals(email, customer.getEmail());
	}
	
	@Test 
	public void testCustomerDeletion() {
		String ownId = "testUser12";
		CustomerType customerType = CustomerType.BUSINESS;
		String organization = "a company";
		String firstName = "firstname1";
		String lastName = "lastname2";
		Locale locale = Locale.US;
		String email = "company@test.testobject.org";
		
		Customer acustomer = customerService.create(ownId, customerType, organization, firstName, lastName, locale, email);
		assertNotNull(acustomer);
		assertNotNull(acustomer.getCustomerId());
		
		customerService.delete(acustomer.getCustomerId());
		
		Customer customer = customerService.get(acustomer.getCustomerId());
		assertNull(customer);
	}

}
