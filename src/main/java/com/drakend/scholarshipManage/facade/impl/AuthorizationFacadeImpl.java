package com.drakend.scholarshipManage.facade.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drakend.scholarshipManage.dto.PermissionDTO;
import com.drakend.scholarshipManage.dto.RoleDTO;
import com.drakend.scholarshipManage.entity.Role;
import com.drakend.scholarshipManage.facade.AuthorizationFacade;
import com.drakend.scholarshipManage.service.RoleService;

import lombok.RequiredArgsConstructor;

/**
 * <p>
 * This class includes all service relate to authentication like login,
 * register, logout, group, permission and role
 * </p>
 * 
 * @author NguyenDuyLong2810
 * 
 */
@Service
@RequiredArgsConstructor
public class AuthorizationFacadeImpl implements AuthorizationFacade {

	private final RoleService roleService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * <p>
	 * This method will update data include name, description and permissions by
	 * role
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param id, roleDto, isModifiedBy
	 * @return {@link RoleDTO}
	 * 
	 */
	@Override
	public RoleDTO editRole(RoleDTO roleDTO, String idModifiedBy) {
		Role role = roleService.editRole(roleDTO, idModifiedBy);
		return buildDTOReturn(role);
	}

	/**
	 * <p>
	 * This method used to creating a new role
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param roleDTO
	 * @param createBy return RoleDTO
	 * @return {@link RoleDTO}
	 */
	@Override
	public RoleDTO createRole(RoleDTO roleDTO, String createBy) {
		Role role = roleService.createRole(roleDTO, createBy);
		return buildDTOReturn(role);
	}

	/**
	 * <p>
	 * Build a role DTO to return
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param role
	 * @return {@link RoleDTO}
	 */
	private RoleDTO buildDTOReturn(Role role) {
		Set<PermissionDTO> permissionDTOs = role.getRolePermissions().stream()
				.map(item -> modelMapper.map(item.getPermission(), PermissionDTO.class)).collect(Collectors.toSet());
		RoleDTO result = modelMapper.map(role, RoleDTO.class);
		result.setPermissions(permissionDTOs);
		return result;
	}

}
