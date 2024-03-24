package com.matohela.scholarshipManage.facade.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.matohela.scholarshipManage.dto.GroupDTO;
import com.matohela.scholarshipManage.dto.PermissionDTO;
import com.matohela.scholarshipManage.dto.RoleDTO;
import com.matohela.scholarshipManage.entity.Permission;
import com.matohela.scholarshipManage.entity.Role;
import com.matohela.scholarshipManage.facade.AuthorizationFacade;
import com.matohela.scholarshipManage.service.PermissionService;
import com.matohela.scholarshipManage.service.RoleService;

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

	private final PermissionService permissionService;

	private final ModelMapper modelMapper;

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
		Role role = roleService.edit(roleDTO, idModifiedBy);
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
		Role role = roleService.create(roleDTO, createBy);
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

	/**
	 * <p>
	 * Find all role
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param query
	 * @return List<{@link RoleDTO}>
	 */
	@Override
	public List<RoleDTO> findAllRole(Map<String, String> query) {
		return roleService.findAll(query).stream().map(this::buildDTOReturn).toList();
	}

	/**
	 * <p>
	 * Find role by id
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param id
	 * @return {@link RoleDTO}
	 *@since 11/2023
	 */
	@Override
	public RoleDTO findRoleById(String id) {
		return buildDTOReturn(roleService.findById(id));
	}

	/**
	 * <p>
	 * Delete role by id
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param id
	 * @param modifiedBy
	 * @return {@link String}
	 */
	@Override
	public String deleteRole(String id, String modifiedBy) {
		return roleService.delete(id);
	}

	/**
	 * <p>
	 * Edit permission
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param permissionDTO
	 * @param modifiedBy
	 * @return {@link PermissionDTO}
	 */
	@Override
	public PermissionDTO editPermission(PermissionDTO permissionDTO, String modifiedBy) {
		Permission permission = permissionService.edit(permissionDTO, modifiedBy);
		return modelMapper.map(permission, PermissionDTO.class);
	}

	/**
	 * <p>
	 * Create permission
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param permissionDTO
	 * @param createBy
	 * @return {@link PermissionDTO}
	 */
	@Override
	public PermissionDTO createPermission(PermissionDTO permissionDTO, String createBy) {
		Permission permission = permissionService.create(permissionDTO, createBy);
		return modelMapper.map(permission, PermissionDTO.class);
	}

	/**
	 * <p>
	 * Find all permission
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param query
	 * @return List<{@link PermissionDTO}>
	 */
	@Override
	public Map<String, Object> findAllPermission(Map<String, String> query) {
		return permissionService.findAll(query);
	}

	/**
	 * <p>
	 * Find permission by id
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param id
	 * @return {@link PermissionDTO}
	 */
	@Override
	public PermissionDTO findPermissionById(String id) {
		return modelMapper.map(permissionService.findById(id), PermissionDTO.class);
	}

	/**
	 * <p>
	 * Delete permission by id
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param id
	 * @param modifiedBy
	 * @return {@link String}
	 */
	@Override
	public String deletePermission(String id, String modifiedBy) {
		return permissionService.delete(id);
	}

	@Override
	public GroupDTO editGroup(GroupDTO groupDTO, String modifiedBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupDTO createGroup(GroupDTO groupDTO, String createBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupDTO> findAllGroup(Map<String, String> query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupDTO findGroupById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteGroup(String id, String modifiedBy) {
		// TODO Auto-generated method stub
		return null;
	}

}
