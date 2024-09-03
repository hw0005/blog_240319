package com.blog.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.blog.comment.mapper.CommentMapper;
import com.blog.common.FileManagerService;
import com.blog.like.mapper.LikeMapper;
import com.blog.post.domain.PostImage;
import com.blog.post.entity.PostEntity;
import com.blog.post.mapper.PostMapper;
import com.blog.post.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostBO {
	@Autowired
	private FileManagerService filemanagerService;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostImageBO postImageBO;
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private LikeMapper likeMapper;
	
	public List<PostEntity> getPostEntityList() {
		return postRepository.findByOrderByIdDesc();
	}
	
	public List<PostEntity> getPostEntityListByUserId(int userId) {
		return postRepository.findByUserIdOrderByUserIdDesc(userId);
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
	
	@Transactional
	public void deletePostByPostId(int postId, int userId) {
		
		// 기존 글 postId로 가져오기, 글 삭제
		PostEntity post = postRepository.findById(postId).orElse(null);
		
		if (post == null) {
			log.error("[글 삭제]: postId:{}, userId:{}", postId, userId);
		}
		postRepository.delete(post);
		
		// 기존 이미지 postId로 가져오기, 이미지들 삭제
		List<PostImage> imageUrl = postImageBO.selectImageUrlListByPostId(postId);
		for (int i = 0; i < imageUrl.size(); i++) {
			filemanagerService.deleteFile(imageUrl.get(i).getImageUrl());
		}
		postImageBO.deleteImageUrlListByPostId(postId);
		
		// 댓글 삭제
		commentMapper.deleteCommentByPostIdAndUserId(postId, userId);
		
		// 좋아요 삭제
		likeMapper.deleteLikeByUserIdPostId(userId, postId);
	}
}
