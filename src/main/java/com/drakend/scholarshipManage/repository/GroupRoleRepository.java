package com.drakend.scholarshipManage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.drakend.scholarshipManage.entity.GroupRole;
import com.drakend.scholarshipManage.entity.RolePermission;

public interface GroupRoleRepository extends JpaRepository<GroupRole, String> {

	@Modifying
	List<GroupRole> deleteByRoleId(String id);
}
