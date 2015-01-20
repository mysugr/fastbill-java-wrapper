package org.testobject.fastbill;

import java.util.Date;
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
	

	public class InvoiceItem {
	    
	    private String articleNumber;
	    private String description;
	    private int quantity;
	    private float unitPrice;
	    private float vatPercent;
	    
	    public InvoiceItem(String articleNumber, String description, int quantity, float unitPrice, float vatPercent) {
	        this.articleNumber = articleNumber;
	        this.description = description;
	        this.quantity = quantity;
	        this.unitPrice = unitPrice;
	        this.vatPercent = vatPercent;
	    }

        public String getArticleNumber() {
            return articleNumber;
        }

        public String getDescription() {
            return description;
        }

        public int getQuantity() {
            return quantity;
        }

        public float getUnitPrice() {
            return unitPrice;
        }

        public float getVatPercent() {
            return vatPercent;
        }
	}
	
	
	public List<Invoice> getInvoice(long customerId);
	
	public Long createInvoice(long customerId, String currency, Date deliveryDate, boolean euDelivery, Iterable<InvoiceItem> items);
}
