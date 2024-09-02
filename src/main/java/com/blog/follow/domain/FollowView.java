package com.blog.follow.domain;

import java.util.List;

import com.blog.post.domain.PostImage;
import com.blog.post.entity.PostEntity;
import com.blog.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FollowView {
	private Follow follow;
	
	private UserEntity user;
	
	private int followCount;
	
	private List<PostEntity> post;
	
	// 이미지 정보
	private List<PostImage> postImage;
}
