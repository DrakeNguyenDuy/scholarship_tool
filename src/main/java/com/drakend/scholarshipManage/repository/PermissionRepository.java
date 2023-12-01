package com.drakend.scholarshipManage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drakend.scholarshipManage.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String> {

}
