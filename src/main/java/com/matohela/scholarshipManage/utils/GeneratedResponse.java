package com.matohela.scholarshipManage.utils;

import lombok.Builder;

@Builder
public class GeneratedResponse<T> {
	private String message;
	
	private T body;
	
//	private static ResponseEntity<T> instance;
//
//	static ResponseEntity<T> builder(){
//		if(instance==null) {
//			instance =new HttpEntity<T>();
//			return instance;
//		}
//			
//	}
}
