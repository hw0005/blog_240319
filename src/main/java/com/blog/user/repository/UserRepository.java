package com.blog.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	// JPQL
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
}
