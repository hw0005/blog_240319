package com.blog.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	// JPQL
	public UserEntity findByLoginId(String loginId);
	
	// JPQL
	public UserEntity findByLoginIdAndPassword(String loginId, String password);

	public List<UserEntity> findByOrderByLoginIdDesc();
	
	
}
