package com.blog.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.blog.bo.BlogBO;
import com.blog.blog.domain.CardView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogBO blogBO;
	
	
	@RequestMapping("/main-blog-view")
	public String mainBlogView() {
		return "blog/mainBlog";
	}
	
	@GetMapping("/my-blog-view")
	public String myBlogView(
			Model model, 
			HttpSession session) {
		//  로그인 여부 확인
		Integer userId = (Integer)session.getAttribute("userId");
		
		
		List<CardView> cardViewList = blogBO.generateCardViewList(userId);
		model.addAttribute("cardViewList", cardViewList);
		
		
		return "blog/myBlog";
	}
	
	@GetMapping("/following-blog-view")
	public String followingBlogView() {
		return "blog/followingBlog";
	}
}
