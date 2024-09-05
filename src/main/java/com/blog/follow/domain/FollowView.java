package com.blog.follow.domain;

import java.util.List;

import com.blog.comment.domain.CommentView;
import com.blog.post.domain.PostImage;
import com.blog.post.entity.PostEntity;
import com.blog.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FollowView {
	private Follow follow;
	
	private UserEntity user;
	
	private int followCount;
	
	private PostEntity post;
	
	// 이미지 정보
	private List<PostImage> postImage;
	
	// 댓글 달기
	private List<CommentView> commentList;
		
	// 댓글 갯수
	private int commentCount;
		
	// 좋아요 갯수
	private int likeCount;
		
	// 좋아요 여부 채워짐
	private boolean filledLike; //채워져있으면 true 아니면 false
}
