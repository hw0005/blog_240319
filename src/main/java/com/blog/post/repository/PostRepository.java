package com.blog.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
	
	public List<PostEntity> findByOrderByIdDesc();
	
	public List<PostEntity> findByOrderByUserIdDesc();

	public List<PostEntity> findByUserIdOrderByUserIdDesc(int userId);

}
