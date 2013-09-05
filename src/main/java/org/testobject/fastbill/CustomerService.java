package org.testobject.fastbill;

import java.util.Locale;

public interface CustomerService {
	
	enum PaymentType {
		INVOICE(1), DEBIT(2), CASH(3), PAYPAL(4), ADVANCE(5), CREDIT(6);
		
		private int id;

		private PaymentType(int id){
			this.id = id;
			
		}
		
		public static PaymentType valueById(int id){
			for (PaymentType value : values()) {
				if(value.id == id){
					return value;
				}
			}
			
			throw new IllegalArgumentException("unkonw payment type with id " + id);
		}
	}

	class Customer {

		private final long customerId;
		private String ownId;
		private CustomerType customerType;
		private String organization;
		private String firstName;
		private String lastName;
		private Locale locale;
		private String email;
		private String dashBoardUrl;
		private String changeDataUrl;
		private PaymentType paymentType;

		public Customer(long customerId, String ownId, CustomerType customerType, String organization, String firstName,
				String lastName, Locale locale, String email, String dashBoardUrl, String changeDataUrl, PaymentType paymentType) {
			this.customerId = customerId;
			this.ownId = ownId;
			this.customerType = customerType;
			this.organization = organization;
			this.firstName = firstName;
			this.lastName = lastName;
			this.locale = locale;
			this.email = email;
			this.dashBoardUrl = dashBoardUrl;
			this.changeDataUrl = changeDataUrl;
			this.paymentType = paymentType;
		}

		public long getCustomerId() {
			return customerId;
		}

		public String getOwnId() {
			return ownId;
		}

		public void setOwnId(String ownId) {
			this.ownId = ownId;
		}

		public CustomerType getCustomerType() {
			return customerType;
		}

		public void setCustomerType(CustomerType customerType) {
			this.customerType = customerType;
		}

		public String getOrganization() {
			return organization;
		}

		public void setOrganization(String organization) {
			this.organization = organization;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public Locale getLocale() {
			return locale;
		}

		public void setLocale(Locale locale) {
			this.locale = locale;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getDashBoardUrl() {
			return dashBoardUrl;
		}
		
		public String getChangeDataUrl() {
			return changeDataUrl;
		}
		
		public PaymentType getPaymentType() {
			return paymentType;
		}

	}

	enum CustomerType {

		BUSINESS("business"), CONSUMER("consumer");

		public final String value;

		private CustomerType(String value) {
			this.value = value;
		}
		
		public static CustomerType valueByString(String customerType){
			for (CustomerType value : values()) {
				if(value.value.equals(customerType)){
					return value;
				}
			}
			
			throw new IllegalArgumentException("unkonw customer type with value " + customerType);
		}
	}

	public Customer create(String ownId, CustomerType customerType, String organization, String firstName, String lastName, Locale locale,
			String email);
	
	public Customer get(long customerId);

}
