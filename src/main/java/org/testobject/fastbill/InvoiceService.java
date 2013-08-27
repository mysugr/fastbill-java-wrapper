package org.testobject.fastbill;

import java.util.List;

public interface InvoiceService {
	
	public class Invoice {
		
		private String invoiceId;
		private String type;
		private long customerId;
		private long date;
		private String documentUrl;
		private double total;
		private String currency;

		public Invoice(String invoiceId, String type, long customerId, long date, String documentUrl, double total, String currency) {
			this.invoiceId = invoiceId;
			this.type = type;
			this.customerId = customerId;
			this.date = date;
			this.documentUrl = documentUrl;
			this.total = total;
			this.currency = currency;
		}
		
		public String getInvoiceId() {
			return invoiceId;
		}
		
		public String getType() {
			return type;
		}
		
		public long getCustomerId() {
			return customerId;
		}
		
		public long getDate() {
			return date;
		}
		
		public String getDocumentUrl() {
			return documentUrl;
		}
		
		public double getTotal() {
			return total;
		}
		
		public String getCurrency() {
			return currency;
		}
		
	}
	
	public List<Invoice> getInvoice(long customerId);

}
