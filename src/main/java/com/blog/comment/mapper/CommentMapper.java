package com.blog.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.blog.comment.domain.Comment;

@Mapper
public interface CommentMapper {
	
	public void insertComment(
			@Param("userId") int userId,
			@Param("postId") int postId,
			@Param("content") String content);
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public void deleteCommentById(int id);
}
