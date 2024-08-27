package com.blog.follow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/follow")
public class FollowRestController {
	
	
	
	//팔로우 요청 API
	@PostMapping("/request")
	public Map<String, Object> followRequest(
			@RequestParam("loginId") String followerUserLoginId,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");		
		
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인 되지 않았습니다. 로그인 하세요");
		}
		
		// DB SELECT, INSERT
		
		
		result.put("code", 200);
		result.put("result", "팔로우 성공");
		
		
		return result;
	}
}
