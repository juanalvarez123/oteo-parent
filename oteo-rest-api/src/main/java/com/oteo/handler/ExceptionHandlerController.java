package com.oteo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.oteo.core.exception.ServiceException;
import com.oteo.model.response.ApiError;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneralExceptions(Exception exception) {

		ApiError apiError = ApiError.builder()
				.errorMessage(exception.getMessage())
				.build();

		return new ResponseEntity<ApiError>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ApiError> handleServiceExceptions(ServiceException exception) {

		ApiError apiError = ApiError.builder()
				.errorMessage(exception.getMessage())
				.build();

		return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	}

}
