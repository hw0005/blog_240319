package com.blog.user;

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
	
	/**
	 * 아이디 중복확인 API
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		
		// db 조회
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			result.put("code", 200);
			result.put("is_duplicated_id", true);
		} else {
			result.put("is_duplicated_id", false);
		}
		return result;
	}
	
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		
		// 해싱된 비밀번호
		String hashedPassword = EncryptUtils.encrypt(password);
		
		// db insert
		UserEntity user = userBO.addUser(loginId, hashedPassword, name, email);
		
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "회원가입에 실패했습니다.");
		}
		return result;
	}
	
	
	//로그인 API
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session) {
		
		// 해싱된 비밀번호
		String hashedPassword = EncryptUtils.encrypt(password);
		
		// db select
		UserEntity user = userBO.getUserEntityByLoginIdPassword(loginId, hashedPassword);
		
		Map<String, Object> result = new HashMap<>();
		// 로그인 처리 및 응답값
		if (user != null) { // 성공
			// session에 사용자 정보 담기
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 400);
			result.put("error_message", "존재하지 않은 사용자입니다.");
		}
		return result;
	}
	
	
}
