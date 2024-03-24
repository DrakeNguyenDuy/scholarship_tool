package com.matohela.scholarshipManage.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.matohela.scholarshipManage.entity.GroupRole;
import com.matohela.scholarshipManage.entity.Permission;
import com.matohela.scholarshipManage.entity.RolePermission;
import com.matohela.scholarshipManage.entity.User;
import com.matohela.scholarshipManage.entity.UserGroup;
import com.matohela.scholarshipManage.enums.StatusActive;
import com.matohela.scholarshipManage.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
@RequiredArgsConstructor
public class UserDetailImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private final UserRepository userRepository;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Permission> permissions = userRepository.findPermissions(user.getId());
		
		Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
		for (Permission permission : permissions) {
			simpleGrantedAuthorities.add(new SimpleGrantedAuthority(permission.getDescription()));
		}
		return simpleGrantedAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user != null && user.getAuditSection().getStatus() == StatusActive.ACTIVE;
	}
}
