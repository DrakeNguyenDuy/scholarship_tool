package com.matohela.scholarshipManage.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matohela.scholarshipManage.common.URLCommon;
import com.matohela.scholarshipManage.dto.RoleDTO;
import com.matohela.scholarshipManage.facade.AuthorizationFacade;
import com.matohela.scholarshipManage.helper.AuthHelper;

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

	@PatchMapping(URLCommon.INDIVIDUAL_ROLE)
	ResponseEntity<RoleDTO> editRole(@PathVariable String id, @RequestBody RoleDTO roleDTO,
			Authentication authentication) {
		roleDTO.setId(id);
		return ResponseEntity.ok(authorizationFacade.editRole(roleDTO, AuthHelper.getUserId(authentication)));
	}

	@PostMapping(URLCommon.ROLE)
	ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO, Authentication authentication) {
		return ResponseEntity.ok(authorizationFacade.createRole(roleDTO, AuthHelper.getUserId(authentication)));
	}

	@GetMapping(URLCommon.ROLE)
	ResponseEntity<List<RoleDTO>> findAllRole(@RequestParam Map<String, String> query) {
		return ResponseEntity.ok(authorizationFacade.findAllRole(query));
	}

	@GetMapping(URLCommon.INDIVIDUAL_ROLE)
	ResponseEntity<RoleDTO> findRoleById(@PathVariable String id) {
		return ResponseEntity.ok(authorizationFacade.findRoleById(id));
	}

	@DeleteMapping(URLCommon.INDIVIDUAL_ROLE)
	ResponseEntity<String> deleteRole(@PathVariable String id, Authentication authentication) {
		return ResponseEntity.ok(authorizationFacade.deleteRole(id, AuthHelper.getUserId(authentication)));
	}

}
