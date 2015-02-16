package org.testobject.fastbill;

import javax.ws.rs.core.MediaType;

import org.testobject.fastbill.jersey.RequestBuilder;
import org.testobject.fastbill.jersey.ResponseReader;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Service {
    
    protected WebResource endpointResource;
    
    public Service(WebResource endpointResource) {
        this.endpointResource = endpointResource;
    }
    
    public RequestBuilder request(String endpointName) {
        return new RequestBuilder(endpointName);
    }
    
    public ResponseReader execute(Object request) {
        return new ResponseReader(this.endpointResource
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, request));
    }
}
