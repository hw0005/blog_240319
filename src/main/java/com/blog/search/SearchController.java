package com.blog.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.follow.bo.FollowBO;
import com.blog.follow.domain.FollowView;
import com.blog.search.bo.SearchBO;
import com.blog.search.domain.SearchUserView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchBO searchBO;
	
	@Autowired
	private FollowBO followBO;
	
	@GetMapping("/user-view")
	public String searchUserView(@RequestParam(required = false) String keyword
								, Model model
								, HttpSession session) {
	
			String userLoginId = (String)session.getAttribute("userLoginId");
		
			
			if (ObjectUtils.isEmpty(keyword) == false) {
				
				List<SearchUserView> searchUserViewList = searchBO.generateSearchUserView(keyword);
				model.addAttribute("searchUserViewList", searchUserViewList);
				
//				List<FollowView> followViewList = followBO.generateFollowViewListByLoginId(userLoginId);
//				model.addAttribute("followViewList" , followViewList);
			}
			
		
		return "search/searchUser";
	}
	
	//@PostMapping("/result-view")
}
