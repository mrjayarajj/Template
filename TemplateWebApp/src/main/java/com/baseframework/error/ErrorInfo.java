package com.baseframework.error;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorInfo implements java.io.Serializable {

	public ErrorInfo() {

	}

	public ErrorInfo(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	private String errorCode;

	private String errorMessage;

	private List<FieldError> filedErrors;

	public List<FieldError> getFiledErrors() {
		return filedErrors;
	}

	public void setFiledErrors(List<FieldError> filedErrors) {
		this.filedErrors = filedErrors;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
