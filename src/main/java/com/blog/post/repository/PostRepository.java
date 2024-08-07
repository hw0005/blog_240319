package com.blog.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.post.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
	

}
