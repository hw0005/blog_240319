package com.blog.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {
	
	// html 나오게 하는 테스트 POST
	@GetMapping("/default")
	public String defa() {
		return "layout/defaultLayout";
	}
	
}
