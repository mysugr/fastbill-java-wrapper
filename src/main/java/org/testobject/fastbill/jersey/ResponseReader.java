package org.testobject.fastbill.jersey;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.sun.jersey.api.client.ClientResponse;

public class ResponseReader {

	private Map<String, Object> responseEntity;

	public ResponseReader(ClientResponse response) {
		Preconditions.checkState(response.getStatus() == 200);

		Map<String, Object> entity = response.getEntity(Map.class);
		responseEntity = (Map<String, Object>) entity.get("RESPONSE");
		Object errors = responseEntity.get("ERRORS");
		Preconditions.checkState(errors == null,  errors != null ? "request had erros " + errors.toString() : "no errors");
	}

	public <T> T getData(String name) {
		return (T) responseEntity.get(name);
	}

}
