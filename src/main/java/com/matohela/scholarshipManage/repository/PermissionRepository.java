package com.matohela.scholarshipManage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.matohela.scholarshipManage.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String>, JpaSpecificationExecutor<Permission> {

	Optional<Permission> findByName(String name);

}
