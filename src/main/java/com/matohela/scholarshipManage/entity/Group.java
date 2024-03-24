package com.matohela.scholarshipManage.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name="`group`")
@Getter
public class Group extends BaseEntity {

	@OneToMany(mappedBy = "group")
	private Set<GroupRole> groupRoles;
	
	@OneToMany(mappedBy = "group")
	private Set<UserGroup> userGroups;
}
