package com.drakend.scholarshipManage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
	private int code;
	private String message;
	private String description;
}
