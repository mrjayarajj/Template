package com.baseframework.biz.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JSONConvertor {

	private Object javaValue = null;

	public void setJavaValue(Object javaValue) {
		this.javaValue = javaValue;
	}

	public String getjson() {
		try {
			ObjectMapper om = new ObjectMapper();			
			om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			om.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
			return ow.writeValueAsString(this.javaValue);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	
	
}
