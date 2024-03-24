package com.matohela.scholarshipManage.exception;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2713696192433886681L;

	public ResourceNotFoundException(String message){
		super(message);
	}
}
