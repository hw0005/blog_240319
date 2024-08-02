package com.blog.user;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.common.EncryptUtils;
import com.blog.user.bo.UserBO;
import com.blog.user.entity.UserEntity;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class userRestController {
	
	@Autowired
	private UserBO userBO;
	
	// 아이디 중복확인 API
	public Map<String, Object> isDuplicatedId() {
		
		Map<String, Object> result = new HashMap<>();
		
		return result;
	}
	
	
	
	//로그인 API
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session) {
		
		// 해싱된 비밀번호
		String hashedPassword = null;
		try {
			hashedPassword = EncryptUtils.createSalt(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// db select
		UserEntity user = userBO.getUserEntityByLoginIdPassword(loginId, hashedPassword);
		
		Map<String, Object> result = new HashMap<>();
		// 로그인 처리 및 응답값
		if (user != null) { // 성공
			// session에 사용자 정보 담기
			session.setAttribute("userId", user.getId());
			
			
		}
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
}
