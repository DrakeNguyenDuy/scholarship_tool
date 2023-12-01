package com.drakend.scholarshipManage.facade;

import com.drakend.scholarshipManage.dto.RoleDTO;

public interface AuthorizationFacade {

	RoleDTO editRole(RoleDTO roleDTO, String idModifiedBy);
	
	RoleDTO createRole(RoleDTO roleDTO, String createBy);
}
