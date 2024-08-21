package com.blog.follow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/follow")
public class followController {
	
	@RequestMapping("/request-view")
	public String followRequestView() {
		
		return "follow/request";
	}
	
	
	
}
