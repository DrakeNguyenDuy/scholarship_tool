package com.matohela.scholarshipManage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO {
	private String id;

	private String name;

	private String description;
	
}
