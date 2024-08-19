package com.blog.comment.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Comment {
	private int id;
	private int userId;
	private int postId;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
