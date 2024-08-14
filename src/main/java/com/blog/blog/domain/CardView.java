package com.blog.blog.domain;

import java.util.List;

import com.blog.post.domain.PostImage;
import com.blog.post.entity.PostEntity;
import com.blog.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CardView {
	// 글
	private PostEntity post; // 글 가져옴.
	
	// 글쓴이 정보
	private UserEntity user; // 유저 정보 가져옴.
	
	// 이미지 정보
	private List<PostImage> postImage;
	
	// 이미지 URL가져옴.
	
	
	// 댓글 갯수
	
	// 좋아요 갯수
	
	// 좋아요 여부
	
	// 팔로우 요청
	
	
}
