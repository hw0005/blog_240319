package com.blog.follow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.blog.follow.domain.Follow;

@Mapper
public interface FollowMapper {
	
	//follow insert -> 팔로우 요청 대입
	public void insertFollower(
			@Param("followerUserLoginId") String followerUserLoginId,
			@Param("followingUserLoginId") String followingUserLoginId,
			@Param("followStatus") String followStatus);
	
	//follow update -> 팔로우 승인
	public void updateFollower(
			@Param("followerUserLoginId") String followerUserLoginId,
			@Param("followingUserLoginId") String followingUserLoginId,
			@Param("followStatus") String followStatus);
	
	
	//follow delete -> 팔로우 거절
	public void deleteFollower(
			@Param("followerUserLoginId") String followerUserLoginId,
			@Param("followingUserLoginId") String followingUserLoginId);
	
	public List<Follow> selectFollowerListCountById(
			@Param("id") int id, 
			@Param("followStatus") String followStatus);
	
	public List<Follow> selectFollowListByLoginId(String followerUserLoginId);

	public Follow selectFollower(
			@Param("follower") String follower,
			@Param("following") String following);
	
}
