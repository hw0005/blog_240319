package com.blog.search;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.search.bo.SearchBO;
import com.blog.user.entity.UserEntity;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/search")
public class SearchRestController {
	
	@Autowired
	private SearchBO searchBO;
	
	// 유저 검색 API
	@PostMapping("/user")
	public Map<String, Object> searchUser(
			@RequestParam("loginId") String loginId, 
			HttpSession session) {
		
		// 로그인 여부
		Integer userId = (Integer)session.getAttribute("userId");
		
		
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인을 하세요");
		}
		
		// db (user)조회
		UserEntity name = searchBO.getUserEntityByLoginId(loginId);
		
		//응답값: 유저 존재할 시  성공, 없을 시 없다고 할 것
		if (name != null) {
			result.put("code", 200);
			result.put("result", "유저 존재");
		}
		if (name == null) {
			result.put("code", 403);
			result.put("non_user", "유저를 찾지 못했습니다.");
		}
		
		return result;
	}
	
	
	
}
