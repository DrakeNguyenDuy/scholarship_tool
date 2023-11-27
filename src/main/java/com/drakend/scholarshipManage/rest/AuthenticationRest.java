package com.drakend.scholarshipManage.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drakend.scholarshipManage.common.URLCommon;
import com.drakend.scholarshipManage.dto.UserDTO;
import com.drakend.scholarshipManage.facade.AuthenticationFacade;

import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping(URLCommon.auth)
@RequiredArgsConstructor
public class AuthenticationRest {
	
	private final AuthenticationFacade authenticationFacade;

	@PostMapping(URLCommon.createSubAdmin)
	ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(authenticationFacade.register(userDTO));
	}
}
