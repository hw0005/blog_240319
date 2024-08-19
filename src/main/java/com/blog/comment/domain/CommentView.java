package com.blog.comment.domain;

import com.blog.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentView {
	private Comment comment;
	
	private UserEntity user;
	
}
