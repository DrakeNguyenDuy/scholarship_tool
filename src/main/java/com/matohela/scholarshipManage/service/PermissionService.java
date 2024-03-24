package com.matohela.scholarshipManage.service;

import java.util.Map;

import com.matohela.scholarshipManage.dto.PermissionDTO;
import com.matohela.scholarshipManage.entity.Permission;

public interface PermissionService {

	Permission edit(PermissionDTO permissionDTO, String idModifiedBy);

	Permission create(PermissionDTO permissionDTO, String createdBy);

	Map<String, Object> findAll(Map<String, String> query);

	Permission findById(String id);

	String delete(String id);
}
