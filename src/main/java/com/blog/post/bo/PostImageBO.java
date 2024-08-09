package com.blog.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.post.mapper.PostMapper;

@Service
public class PostImageBO {
	@Autowired
	private PostMapper postMapper;
	
	public void addPostImage(int postId, String imageUrl) {
		postMapper.insertPostImage(postId, imageUrl);
	}
	
	
}
