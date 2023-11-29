package com.drakend.scholarshipManage.facade.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.drakend.scholarshipManage.config.JwtTokenProvider;
import com.drakend.scholarshipManage.dto.LoginResponse;
import com.drakend.scholarshipManage.dto.UserDTO;
import com.drakend.scholarshipManage.entity.AuditSection;
import com.drakend.scholarshipManage.entity.User;
import com.drakend.scholarshipManage.enums.StatusActive;
import com.drakend.scholarshipManage.exception.ResourceWasExistException;
import com.drakend.scholarshipManage.facade.AuthenticationFacade;
import com.drakend.scholarshipManage.service.UserService;
import com.drakend.scholarshipManage.service.impl.UserDetailImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	private final UserService userService;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	private final JwtTokenProvider jwtTokenProvider;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO register(UserDTO userDTO) {
		if (userService.isExistByEmail(userDTO.getEmail())) {
			throw new ResourceWasExistException("Email user was exist");
		}
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = modelMapper.map(userDTO, User.class);
		user.setAuditSection(AuditSection.builder().dateCreated(new Date()).createBy("").modifiedBy("")
				.dateModified(new Date()).status(StatusActive.ACTIVE).build());
		return modelMapper.map(userService.save(user), UserDTO.class);
	}

	@Override
	public LoginResponse login(UserDTO userDTO) {
		String email = userDTO.getEmail();
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, userDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtTokenProvider.gennerateToken((UserDetailImpl) authentication.getPrincipal());
		User user = userService.findByEmail(userDTO.getEmail());
		return LoginResponse.builder().accessToken(jwt).permissions(
				authentication.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toSet()))
				.userId(user.getId()).build();
	}

}
