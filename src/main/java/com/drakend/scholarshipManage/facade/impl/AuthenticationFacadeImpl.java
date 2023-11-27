package com.drakend.scholarshipManage.facade.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.drakend.scholarshipManage.dto.UserDTO;
import com.drakend.scholarshipManage.entity.User;
import com.drakend.scholarshipManage.exception.ResourceWasExistException;
import com.drakend.scholarshipManage.facade.AuthenticationFacade;
import com.drakend.scholarshipManage.service.UserService;
import com.drakend.scholarshipManage.utils.ModelMapperCustom;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	private final UserService userService;

	private final ModelMapperCustom mapperCustom;

	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDTO register(UserDTO userDTO) {
		if (userService.isExistByEmail(userDTO.getEmail())) {
			throw new ResourceWasExistException("Email user was exist");
		}
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = mapperCustom.map(userDTO, User.class);
		return mapperCustom.map(userService.save(user), UserDTO.class);
	}

}
