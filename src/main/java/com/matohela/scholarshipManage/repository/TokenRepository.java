package com.matohela.scholarshipManage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matohela.scholarshipManage.entity.Token;

/**
 * 
 */
public interface TokenRepository extends JpaRepository<Token, String> {

	public Optional<Token> findByUserId(String userId);
}
