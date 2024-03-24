package com.matohela.scholarshipManage.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matohela.scholarshipManage.entity.Permission;
import com.matohela.scholarshipManage.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * @param email
	 * @return
	 */
	Optional<User> findByEmail(String email);

//	@Query("SELECT u FROM User u JOIN FETCH u.userGroups ug JOIN FETCH"
//	+ " ug.group g JOIN FETCH g.groupRoles gl JOIN FETCH gl.role"
//	+ " r JOIN FETCH r.rolePermissions rp JOIN FETCH rp.permission WHERE u.email =:email ")
	@Query("SELECT p FROM User u JOIN u.userGroups ug JOIN ug.group g "
			+ "JOIN g.groupRoles gr JOIN gr.role r JOIN r.rolePermissions rp "
			+ "JOIN rp.permission p WHERE u.id = :id")
	Set<Permission> findPermissions(String id);
}
