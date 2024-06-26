package com.matohela.scholarshipManage.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.matohela.scholarshipManage.common.Message;
import com.matohela.scholarshipManage.dto.PermissionDTO;
import com.matohela.scholarshipManage.dto.RoleDTO;
import com.matohela.scholarshipManage.entity.AuditSection;
import com.matohela.scholarshipManage.entity.Permission;
import com.matohela.scholarshipManage.entity.Role;
import com.matohela.scholarshipManage.entity.RolePermission;
import com.matohela.scholarshipManage.entity.RolePermissionKey;
import com.matohela.scholarshipManage.enums.StatusActive;
import com.matohela.scholarshipManage.exception.BadAgrumentException;
import com.matohela.scholarshipManage.exception.ResourceNotFoundException;
import com.matohela.scholarshipManage.exception.ResourceWasExistException;
import com.matohela.scholarshipManage.helper.DateHelpers;
import com.matohela.scholarshipManage.repository.GroupRoleRepository;
import com.matohela.scholarshipManage.repository.PermissionRepository;
import com.matohela.scholarshipManage.repository.RolePermissionRepository;
import com.matohela.scholarshipManage.repository.RoleRepository;
import com.matohela.scholarshipManage.service.RoleService;

import lombok.RequiredArgsConstructor;

/**
 * <p>
 * Role service implement
 * </p>
 * 
 * @author NguyenDuyLong2810
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

	private final PermissionRepository permissionRepository;

	private final RolePermissionRepository rolePermissionRepository;

	private final GroupRoleRepository groupRoleRepository;

	/**
	 * <p>
	 * This method will update data include name, description and permissions by
	 * role
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param id, roleDto, isModifiedBy
	 * @return {@link Role}
	 * @throws {@link ResourceWasExistException} - in case role name was existed
	 * @throws {@link ResourceNotFoundException} - in case can not find role by id
	 * 
	 */
	@Override
	@Transactional
	public Role edit(RoleDTO roleDTO, String idModifiedBy) {
		String roleId = roleDTO.getId();
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException(Message.RESOURCE_NOT_EXIST));
		String name = roleDTO.getName().toUpperCase();
		if (roleRepository.findByName(name).isPresent() && !role.getName().equals(name)) {
			throw new ResourceWasExistException(Message.NAME_WAS_EXISTED);
		}
		editRoleEntity(idModifiedBy, role, name);
		Set<String> idPermissions = roleDTO.getPermissions().stream().map(PermissionDTO::getId).collect(Collectors.toSet());
		rolePermissionRepository.deleteByRoleId(roleId);
		List<Permission> permissions = permissionRepository.findAllById(idPermissions);
		Set<RolePermission> rolePermissions = buildRolePermissions(role, permissions);
		rolePermissions.remove(null);
		rolePermissionRepository.saveAll(rolePermissions);
		role.setRolePermissions(rolePermissions);
		return roleRepository.save(role);
	}

	/**
	 * <p>
	 * Build entity for edit role
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param idModifiedBy
	 * @param role
	 * @param name
	 * @throws {@link BadAgrumentException} - in case given a name too long
	 */
	private void editRoleEntity(String idModifiedBy, Role role, String name) {
		if (name.length() > 255) {
			throw new BadAgrumentException(Message.NAME_TOO_LONG);
		}
		role.setName(name);
		role.setDescription(role.getDescription());
		AuditSection auditSection = role.getAuditSection();
		auditSection.setDateModified(new Date());
		auditSection.setModifiedBy(idModifiedBy);
		role.setAuditSection(auditSection);
	}

	/**
	 * <p>
	 * This method used to creating new role
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param roleDTO
	 * @param createdBy
	 * @return {@link Role}
	 * @throws {@link ResourceWasExistException} - in case given a name was existed
	 */
	@Override
	@Transactional
	public Role create(RoleDTO roleDTO, String createdBy) {
		String name = roleDTO.getName();
		if (roleRepository.findByName(name).isPresent()) {
			throw new ResourceWasExistException(Message.NAME_WAS_EXISTED);
		}
		Role role = buildCreateEntity(createdBy, roleDTO, name);
		Set<String> idPermissions = roleDTO.getPermissions().stream().map(PermissionDTO::getId).collect(Collectors.toSet());
		List<Permission> permissions = permissionRepository.findAllById(idPermissions);
		Set<RolePermission> rolePermissions = buildRolePermissions(role, permissions);
		rolePermissionRepository.saveAll(rolePermissions);
		role.setRolePermissions(rolePermissions);
		return roleRepository.save(role);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param role
	 * @param permissions
	 * @return Set<{@link RolePermission}>
	 */
	private Set<RolePermission> buildRolePermissions(Role role, List<Permission> permissions) {
		if (CollectionUtils.isEmpty(permissions))
			return Collections.emptySet();
		return permissions.stream().map(item -> RolePermission.builder()
				.rolePermissionKey(RolePermissionKey.builder().permissionId(item.getId()).roleId(role.getId()).build())
				.permission(item).role(role).build()).collect(Collectors.toSet());
	}

	/**
	 * <p>
	 * Build entity for create role entity
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param createdBy
	 * @param roleDto
	 * @param name
	 * @return {@link Role}
	 * @throws {@link BadAgrumentException} - in case given a name too long
	 */
	private Role buildCreateEntity(String createdBy, RoleDTO roleDto, String name) {
		Role result = new Role();
		if (name.length() > 255) {
			throw new BadAgrumentException(Message.NAME_TOO_LONG);
		}
		result.setName(name);
		result.setAuditSection(
				AuditSection.builder().createBy(createdBy).modifiedBy(createdBy).dateCreated(DateHelpers.getInstance())
						.dateModified(DateHelpers.getInstance()).status(StatusActive.ACTIVE).build());
		result.setDescription(roleDto.getDescription());
		return result;
	}

	/**
	 * <p>
	 * Find all roles
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param query
	 * @return List<{@link Role}>
	 */
	@Override
	public List<Role> findAll(Map<String, String> query) {
		return roleRepository.findAll();
	}

	/**
	 * <p>
	 * Find all role by id
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @return {@link Role}
	 * @throws {@link ResourceNotFoundException} - in case given id isn't exist
	 */
	@Override
	public Role findById(String id) {
		return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Message.RESOURCE_NOT_EXIST));
	}

	/**
	 * <p>
	 * Delete role and relation ship related
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @return {@link String}
	 * @throws {@link BadAgrumentException} - in case given id is null
	 */
	@Override
	@Transactional
	public String delete(String id) {
		try {
			groupRoleRepository.deleteByRoleId(id);
			rolePermissionRepository.deleteByRoleId(id);
			roleRepository.deleteById(id);
			return Message.SUCCESS;
		} catch (IllegalArgumentException e) {
			throw new BadAgrumentException(Message.ID_CAN_NOT_BE_NULL);
		}
	}

}
