package org.testobject.fastbill;


public interface SubscriptionService {
	
	public class Subscription {
		
		private long id;
		private String customerId;
		private long start;
		private long next;
		private long last;
		private String status;

		public Subscription(long id, String customerId, long start, long next, long last, String status) {
			this.id = id;
			this.customerId = customerId;
			this.start = start;
			this.next = next;
			this.last = last;
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

		public String getStatus() {
			return status;
		}
		
	}

	long createSubscription(long customerId, long productId);
	
	public Subscription getSubscription(long subscriotionId);

}
