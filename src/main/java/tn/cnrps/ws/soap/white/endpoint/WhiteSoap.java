package tn.cnrps.ws.soap.white.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import tn.cnrps.ws.soap.white.models.whitetest.StudentRequest;
import tn.cnrps.ws.soap.white.models.whitetest.WhiteTestResponse;
import tn.cnrps.ws.soap.white.services.WhiteService;

@Endpoint
public class WhiteSoap {
	
	private final static String NAME_SPACE = "http://www.cnrps.tn/ws/soap/white/models/whitetest";

	@Autowired
	private WhiteService service;
	
	@PayloadRoot(namespace = NAME_SPACE,localPart = "StudentRequest")
	@ResponsePayload
	public WhiteTestResponse getStatusClient(@RequestPayload StudentRequest request) {
		return service.register(request);
	}
}
