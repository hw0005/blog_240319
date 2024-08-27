package com.blog.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.post.domain.PostImage;
import com.blog.post.mapper.PostMapper;

@Service
public class PostImageBO {
	@Autowired
	private PostMapper postMapper;
	
	// 이미지 선택
	public List<PostImage> selectImageUrlListByPostId(int postId) {
		return postMapper.selectImageUrlListByPostId(postId);
	}
	public PostImage selectImageUrlByPostId (int postId) {
		return postMapper.selectImageUrlByPostId(postId);
	}
	
	// 이미지 추가
	public void addImage(int userId, int postId, String imageUrl) {
		postMapper.insertImage(userId, postId, imageUrl);
	}
	
	
	//이미지 삭제
	public void deleteImageUrlListByPostId(int postId) {
		postMapper.deleteImageUrlListByPostId(postId);
	}
	
}
