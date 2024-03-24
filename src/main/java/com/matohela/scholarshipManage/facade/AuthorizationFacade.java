package com.matohela.scholarshipManage.facade;

import java.util.List;
import java.util.Map;

import com.matohela.scholarshipManage.dto.GroupDTO;
import com.matohela.scholarshipManage.dto.PermissionDTO;
import com.matohela.scholarshipManage.dto.RoleDTO;

/**
 * <p>
 * Define all method for authorization facade
 * </p>
 * 
 * @author NguyenDuyLong2810
 */
public interface AuthorizationFacade {

	RoleDTO editRole(RoleDTO roleDTO, String modifiedBy);

	RoleDTO createRole(RoleDTO roleDTO, String createBy);

	List<RoleDTO> findAllRole(Map<String, String> query);

	RoleDTO findRoleById(String id);

	String deleteRole(String id, String modifiedBy);
	
	PermissionDTO editPermission(PermissionDTO permissionDTO, String modifiedBy);

	PermissionDTO createPermission(PermissionDTO permissionDTO, String createBy);

	Map<String, Object> findAllPermission(Map<String, String> query);

	PermissionDTO findPermissionById(String id);

	String deletePermission(String id, String modifiedBy);
	
	GroupDTO editGroup(GroupDTO groupDTO, String modifiedBy);

	GroupDTO createGroup(GroupDTO groupDTO, String createBy);

	List<GroupDTO> findAllGroup(Map<String, String> query);

	GroupDTO findGroupById(String id);

	String deleteGroup(String id, String modifiedBy);
}
