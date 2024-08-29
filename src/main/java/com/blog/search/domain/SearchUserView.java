package com.blog.search.domain;

import java.util.List;

import com.blog.follow.domain.Follow;
import com.blog.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class SearchUserView {
	// 유저 조회 및 검색
	private UserEntity user;
	
	private List<Follow> followList;
}
