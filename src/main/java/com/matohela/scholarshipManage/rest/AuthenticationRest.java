package com.matohela.scholarshipManage.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matohela.scholarshipManage.common.URLCommon;
import com.matohela.scholarshipManage.dto.LoginResponse;
import com.matohela.scholarshipManage.dto.UserDTO;
import com.matohela.scholarshipManage.facade.AuthenticationFacade;

import lombok.RequiredArgsConstructor;

/**
 * @author NguyenDuyLong2810
 * <p>This controller for authentication</p>
 */
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

}
