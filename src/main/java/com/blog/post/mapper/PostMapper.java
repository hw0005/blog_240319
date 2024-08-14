package com.blog.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.blog.post.domain.PostImage;

@Mapper
public interface PostMapper {
	public List<Map<String, Object>> selectPostListTest();
	
	public void insertImage(
			@Param("userId") int userId,
			@Param("postId") int postId,
			@Param("imageUrl") String imageUrl);
	
	public List<PostImage> selectImageUrlListByPostId(int postId);
	
	
}
