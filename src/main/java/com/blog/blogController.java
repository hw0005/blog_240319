package com.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@RequestMapping("/main-blog-view")
	public String mainBlogView() {
		return "blog/mainBlog";
	}
	
	@GetMapping("/my-blog-view")
	public String myBlogView() {
		return "blog/myBlog";
	}
	
	@GetMapping("/following-blog-view")
	public String followingBlogView() {
		return "blog/followingBlog";
	}
}
