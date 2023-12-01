package com.drakend.scholarshipManage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDTO {
	private String id;

	private String name;
	
	private String description;
}
