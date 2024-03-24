package com.matohela.scholarshipManage.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "permission")
@Getter
@Setter
public class Permission extends BaseEntity {

	@OneToMany(mappedBy = "permission")
	private Set<RolePermission> rolePermissions;
	
}
