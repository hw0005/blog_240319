package com.blog.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	/**
	 * 글 생성 API
	 * @param subject
	 * @param content
	 * @param file
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> postCreate(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam("file") List<MultipartFile> file,
			HttpSession session) {
		// 글쓴이 번호를 session에서 꺼낸다. 비로그인시 에러
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		if (userId != null) {
			result.put("code", 403);
			result.put("result", "로그인이 되지 않습니다.");
		}
		
		// DB INSERT
		postBO.addPost(userId, userLoginId, subject, content, file);
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	// 글 삭제 API
	@DeleteMapping("/delete")
	public Map<String, Object> postDelete(
			@RequestParam("postId") int postId,
			HttpSession session) {
		int userId = (int)session.getAttribute("userId");
		
		// db delete
		postBO.deletePostByPostId(postId, userId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	
}
