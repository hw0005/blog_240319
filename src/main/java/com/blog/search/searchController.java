package com.blog.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.search.bo.SearchBO;
import com.blog.search.domain.SearchUserView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchBO searchBO;
	
	
	@RequestMapping("/user-view")
	public String searchUserView(Model model,
			HttpSession session) {
			//  로그인 여부 확인
			String userLoginId = (String)session.getAttribute("userLoginId");
			
			List<SearchUserView> searchUserViewList = searchBO.generateSearchUserView(userLoginId);
			model.addAttribute("searchUserViewList", searchUserViewList);
		
		return "search/searchUser";
	}
	
}
