package com.blog.blog.domain;

import java.util.List;

import com.blog.comment.domain.CommentView;
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
	
	// 댓글 달기
	private List<CommentView> commentList;
	
	// 댓글 갯수
	
	
	// 좋아요 갯수
	private int likeCount;
	
	// 좋아요 여부 채워짐
	private boolean filledLike; //채워져있으면 true 아니면 false

	// 팔로우 요청
	
	
	
	
}
