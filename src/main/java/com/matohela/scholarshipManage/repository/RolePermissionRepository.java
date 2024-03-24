package com.matohela.scholarshipManage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.matohela.scholarshipManage.entity.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, String> {

	@Modifying
	List<RolePermission> deleteByRoleId(String id);
	
	@Modifying
	List<RolePermission> deleteByPermissionId(String id);
}
