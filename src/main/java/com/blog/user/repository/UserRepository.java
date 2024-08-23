package com.blog.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	//public List<UserEntity> findByLoginIdStartingWith(@Param("loginId") String loginId);
	
	
	// JPQL 유저조회
	public UserEntity findByLoginId(String loginId);

	// JPQL 로그인
	public UserEntity findByLoginIdAndPassword(String loginId, String password);

	// JPQL 글 게시, 유저 조회
	public List<UserEntity> findByOrderByLoginIdDesc();
	

	

}
