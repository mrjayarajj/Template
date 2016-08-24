package com.baseframework.rws.security.core.userdetails;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.baseframework.domain.security.core.userdetails.User;

public class UserRestControllerTest {

	/**
	 * keytool -export -alias baseframework.com -file C:\baseframework.crt -keystore C:\GitHubWorkspace\TemplateWebApp\src\main\resources\baseframework.keystore
	 * keytool -import -alias baseframework.com -keystore C:\Java\jdk1.8.0_60\jre\lib\security\cacerts -file C:\baseframework.crt
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String GET_USER = "https://www.baseframework.com:8443/TemplateWebApp/rws/user/{id}";
		String CREATE_USER = "https://www.baseframework.com:8443/TemplateWebApp/rws/user/create";

		RestTemplate restTemplate = new RestTemplate();

		SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
		rf.setReadTimeout(1 * 400);//400 milli sec
		rf.setConnectTimeout(1 * 200);//200 milli sec

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		ResponseEntity<User> result = restTemplate.exchange(GET_USER, HttpMethod.GET, entity, User.class, params);
		System.out.println(result.getBody());

		User resultIn = restTemplate.postForObject(CREATE_USER,  result , User.class);

		// User user = restTemplate.getForObject(GET_USER, User.class);
		// System.out.println(user);
	}
}
