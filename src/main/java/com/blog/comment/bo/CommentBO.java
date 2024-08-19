package com.blog.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.comment.domain.Comment;
import com.blog.comment.domain.CommentView;
import com.blog.comment.mapper.CommentMapper;
import com.blog.user.bo.UserBO;
import com.blog.user.entity.UserEntity;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	public void addComment(int userId, int postId, String content) {
		commentMapper.insertComment(userId, postId, content);
	}
	
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 댓글들 가져옴
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 1개
			commentView.setComment(comment);
			
			// 댓글쓰니
			UserEntity user = userBO.getUserEntityById(comment.getUserId());
			commentView.setUser(user);
			
			
			commentViewList.add(commentView);
		}
		return commentViewList;
	}
	
}
