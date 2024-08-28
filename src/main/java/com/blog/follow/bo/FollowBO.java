package com.blog.follow.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.follow.mapper.FollowMapper;

@Service
public class FollowBO {
	@Autowired
	private FollowMapper followMapper;
	
	public void addFollower(String followerUserLoginId, String followingUserLoginId) {
		followMapper.insertFollower(followerUserLoginId, followingUserLoginId);
	}
}
