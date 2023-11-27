package com.drakend.scholarshipManage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
	private int statusCode;
	private String message;
	private String description;
}
