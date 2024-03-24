package com.matohela.scholarshipManage.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matohela.scholarshipManage.entity.User;
import com.matohela.scholarshipManage.exception.ResourceNotFoundException;
import com.matohela.scholarshipManage.repository.UserRepository;
import com.matohela.scholarshipManage.service.UserService;

import lombok.AllArgsConstructor;

/**
 * <p>
 * User service implement
 * </p>
 * 
 * @author NguyenDuyLong2810
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	/**
	 * <p>
	 * Save user entity (Used to creating or updating)
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param user
	 * @return {@link User}
	 */
	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	/**
	 * <p>
	 * Check a email is exist
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param email
	 * @return boolean
	 */
	@Override
	public boolean isExistByEmail(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	/**
	 * <p>
	 * Find user by email
	 * </p>
	 * 
	 * @author NguyenDuyLong2810
	 * @param email
	 * @return {@link User}
	 * @throws ResourceNotFoundException
	 */
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Can not find user by email"));
	}

}
