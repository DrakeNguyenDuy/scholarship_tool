package com.drakend.scholarshipManage.facade;

import com.drakend.scholarshipManage.dto.LoginResponse;
import com.drakend.scholarshipManage.dto.UserDTO;

public interface AuthenticationFacade {

	UserDTO register(UserDTO userDTO);
	
	LoginResponse login (UserDTO userDTO);
}
