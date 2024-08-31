package com.blog.follow.domain;

import com.blog.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FollowView {
	private Follow follow;
	
	private UserEntity user;
	
	private int followCount;
}
