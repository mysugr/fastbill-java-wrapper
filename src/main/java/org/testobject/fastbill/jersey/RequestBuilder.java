package org.testobject.fastbill.jersey;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.base.Preconditions;

public class RequestBuilder {
	
	private final Map<String, Object> request = new HashMap<String, Object>();
	private final Map<String, Object> data = new HashMap<String, Object>();
	private final Map<String, Object> filter = new HashMap<String, Object>();

	
	private final AtomicBoolean open = new AtomicBoolean(true);
	
	public RequestBuilder(String requestName) {
		request.put("SERVICE", requestName);
	}
	
	public RequestBuilder addData(String name, Object value){
		Preconditions.checkState(open.get(), "builder was already build");
		
		data.put(name, value);
		
		return this;
	}
	
	public RequestBuilder addFilter(String name, Object value){
		Preconditions.checkState(open.get(), "builder was already build");
		
		filter.put(name, value);
		
		return this;
	}
	
	public Map<String, Object> build(){
		Preconditions.checkState(open.getAndSet(false), "builder was already build");
		
		if(data.isEmpty() == false){
			request.put("DATA", Collections.unmodifiableMap(data));
		}
		if(filter.isEmpty() == false){
			request.put("FILTER", Collections.unmodifiableMap(filter));
		}
		
		return Collections.unmodifiableMap(request);
	}

}
