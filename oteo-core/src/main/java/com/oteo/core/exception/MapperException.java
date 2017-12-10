package com.oteo.core.exception;

@SuppressWarnings("serial")
public class MapperException extends RuntimeException {

	public MapperException(String message) {

		super(message);
	}

	public MapperException(String message, Exception ex) {

		super(message, ex);
	}

}
