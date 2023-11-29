package com.drakend.scholarshipManage.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "role_permission")
@Getter
public class RolePermission {
	
	@EmbeddedId
	private RolePermissionKey rolePermissionKey;

	@ManyToOne
	@MapsId("roleId")
	@JoinColumn(name = "role_id")
	private Role role;

	@ManyToOne
	@MapsId("permissionId")
	@JoinColumn(name = "permission_id")
	private Permission permission;
}
