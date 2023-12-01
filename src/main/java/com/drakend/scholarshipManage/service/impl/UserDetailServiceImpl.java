package com.drakend.scholarshipManage.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.drakend.scholarshipManage.entity.User;
import com.drakend.scholarshipManage.exception.ResourceNotFoundException;
import com.drakend.scholarshipManage.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("Not found user by email"));
		return new UserDetailImpl(user);
	}

}
