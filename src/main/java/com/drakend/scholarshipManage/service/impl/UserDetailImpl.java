package com.drakend.scholarshipManage.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.drakend.scholarshipManage.entity.GroupRole;
import com.drakend.scholarshipManage.entity.User;
import com.drakend.scholarshipManage.entity.UserGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDetailImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<UserGroup> userGroupSet = user.getUserGroups();
		Set<String> roles = new HashSet<>();
		for (UserGroup userGroup : userGroupSet) {
			Set<GroupRole> groupRoles = userGroup.getGroup().getGroupRoles();
			for (GroupRole groupRole : groupRoles) {
				roles.add(groupRole.getRole().getName());
			}
		}
		Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		for (String role : roles) {
			simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
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
		return true;
	}
}
