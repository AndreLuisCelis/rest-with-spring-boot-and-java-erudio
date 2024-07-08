package com.celisapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotfoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotfoundException(String ex) {
		super(ex);
	}

}
