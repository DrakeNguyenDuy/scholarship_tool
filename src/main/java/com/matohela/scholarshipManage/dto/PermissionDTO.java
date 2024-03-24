package com.matohela.scholarshipManage.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * This class a present for permission DTO and extended from {@link BaseDTO}
 * </p>
 * 
 * @author NguyenDuyLong2810
 */
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionDTO {
	
	private String id;
	private String name;
	private String description;
	Set<RoleDTO> roles;

}
