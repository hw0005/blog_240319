package com.blog.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.blog.post.domain.PostImage;

@Mapper
public interface PostMapper {
	public List<Map<String, Object>> selectPostListTest();
	
	// 이미지 추가
	public void insertImage(
			@Param("userId") int userId,
			@Param("postId") int postId,
			@Param("imageUrl") String imageUrl);
	
	// 이미지 선택
	public List<PostImage> selectImageUrlListByPostId(int postId);
	
	public PostImage selectImageUrlByPostId(int postId);
	
	// 이미지 삭제
	public void deleteImageUrlListByPostId(int postId);
	
	public List<PostImage> selectImageUrlListById(int postId);
	
}
