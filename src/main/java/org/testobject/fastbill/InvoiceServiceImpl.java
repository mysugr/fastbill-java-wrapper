package org.testobject.fastbill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.testobject.fastbill.jersey.RequestBuilder;
import org.testobject.fastbill.jersey.ResponseReader;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class InvoiceServiceImpl implements InvoiceService {

	private WebResource endpointResource;

	public InvoiceServiceImpl(WebResource endpointResource) {
		this.endpointResource = endpointResource;
	}

	@Override
	public List<Invoice> getInvoice(long customerId) {
		Map<String, Object> request = new RequestBuilder("invoice.get")
				.addFilter("CUSTOMER_ID", customerId)
				.build();

		ResponseReader response = new ResponseReader(endpointResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, request));

		List<Invoice> invoices = new ArrayList<>();
		List<Map<String, Object>> invoiceData = response.getData("INVOICES");
		for (Map<String, Object> map : invoiceData) {
			String invoiceId = (String) map.get("INVOICE_ID");
			String type = map.get("TYPE").toString();
			String date = (String) map.get("INVOICE_DATE");
			String documentUrl = (String) map.get("DOCUMENT_URL");
			double total = (double) map.get("TOTAL");
			String currency = (String) map.get("CURRENCY_CODE");
			invoices.add(new Invoice(invoiceId, type, customerId, Util.dayToTimestamp(date), documentUrl, total, currency));
		}

		return invoices;
	}

}
