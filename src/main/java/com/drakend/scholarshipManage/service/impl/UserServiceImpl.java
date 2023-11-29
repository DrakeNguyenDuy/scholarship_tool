package com.drakend.scholarshipManage.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.drakend.scholarshipManage.entity.User;
import com.drakend.scholarshipManage.exception.ResourceNotFoundException;
import com.drakend.scholarshipManage.repository.UserRepository;
import com.drakend.scholarshipManage.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean isExistByEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Can not find user by email"));
	}

}
