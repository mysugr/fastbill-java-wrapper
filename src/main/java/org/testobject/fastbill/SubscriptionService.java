package org.testobject.fastbill;

import java.util.List;

public interface SubscriptionService {

	public class Subscription {

		private long id;
		private String customerId;
		private long start;
		private long next;
		private long last;
		private String status;
		private long product;

		public Subscription(long id, String customerId, long start, long next, long last, long product, String status) {
			this.id = id;
			this.customerId = customerId;
			this.start = start;
			this.next = next;
			this.last = last;
			this.product = product;
			this.status = status;
		}

		public long getId() {
			return id;
		}

		public String getCustomerId() {
			return customerId;
		}

		public long getStart() {
			return start;
		}

		public long getNext() {
			return next;
		}

		public long getLast() {
			return last;
		}
		
		public long getProduct() {
			return product;
		}

		public String getStatus() {
			return status;
		}

	}

	long createSubscription(long customerId, long productId);

	Subscription getSubscription(long subscriptionId);
	
	List<Subscription> getSubscriptions(long customerId);

	void cancelSubscription(long subscriptionId);

}
