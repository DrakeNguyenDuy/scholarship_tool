package com.drakend.scholarshipManage.service;

import com.drakend.scholarshipManage.dto.RoleDTO;
import com.drakend.scholarshipManage.entity.Role;

public interface RoleService {

	Role editRole(RoleDTO roleDTO, String idModifiedBy);
}
