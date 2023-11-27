package com.drakend.scholarshipManage.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity(name = "permission")
@Getter
public class Permission extends BaseEntity {

	@OneToMany(mappedBy = "permission")
	private Set<RolePermission> rolePermissions;
}
