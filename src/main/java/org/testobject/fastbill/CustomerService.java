package org.testobject.fastbill;

import java.util.Locale;

public interface CustomerService {

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

		public Customer(long customerId, String ownId, CustomerType customerType, String organization, String firstName,
				String lastName, Locale locale, String email, String dashBoardUrl, String changeDataUrl) {
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

	}

	enum CustomerType {

		BUSINESS("business"), CONSUMER("consumer");

		public final String value;

		private CustomerType(String value) {
			this.value = value;
		}
	}

	public Customer create(String ownId, CustomerType customerType, String organization, String firstName, String lastName, Locale locale,
			String email);

}
