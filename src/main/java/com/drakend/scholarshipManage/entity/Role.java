package com.drakend.scholarshipManage.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "role")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity{
	
	@OneToMany(mappedBy = "role")
	private Set<RolePermission> rolePermissions;
	
	@OneToMany(mappedBy = "role")
	private Set<GroupRole> groupRoles;
}
