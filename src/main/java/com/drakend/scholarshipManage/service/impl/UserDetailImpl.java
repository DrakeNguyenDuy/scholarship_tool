package com.drakend.scholarshipManage.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.drakend.scholarshipManage.entity.GroupRole;
import com.drakend.scholarshipManage.entity.RolePermission;
import com.drakend.scholarshipManage.entity.User;
import com.drakend.scholarshipManage.entity.UserGroup;
import com.drakend.scholarshipManage.enums.StatusActive;
import com.drakend.scholarshipManage.repository.UserRepository;

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
		Set<UserGroup> userGroupSet = user.getUserGroups();
		Set<GroupRole> groupRoles = new HashSet<>();
		Set<RolePermission> rolePermissions = new HashSet<>();
		Set<String> permissions = new HashSet<String>();
		for (UserGroup userGroup : userGroupSet) {
			groupRoles.addAll(userGroup.getGroup().getGroupRoles());
		}
		for (GroupRole groupRole : groupRoles) {
			rolePermissions.addAll(groupRole.getRole().getRolePermissions());
		}
		for (RolePermission rolePermission : rolePermissions) {
			permissions.add(rolePermission.getPermission().getName());
		}
		
		Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
		for (String permission : permissions) {
			simpleGrantedAuthorities.add(new SimpleGrantedAuthority(permission));
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
