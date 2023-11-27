package com.drakend.scholarshipManage.facade;

import com.drakend.scholarshipManage.dto.UserDTO;

public interface AuthenticationFacade {

	UserDTO register(UserDTO userDTO);
}
