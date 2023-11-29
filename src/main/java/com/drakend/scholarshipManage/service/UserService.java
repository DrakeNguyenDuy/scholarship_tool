package com.drakend.scholarshipManage.service;

import java.util.Optional;

import com.drakend.scholarshipManage.entity.User;

public interface UserService {
	User save(User user);

	boolean isExistByEmail(String email);
	
	User findByEmail(String email);
	
}
