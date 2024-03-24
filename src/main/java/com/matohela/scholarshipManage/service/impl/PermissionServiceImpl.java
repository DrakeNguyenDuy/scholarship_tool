package com.matohela.scholarshipManage.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.matohela.scholarshipManage.common.Constant;
import com.matohela.scholarshipManage.common.Message;
import com.matohela.scholarshipManage.common.ParameterCommon;
import com.matohela.scholarshipManage.dto.BaseDTO;
import com.matohela.scholarshipManage.dto.PermissionDTO;
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
import com.matohela.scholarshipManage.repository.PermissionRepository;
import com.matohela.scholarshipManage.repository.RolePermissionRepository;
import com.matohela.scholarshipManage.repository.RoleRepository;
import com.matohela.scholarshipManage.service.PermissionService;
import com.matohela.scholarshipManage.specifications.PermissionSpecification;
import com.matohela.scholarshipManage.utils.ServiceUtils;

import lombok.RequiredArgsConstructor;

/**
 * <p>
 * Permission service implement
 * </p>
 * 
 * @author NguyenDuyLong2810
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

	private final PermissionRepository permissionRepository;

	private final RolePermissionRepository rolePermissionRepository;

	private final RoleRepository roleRepository;

	/**
	 * <p>
	 * This method will update data include name, description
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param id, permissionDTO, isModifiedBy
	 * @return {@link Permission}
	 * @throws {@link ResourceWasExistException} - in case permission name was
	 *                existed
	 * @throws {@link ResourceNotFoundException} - in case can not find permission
	 *                by id
	 * 
	 */
	@Override
	@Transactional
	public Permission edit(PermissionDTO permissionDTO, String idModifiedBy) {
		String permissionId = permissionDTO.getId();
		Permission permission = permissionRepository.findById(permissionId)
				.orElseThrow(() -> new ResourceNotFoundException(Message.RESOURCE_NOT_EXIST));
		String name = permissionDTO.getName().toUpperCase();
		if (permissionRepository.findByName(name).isPresent() && !permissionDTO.getName().equals(name)) {
			throw new ResourceWasExistException(Message.NAME_WAS_EXISTED);
		}
		editPermissionEntity(idModifiedBy, permission, name);
		rolePermissionRepository.deleteByPermissionId(permissionId);
		Set<RolePermission> rolePermissions = saveRolePermission(permissionDTO, permission);
		permission.setRolePermissions(rolePermissions);
		return permissionRepository.save(permission);
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param permissionDTO
	 * @param permission
	 * @return Set<{@link RolePermission}>
	 */
	public Set<RolePermission> saveRolePermission(PermissionDTO permissionDTO, Permission permission) {
		Set<String> roleIds = permissionDTO.getRoles().stream().map(BaseDTO::getId).collect(Collectors.toSet());
		List<Role> roles = roleRepository.findAllById(roleIds);
		Set<RolePermission> rolePermissions = buildRolePermissions(permission, roles);
		rolePermissionRepository.saveAll(rolePermissions);
		return rolePermissions;
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param permission
	 * @param permissions
	 * @return Set<{@link RolePermission}>
	 */
	private Set<RolePermission> buildRolePermissions(Permission permission, List<Role> roles) {
		if (CollectionUtils.isEmpty(roles))
			return Collections.emptySet();
		return roles.stream().map(item -> RolePermission.builder()
				.rolePermissionKey(
						RolePermissionKey.builder().permissionId(permission.getId()).roleId(item.getId()).build())
				.permission(permission).role(item).build()).collect(Collectors.toSet());
	}

	/**
	 * <p>
	 * Build entity for edit permission
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param idModifiedBy
	 * @param permission
	 * @param name
	 * @throws {@link BadAgrumentException} - in case given a name too long
	 */
	private void editPermissionEntity(String idModifiedBy, Permission permission, String name) {
		if (name.length() > Constant.LENGTH_255) {
			throw new BadAgrumentException(Message.NAME_TOO_LONG);
		}
		permission.setName(name);
		permission.setDescription(permission.getDescription());
		AuditSection auditSection = permission.getAuditSection();
		auditSection.setDateModified(new Date());
		auditSection.setModifiedBy(idModifiedBy);
		permission.setAuditSection(auditSection);
	}

	/**
	 * <p>
	 * This method used to creating new permission
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param permissionDTO
	 * @param createdBy
	 * @return {@link Permission}
	 * @throws {@link ResourceWasExistException} - in case given a name was existed
	 */
	@Override
	@Transactional
	public Permission create(PermissionDTO permissionDTO, String createdBy) {
		String name = permissionDTO.getName();
		if (permissionRepository.findByName(name).isPresent()) {
			throw new ResourceWasExistException(Message.NAME_WAS_EXISTED);
		}
		Permission permission = buildCreateEntity(createdBy, permissionDTO.getDescription(), name);
		Set<RolePermission> rolePermissions = saveRolePermission(permissionDTO, permission);
		permission.setRolePermissions(rolePermissions);
		return permissionRepository.save(permission);
	}

	/**
	 * <p>
	 * Build entity for create permission entity
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param createdBy
	 * @param description
	 * @param name
	 * @return {@link Permission}
	 * @throws {@link BadAgrumentException} - in case given a name too long
	 */
	private Permission buildCreateEntity(String createdBy, String description, String name) {
		Permission result = new Permission();
		if (name.length() > Constant.LENGTH_255) {
			throw new BadAgrumentException(Message.NAME_TOO_LONG);
		}
		result.setName(name);
		result.setAuditSection(
				AuditSection.builder().createBy(createdBy).modifiedBy(createdBy).dateCreated(DateHelpers.getInstance())
						.dateModified(DateHelpers.getInstance()).status(StatusActive.ACTIVE).build());
		result.setDescription(description);
		return result;
	}

	/**
	 * <p>
	 * Find all permissions
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param query
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> findAll(Map<String, String> query) {
		Map<String, Object> resultMap = new HashMap<>();
		int page = ServiceUtils.getPageFromFilter(query.get(ParameterCommon.PAGE));
		Pageable pageable = PageRequest.of(page, ParameterCommon.LIMIT);
		Specification<Permission> specification = PermissionSpecification.search(query);
		Page<Permission> pages = permissionRepository.findAll(specification, pageable);
		if (pages.hasContent()) {
			List<Permission> permissions = pages.getContent();
			List<PermissionDTO> permissionDTOs = permissions.stream().map(item -> PermissionDTO.builder()
					.id(item.getId()).name(item.getName()).description(item.getDescription()).build()).toList();
			resultMap.put(ParameterCommon.DATA, permissionDTOs);
			resultMap.put(ParameterCommon.PAGE, page);
			resultMap.put(ParameterCommon.TOTAL_PAGE, pages.getTotalPages());
			return resultMap;
		}
		return Collections.emptyMap();
	}

	/**
	 * <p>
	 * Find all permission by id
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @return {@link Permission}
	 * @throws {@link ResourceNotFoundException} - in case given id isn't exist
	 */
	@Override
	public Permission findById(String id) {
		return permissionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Message.RESOURCE_NOT_EXIST));
	}

	/**
	 * <p>
	 * Delete permission
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
			permissionRepository.deleteById(id);
			return Message.SUCCESS;
		} catch (IllegalArgumentException e) {
			throw new BadAgrumentException(Message.ID_CAN_NOT_BE_NULL);
		}
	}

}
