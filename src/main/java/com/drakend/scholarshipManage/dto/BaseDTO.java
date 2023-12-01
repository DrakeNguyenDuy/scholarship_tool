package com.drakend.scholarshipManage.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * This abstract class present for BaseDTO object includes: id, name and
 * description
 * </p>
 * 
 * @author NguyenDuyLong2810
 */
@Getter
@Setter
public abstract class BaseDTO {
	private String id;

	private String name;

	private String description;
}
