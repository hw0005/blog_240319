package com.blog.follow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.follow.bo.FollowBO;
import com.blog.follow.domain.FollowView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/follow")
public class FollowController {
	@Autowired
	private FollowBO followBO;
	
	
	@RequestMapping("/request-view")
	public String followRequestView(Model model,
			HttpSession session) {
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		List<FollowView> followViewList = followBO.generateFollowViewListByLoginId(userLoginId);
		model.addAttribute("followViewList" , followViewList);
		
		return "follow/request";
	}
	
	
	
}
