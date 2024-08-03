package com.celisapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceNotfoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotfoundException(String ex) {
		super(ex);
	}
	
	public ResourceNotfoundException(String ex, Throwable cause) {
		super(ex, cause);
	}


}
