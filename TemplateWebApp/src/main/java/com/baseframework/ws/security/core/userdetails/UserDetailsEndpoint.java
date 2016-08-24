package com.baseframework.ws.security.core.userdetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserDetailsEndpoint {

	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsEndpoint.class);

	private static final String NAMESPACE = "http://www.baseframework.com/ws/security/core/userdetails";
	
	@Autowired
	@Qualifier("userService")
	private UserDetailsService userDetailsService;

	@PayloadRoot(namespace = NAMESPACE, localPart = "GetUserDetailsRequest")
	@ResponsePayload
	public GetUserDetailsResponse getCustomer(@RequestPayload GetUserDetailsRequest getUserDetailsRequest)
			throws Exception {
		LOG.info("invokeInternal UserDetailsEndpoint");
		if(getUserDetailsRequest.getUsername().equals("error")){
			throw new RuntimeException("Invalid user name");
		}
		
		UserDetails u = userDetailsService.loadUserByUsername(getUserDetailsRequest.getUsername());
		
		GetUserDetailsResponse getUserDetailsResponse = new GetUserDetailsResponse();
		getUserDetailsResponse.setPassword(u.getPassword());		
		
		return getUserDetailsResponse;
	}

}
