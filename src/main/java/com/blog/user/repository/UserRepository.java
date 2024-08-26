package com.blog.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	// JPQL 유저조회
	public UserEntity findByLoginId(String loginId);

	// JPQL 로그인
	public UserEntity findByLoginIdAndPassword(String loginId, String password);

	// JPQL 유저 검색 조회
	public List<UserEntity> findByLoginIdStartingWithOrderByIdDesc(String loginId);
	

	

}
