package com.drakend.scholarshipManage.rest;

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

import com.drakend.scholarshipManage.common.URLCommon;
import com.drakend.scholarshipManage.dto.RoleDTO;
import com.drakend.scholarshipManage.facade.AuthorizationFacade;
import com.drakend.scholarshipManage.helper.AuthHelper;

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
	ResponseEntity<RoleDTO> edit(@PathVariable String id, @RequestBody RoleDTO roleDTO, Authentication authentication) {
		roleDTO.setId(id);
		return ResponseEntity.ok(authorizationFacade.edit(roleDTO, AuthHelper.getUserId(authentication)));
	}

	@PostMapping(URLCommon.ROLE)
	ResponseEntity<RoleDTO> create(@RequestBody RoleDTO roleDTO, Authentication authentication) {
		return ResponseEntity.ok(authorizationFacade.create(roleDTO, AuthHelper.getUserId(authentication)));
	}

	@GetMapping(URLCommon.ROLE)
	ResponseEntity<List<RoleDTO>> findAll(@RequestParam Map<String, String> query) {
		return ResponseEntity.ok(authorizationFacade.findAll(query));
	}

	@GetMapping(URLCommon.INDIVIDUAL_ROLE)
	ResponseEntity<RoleDTO> findById(@PathVariable String id) {
		return ResponseEntity.ok(authorizationFacade.findById(id));
	}

	@DeleteMapping(URLCommon.INDIVIDUAL_ROLE)
	ResponseEntity<String> delete(@PathVariable String id, Authentication authentication) {
		return ResponseEntity.ok(authorizationFacade.delete(id, AuthHelper.getUserId(authentication)));
	}

}
