package com.drakend.scholarshipManage.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class UserDTO {

	private String id;

	@NotBlank
	private String email;

	@NotBlank
	private String password;
	
	@NotBlank
	@JsonProperty("full_name")
	private String fullName;

	@JsonProperty("citizend_id")
	private String citizendId;
	
}
