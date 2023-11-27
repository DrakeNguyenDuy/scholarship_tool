package com.drakend.scholarshipManage.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "group_role")
@Getter
public class GroupRole {
	
	@EmbeddedId
	private GroupRoleKey groupRoleKey;

	@ManyToOne
	@MapsId("groupId")
	@JoinColumn(name = "group_id")
	private Group group;

	@ManyToOne
	@MapsId("roleId")
	@JoinColumn(name = "role_id")
	private Role role;
}
