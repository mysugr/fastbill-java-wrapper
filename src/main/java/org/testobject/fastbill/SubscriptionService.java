package org.testobject.fastbill;

import java.util.Date;

public interface SubscriptionService {
	
	public class Subscription {
		
		private long id;
		private String customerId;
		private Date start;
		private Date next;
		private Date last;
		private String status;

		public Subscription(long id, String customerId, Date start, Date next, Date last, String status) {
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

		public Date getStart() {
			return start;
		}

		public Date getNext() {
			return next;
		}

		public Date getLast() {
			return last;
		}

		public String getStatus() {
			return status;
		}
		
	}

	long createSubscription(long customerId, long productId);
	
	public Subscription getSubscription(long subscriotionId);

}
