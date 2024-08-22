package com.blog.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.blog.bo.BlogBO;
import com.blog.blog.domain.CardView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private BlogBO blogBO;
	
	@RequestMapping("/user-view")
	public String searchUserView(Model model,
			HttpSession session) {
			//  로그인 여부 확인
			Integer userId = (Integer)session.getAttribute("userId");
			
			List<CardView> cardViewList = blogBO.generateCardViewList(userId);
			model.addAttribute("cardViewList", cardViewList);
			
		
		return "search/searchUser";
	}
	
}
