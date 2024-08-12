package com.blog.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {
	public List<Map<String, Object>> selectPostListTest();
	
	public void insertImage(
			@Param("userId") int userId,
			@Param("postId") int postId,
			@Param("imageUrl") List<String> imageUrl);
	
	
	
}
