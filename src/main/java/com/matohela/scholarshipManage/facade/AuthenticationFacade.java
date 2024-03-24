package com.matohela.scholarshipManage.facade;

import com.matohela.scholarshipManage.dto.LoginResponse;
import com.matohela.scholarshipManage.dto.UserDTO;

public interface AuthenticationFacade {

	UserDTO register(UserDTO userDTO);

	LoginResponse login(UserDTO userDTO);

}
