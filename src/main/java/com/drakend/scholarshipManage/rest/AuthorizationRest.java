package com.drakend.scholarshipManage.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drakend.scholarshipManage.common.URLCommon;
import com.drakend.scholarshipManage.dto.RoleDTO;
import com.drakend.scholarshipManage.facade.AuthorizationFacade;
import com.drakend.scholarshipManage.service.impl.UserDetailImpl;

import lombok.RequiredArgsConstructor;

/**
 * <p>
 * This class include all rest API's for authorization
 * </p>
 * 
 * @author NguyenDuyLong2810
 * 
 */
@RestController
@RequiredArgsConstructor
public class AuthorizationRest {

	private final AuthorizationFacade authorizationFacade;

	@PatchMapping(URLCommon.EDIT_ROLE)
	ResponseEntity<RoleDTO> editRole(@PathVariable String id, @RequestBody RoleDTO roleDTO,
			Authentication authentication) {
		roleDTO.setId(id);
		return ResponseEntity.ok(authorizationFacade.editRole(roleDTO, getUserId(authentication)));
	}

	@PostMapping(URLCommon.CREATE_ROLE)
	ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO, Authentication authentication) {
		return ResponseEntity.ok(authorizationFacade.createRole(roleDTO, getUserId(authentication)));
	}

	/**
	 * @author NguyenDuyLong2810
	 * @param authentication
	 * @return String
	 */
	private String getUserId(Authentication authentication) {
		UserDetailImpl userDetailImpl = (UserDetailImpl) authentication.getPrincipal();
		return userDetailImpl.getUser().getId();
	}
}
