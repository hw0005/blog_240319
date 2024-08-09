package com.blog.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.common.FileManagerService;
import com.blog.post.entity.PostEntity;
import com.blog.post.repository.PostRepository;

@Service
public class PostBO {
	@Autowired
	private FileManagerService filemanagerService;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostImageBO postImageBO;
	
	
	public PostEntity addPost(int userId, String userLoginId, String subject, String content, List<MultipartFile> file) {
		
		postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.subject(subject)
				.content(content)
				.build());
		
		List<PostEntity> postList = postRepository.findById(id);
		
		List<String> imageUrl = filemanagerService.uploadFileList(file, userLoginId);
		postImageBO.addPostImage(postId, imageUrl);
	}
	
}
