package com.blog.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	// 글생성 API
	// input: subject, content, file
	@PostMapping("/create")
	public Map<String, Object> postCreate(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file,
			HttpSession session) {
		
		
		
		
		Map<String, Object> result = new HashMap<>();
		
		return result;
	}
}
