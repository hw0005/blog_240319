package com.blog.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.post.domain.PostImage;
import com.blog.post.mapper.PostMapper;

@Service
public class PostImageBO {
	@Autowired
	private PostMapper postMapper;
	
	public PostImage selectImageUrlByPostId(int postId) {
		return postMapper.selectImageUrlByPostId(postId);
	}
	
	public void addImage(int userId, int postId, String imageUrl) {
		postMapper.insertImage(userId, postId, imageUrl);
	}
	

	
}
