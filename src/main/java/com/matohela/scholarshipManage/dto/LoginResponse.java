package com.matohela.scholarshipManage.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * This class present a object for a login response, includes: userId,
 * accessToken, permissions and expireIn
 * </p>
 * 
 * @author NguyenDuyLong2810
 */
@Builder
@Getter
@Setter
public class LoginResponse {

	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("access_token")
	private String accessToken;

	private Set<String> permissions;

	@JsonProperty("exprire_in")
	private Long exprireIn;
}
