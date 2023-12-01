package com.drakend.scholarshipManage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * This class present a object for response error, includes: code, message and
 * description
 * </p>
 * @author NguyenDuyLong2810
 */
@AllArgsConstructor
@Getter
public class ErrorResponse {
	private int code;
	private String message;
	private String description;
}
