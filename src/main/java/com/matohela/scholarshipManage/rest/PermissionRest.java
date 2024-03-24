/**
 *  23/03/2024
 */
package com.matohela.scholarshipManage.rest;

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
import com.matohela.scholarshipManage.dto.PermissionDTO;
import com.matohela.scholarshipManage.facade.AuthorizationFacade;
import com.matohela.scholarshipManage.helper.AuthHelper;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@RestController
@RequiredArgsConstructor
public class PermissionRest {
	
	private final AuthorizationFacade authorizationFacade;
	
	@PatchMapping(URLCommon.INDIVIDUAL_PERMISSION)
	ResponseEntity<PermissionDTO> editPermission(@PathVariable String id, @RequestBody PermissionDTO permissionDTO,
			Authentication authentication) {
		permissionDTO.setId(id);
		return ResponseEntity
				.ok(authorizationFacade.editPermission(permissionDTO, AuthHelper.getUserId(authentication)));
	}

	@PostMapping(URLCommon.PERMISSION)
	ResponseEntity<PermissionDTO> createPermission(@RequestBody PermissionDTO permissionDTO,
			Authentication authentication) {
		return ResponseEntity
				.ok(authorizationFacade.createPermission(permissionDTO, AuthHelper.getUserId(authentication)));
	}

	@GetMapping(URLCommon.PERMISSION)
	ResponseEntity<Map<String, Object>> findAllPermission(@RequestParam Map<String, String> query) {
		return ResponseEntity.ok(authorizationFacade.findAllPermission(query));
	}

	@GetMapping(URLCommon.INDIVIDUAL_PERMISSION)
	ResponseEntity<PermissionDTO> findPermissionById(@PathVariable String id) {
		return ResponseEntity.ok(authorizationFacade.findPermissionById(id));
	}

	@DeleteMapping(URLCommon.INDIVIDUAL_PERMISSION)
	ResponseEntity<String> deletePermission(@PathVariable String id, Authentication authentication) {
		return ResponseEntity.ok(authorizationFacade.deletePermission(id, AuthHelper.getUserId(authentication)));
	}
}
