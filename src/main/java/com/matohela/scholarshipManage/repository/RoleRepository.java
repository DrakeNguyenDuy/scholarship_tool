package com.matohela.scholarshipManage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matohela.scholarshipManage.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	Optional<Role> findByName(String name);
}
