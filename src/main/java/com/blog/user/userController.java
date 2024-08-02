package com.blog.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class userController {
	
	// 로그인 화면
	@GetMapping("/sign-in-view")
	public String signInView() {
		
		return "user/signIn";
	}
	
	// 회원가입 화면
	@GetMapping("/sign-up-view")
	public String signUpView() {
		return "user/signUp";
	}
	
	
}
