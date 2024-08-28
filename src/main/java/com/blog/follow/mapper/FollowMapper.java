package com.blog.follow.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowMapper {
	
	public void insertFollower(
			@Param("followerUserLoginId") String followerUserLoginId,
			@Param("followingUserLoginId") String followingUserLoginId);
}
