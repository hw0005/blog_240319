package com.blog.post.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PostImage {
	private int id;
	private int userId;
	private int postId;
	private String imageUrl;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
