package com.drakend.scholarshipManage.dto;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * This class present DTO for user
 * </p>
 * 
 * @author Nguyen Duy Long
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO {

	private String id;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	private String citizendId;

	private Set<String> groups;
	
}
