package com.drakend.scholarshipManage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.drakend.scholarshipManage.common.Constant;
import com.drakend.scholarshipManage.dto.BaseDTO;
import com.drakend.scholarshipManage.dto.RoleDTO;
import com.drakend.scholarshipManage.entity.AuditSection;
import com.drakend.scholarshipManage.entity.Permission;
import com.drakend.scholarshipManage.entity.Role;
import com.drakend.scholarshipManage.entity.RolePermission;
import com.drakend.scholarshipManage.entity.RolePermissionKey;
import com.drakend.scholarshipManage.exception.BadAgrumentException;
import com.drakend.scholarshipManage.exception.ResourceNotFoundException;
import com.drakend.scholarshipManage.exception.ResourceWasExistException;
import com.drakend.scholarshipManage.repository.PermissionRepository;
import com.drakend.scholarshipManage.repository.RolePermissionRepository;
import com.drakend.scholarshipManage.repository.RoleRepository;
import com.drakend.scholarshipManage.service.RoleService;

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

	/**
	 * <p>
	 * This method will update data include name, description and permissions by
	 * role
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param id, roleDto, isModifiedBy
	 * @return Role
	 * 
	 */
	@Override
	@Transactional
	public Role editRole(RoleDTO roleDTO, String idModifiedBy) {
		String roleId = roleDTO.getId();
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Can not find role"));
		String name = roleDTO.getName().toUpperCase();
		if (roleRepository.findByName(name).isPresent() && !role.getName().equals(name)) {
			throw new ResourceWasExistException("Role name was existed");
		}
		editRoleEntity(idModifiedBy, role, name);
		Set<String> idPermissions = roleDTO.getPermissions().stream().map(BaseDTO::getId).collect(Collectors.toSet());
		rolePermissionRepository.deleteByRoleId(roleId);
		List<Permission> permissions = permissionRepository.findAllById(idPermissions);
		Set<RolePermission> rolePermissions = permissions.stream().map(item -> RolePermission.builder()
				.rolePermissionKey(RolePermissionKey.builder().permissionId(item.getId()).roleId(role.getId()).build())
				.permission(item).role(role).build()).collect(Collectors.toSet());
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
	 */
	private void editRoleEntity(String idModifiedBy, Role role, String name) {
		role.setName(name);
		role.setDescription(role.getDescription());
		AuditSection auditSection = role.getAuditSection();
		auditSection.setDateModified(new Date());
		auditSection.setModifiedBy(idModifiedBy);
		role.setAuditSection(auditSection);
	}

}
