package com.drakend.scholarshipManage.service;

import java.util.List;
import java.util.Map;

import com.drakend.scholarshipManage.dto.RoleDTO;
import com.drakend.scholarshipManage.entity.Role;

public interface RoleService {

	Role edit(RoleDTO roleDTO, String idModifiedBy);

	Role create(RoleDTO roleDTO, String createdBy);

	List<Role> findAll(Map<String, String> query);

	Role findById(String id);

	String delete(String id);
}
