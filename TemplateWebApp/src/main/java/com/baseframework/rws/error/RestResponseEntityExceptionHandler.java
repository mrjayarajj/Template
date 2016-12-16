package com.baseframework.rws.error;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.baseframework.error.ErrorInfo;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ObjectNotFoundException.class })
	protected ResponseEntity<ErrorInfo> handle(ObjectNotFoundException e) {
		ErrorInfo error = new ErrorInfo("NoDataFoundRequest", e.getMessage());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}
}