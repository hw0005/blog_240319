package com.blog.follow.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Follow {
	private int id;
	private String followerUserLoginId;
	private String followingUserLoginId;
	private LocalDateTime createdAt;
}
