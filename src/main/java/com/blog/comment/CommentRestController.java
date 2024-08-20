package com.blog.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	@Autowired
	private CommentBO commentBO;
	
	/**
	 * 댓글 추가 API
	 * @param postId
	 * @param content
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> comment(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session
 			){
		// 로그인 여부
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId == null) {
			Map<String, Object> result = new HashMap<>();
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요");
			return result;
		}
		
		// db insert 
		commentBO.addComment(userId, postId, content);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("commentId") int commentId,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인되지 않았습니다.");
		}
		
		// DB delete
		commentBO.deleteCommentById(commentId);
		result.put("code", 200);
		result.put("result", "성공");
		
		
		return result;
	}
	
	
	
	
}
