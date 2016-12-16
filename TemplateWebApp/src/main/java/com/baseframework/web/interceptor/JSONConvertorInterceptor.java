package com.baseframework.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class JSONConvertorInterceptor extends HandlerInterceptorAdapter {

	public String getJSON(Object object) {
		try {
			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			om.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
			return ow.writeValueAsString(object);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(JSONConvertorInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// response.setHeader("Cache-Control", "no-store");
		// response.setHeader("Cache-Control", "private, max-age=31536000");
		// //enabling private cache
		// response.setHeader("Pragma", "no-cache"); //for HTTP 1.0 not required
		// response.setHeader("X-Frame-Options", "SAMEORIGIN");
		// response.setHeader("Strict-Transport-Security", "max-age=31536000;
		// includeSubDomains");
		// response.setHeader("X-XSS-Protection", "1; mode=block");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {
			Object o = modelAndView.getModel().get("form");
			String jsonStr = getJSON(o);

			if (jsonStr != null && jsonStr.length() > 0) {
				modelAndView.addObject("json", jsonStr);
			} else {
				modelAndView.addObject("json", "{}");
			}
		}

	}

}
