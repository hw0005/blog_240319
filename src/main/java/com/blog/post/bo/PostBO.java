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
	
	public List<PostEntity> getPostEntityList() {
		return postRepository.findByOrderByIdDesc();
	}
	
	public void addPost(int userId, String userLoginId, String subject, String content, List<MultipartFile> file) {
		// postEntity에서 저장돼있는 그 글을 불러온다. 그 글에 있는 이미지를 저장함.
		PostEntity post = PostEntity.builder()
				.userId(userId)
				.subject(subject)
				.content(content)
				.build();
		
		post = postRepository.save(post);
		
		
		List<String> imagePath = null;
		if (file != null) {
			// 업로드 할 이미지가 있을 때(이미지가 null X라 이 경우밖에 없음)
			imagePath = filemanagerService.uploadFileList(file, userLoginId);
			
			// mybatis에서 반목문을 돌리거나 imagePath를 BO에서 반복문 돌리기 둘 중 하나 하면 됨.
			for (int i = 0; i < imagePath.size(); i++) {
				postImageBO.addImage(userId, post.getId(), imagePath.get(i));
			}
		}
	}
	
	
	
}
