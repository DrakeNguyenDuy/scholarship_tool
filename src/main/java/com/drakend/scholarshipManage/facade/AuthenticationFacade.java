package com.drakend.scholarshipManage.facade;

import com.drakend.scholarshipManage.dto.LoginResponse;
import com.drakend.scholarshipManage.dto.RoleDTO;
import com.drakend.scholarshipManage.dto.UserDTO;

public interface AuthenticationFacade {

	UserDTO register(UserDTO userDTO);

	LoginResponse login(UserDTO userDTO);

	RoleDTO editRole(RoleDTO roleDTO, String idModifiedBy);
}
