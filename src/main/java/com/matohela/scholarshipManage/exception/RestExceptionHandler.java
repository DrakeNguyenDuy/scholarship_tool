package com.matohela.scholarshipManage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.matohela.scholarshipManage.dto.ErrorResponse;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorResponse resourceNotFoundException(Exception ex, WebRequest request) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "NOT_FOUND_RESOURCE", ex.getMessage());
	}

	@ExceptionHandler(ResourceWasExistException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse resourceWasExistException(Exception ex, WebRequest request) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "RESOURCE_WAS_EXIST", ex.getMessage());
	}
	
	@ExceptionHandler(BadAgrumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse badAgrumentException(Exception ex, WebRequest request) {
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "BAD_AGRUMENT", ex.getMessage());
	}
	
}
