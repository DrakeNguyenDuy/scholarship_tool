package com.drakend.scholarshipManage.service;

import com.drakend.scholarshipManage.entity.User;

public interface UserService {
	User save(User user);

	boolean isExistByEmail(String email);
}
