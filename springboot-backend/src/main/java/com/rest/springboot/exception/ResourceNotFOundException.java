package com.rest.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFOundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFOundException(String message) {
		super(message);
	}
}
