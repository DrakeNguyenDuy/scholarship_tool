package com.drakend.scholarshipManage.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drakend.scholarshipManage.common.URLCommon;
import com.drakend.scholarshipManage.dto.LoginResponse;
import com.drakend.scholarshipManage.dto.RoleDTO;
import com.drakend.scholarshipManage.dto.UserDTO;
import com.drakend.scholarshipManage.facade.AuthenticationFacade;
import com.drakend.scholarshipManage.service.impl.UserDetailImpl;
import com.drakend.scholarshipManage.service.impl.UserDetailServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class AuthenticationRest {

	private final AuthenticationFacade authenticationFacade;

	@PostMapping(URLCommon.CREATE_SUBADMIN)
	ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(authenticationFacade.register(userDTO));
	}

	@PostMapping(URLCommon.LOGIN)
	ResponseEntity<LoginResponse> login(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(authenticationFacade.login(userDTO));
	}

	@PatchMapping(URLCommon.EDIT_ROLE)
	ResponseEntity<RoleDTO> editRole(@PathVariable String id, @RequestBody RoleDTO roleDTO,
			Authentication authentication) {
		UserDetailImpl userDetailImpl = (UserDetailImpl) authentication.getPrincipal();
		roleDTO.setId(id);
		return ResponseEntity.ok(authenticationFacade.editRole(roleDTO, userDetailImpl.getUser().getId()));
	}

}
