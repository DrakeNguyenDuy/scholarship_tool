package com.matohela.scholarshipManage.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role_permission")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
