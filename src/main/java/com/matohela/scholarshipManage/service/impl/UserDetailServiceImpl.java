package com.matohela.scholarshipManage.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.matohela.scholarshipManage.entity.User;
import com.matohela.scholarshipManage.exception.ResourceNotFoundException;
import com.matohela.scholarshipManage.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	/**
	 * <p>
	 * This method will help load user by user name (email)
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param userName
	 * @return {@link UserDetails}
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("Not found user by email"));
		return new UserDetailImpl(user, userRepository);
	}

}
