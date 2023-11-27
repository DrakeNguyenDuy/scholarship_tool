package com.drakend.scholarshipManage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.drakend.scholarshipManage.dto.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse resourceNotFoundException(Exception ex, WebRequest request) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "NOT_FOUND_RESOURCE", ex.getMessage());
	}

	@ExceptionHandler(ResourceWasExistException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse resourceWasExistException(Exception ex, WebRequest request) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "RESOURCE_WAS_EXIST", ex.getMessage());
	}
}
