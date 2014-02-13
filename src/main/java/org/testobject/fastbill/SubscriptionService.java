package org.testobject.fastbill;

import java.math.BigDecimal;
import java.util.List;

public interface SubscriptionService {

	public class Subscription {

		private long id;
		private long customerId;
		private long start;
		private long next;
		private long last;
		private String status;
		private String product;
		private String hash;

		public Subscription(long id, long customerId, long start, long next, long last, String product, String status, String hash) {

			this.id = id;
			this.customerId = customerId;
			this.start = start;
			this.next = next;
			this.last = last;
			this.product = product;
			this.status = status;
			this.hash = hash;
		}

		public long getId() {
			return id;
		}

		public long getCustomerId() {
			return customerId;
		}

		public long getStart() {
			return start;
		}

		public long getNext() {
			return next;
		}

		public void setNext(long next) {
			this.next = next;
		}

		public long getLast() {
			return last;
		}
		public String getProduct() {
			return product;
		}

		public String getStatus() {
			return status;
		}

		public String getHash() {
			return hash;
		}

	}

	long createSubscription(long customerId, String productId);

	Subscription getSubscription(long subscriptionId);

	List<Subscription> getSubscriptions(long customerId);

	void cancelSubscription(long subscriptionId);

	void setUsagedata(long subscriptionId, String productId, int quantity, BigDecimal unitPrice, String description, String usageDate, String currencyCode);

	void updateSubscription(Subscription subscription);

}
