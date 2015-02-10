package org.testobject.fastbill;


public interface CustomerService {

	enum PaymentType {
		INVOICE(1), DEBIT(2), CASH(3), PAYPAL(4), ADVANCE(5), CREDIT(6);

		private int id;

		private PaymentType(int id) {
			this.id = id;

		}

		public static PaymentType valueById(int id) {
			for (PaymentType value : values()) {
				if (value.id == id) {
					return value;
				}
			}

			throw new IllegalArgumentException("unkonw payment type with id " + id);
		}
	}

	public class Customer {

		private final long customerId;
		private String ownId;
		private CustomerType customerType;
		private String organization;
		private String firstName;
		private String lastName;
		private String address;
		private String address2;
		private String zipCode;
		private String city;
		private String countryCode;
		private String languageCode;
		private String email;
		private String dashBoardUrl;
		private String changeDataUrl;
		private PaymentType paymentType;
		private String currencyCode;
		private String hash;


		public Customer(long customerId, String ownId, CustomerType customerType, String organization, String firstName,
				String lastName, String countryCode, String languageCode, String email, String dashBoardUrl, String changeDataUrl, PaymentType paymentType, String currencyCode, String hash) {
			this.customerId = customerId;
			this.ownId = ownId;
			this.customerType = customerType;
			this.organization = organization;
			this.firstName = firstName;
			this.lastName = lastName;
			this.countryCode = countryCode;
			this.languageCode = languageCode;
			this.email = email;
			this.dashBoardUrl = dashBoardUrl;
			this.changeDataUrl = changeDataUrl;
			this.paymentType = paymentType;

			this.setCurrencyCode(currencyCode);
			this.hash = hash;

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
		

		public void setAddress(String address) {
		    this.address = address;
		}
		
		public String getAddress() {
		    return this.address;
		}
		
		public void setAddress2(String address2) {
		    this.address2 = address2;
		}
		
		public String getAddress2() {
		    return this.address2;
		}
		
		public void setZipCode(String zipCode) {
		    this.zipCode = zipCode;
		}
		
		public String getZipCode() {
		    return this.zipCode;
		}
		
		public void setCity(String city) {
		    this.city = city;
		}
		
		public String getCity() {
		    return this.city;
		}
		
		public void setCountryCode(String countryCode) {
		    this.countryCode = countryCode;
		}
		
		public String getCountryCode() {
		    return this.countryCode;
		}
		
		public void setLanguageCode(String languageCode) {
		    this.languageCode = languageCode;
		}
		
		public String getLanguageCode() {
		    return this.languageCode;
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

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }

		public String getHash() {
			return hash;
		}

	}

	public enum CustomerType {

		BUSINESS("business"), CONSUMER("consumer");

		public final String value;

		private CustomerType(String value) {
			this.value = value;
		}

		public static CustomerType valueByString(String customerType) {
			for (CustomerType value : values()) {
				if (value.value.equals(customerType)) {
					return value;
				}
			}

			throw new IllegalArgumentException("unkonw customer type with value " + customerType);
		}
	}

    public Customer create(String ownId, CustomerType customerType, String organization, String firstName, String lastName, 
            String address, String address2, String zipCode, String city,
            String countryCode, String languageCode,
            String email, String currencyCode);

	public Customer get(long customerId);

	public void delete(long ownExtId);

}
