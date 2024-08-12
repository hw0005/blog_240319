package com.blog.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.post.mapper.PostMapper;

@Service
public class PostImageBO {
	@Autowired
	private PostMapper postMapper;
	
	public void addImage(int userId, int postId, List<String> imageUrl) {
		postMapper.insertImage(userId, postId, imageUrl);
	}
	
	
}
