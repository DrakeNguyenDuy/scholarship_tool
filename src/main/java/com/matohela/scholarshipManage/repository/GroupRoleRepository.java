package com.matohela.scholarshipManage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.matohela.scholarshipManage.entity.GroupRole;

public interface GroupRoleRepository extends JpaRepository<GroupRole, String> {

	@Modifying
	List<GroupRole> deleteByRoleId(String id);
}
