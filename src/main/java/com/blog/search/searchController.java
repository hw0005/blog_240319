package com.blog.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class searchController {
	
	@RequestMapping("/user-view")
	public String searchUserView() {
		
		return "search/searchUser";
	}
	
}
