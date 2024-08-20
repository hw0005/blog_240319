package com.blog.like.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
	public int selectLikeCountByUserIdOrPostId(
			@Param("userId") Integer userId,
			@Param("postId") int postId);
	
	public int deleteLikeByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public int insertLike(
			@Param("userId") int userId,
			@Param("postId") int postId);
}
