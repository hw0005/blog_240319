package com.blog.follow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.follow.bo.FollowBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/follow")
public class FollowRestController {
	
	@Autowired
	private FollowBO followBO;
	
	
	//팔로우 요청 API // insert
	@PostMapping("/request")
	public Map<String, Object> followRequest(
			@RequestParam("followingUserLoginId") String followingUserLoginId,// 팔로우 할 아이디
			@RequestParam("followStatus") String followStatus,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");		
		String followerUserLoginId = (String)session.getAttribute("userLoginId"); // 내 아이디
		
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인 되지 않았습니다. 로그인 하세요");
		}
		
		// 팔로우 요청 대기 DB INSERT, 응답값
		followBO.addFollower(followerUserLoginId, followingUserLoginId, followStatus);
		result.put("code", 200);
		result.put("result", "팔로우 요청 성공");
		
		return result;
	}
	
	
	// 팔로우 승인 API // update && 스크립트에서 form태그를 써보자!
	@PostMapping("/permit")
	public Map<String, Object> followPermit(
			@RequestParam("followerUserLoginId") String followerUserLoginId, // 팔로우 요청받은 아이디
			@RequestParam("followStatus") String followStatus,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");		
		String followingUserLoginId = (String)session.getAttribute("userLoginId");
		
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인 되지 않았습니다. 로그인 하세요");
		}
		
		// 팔로우 요청 승인 DB UPDATE, 응답값
		followBO.updateFollower(followerUserLoginId, followingUserLoginId, followStatus);
		result.put("code", 200);
		result.put("result", "팔로우 승인 성공");
		
		return result;
	}
	
	// 팔로우 거절 API
	@PostMapping("/decline")
	public Map<String, Object> followDecline(
			@RequestParam("followerUserLoginId") String followerUserLoginId, // 팔로우 요청받은 아이디로 지울 거임
			HttpSession session) {
		
		String userLoginId = (String)session.getAttribute("userLoginId"); // 요청 수락거절 화면에서 로그인 된 아이디
		
		
		Map<String, Object> result = new HashMap<>();
		if (userLoginId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인 되지 않았습니다. 로그인 하십시오");
		}
		
		// 팔로우 거절DB DELETE & 응답값
		followBO.deleteFollower(followerUserLoginId, userLoginId);
		
		result.put("code", 200);
		result.put("result", "팔로우 요청 거절완료");
		
		return result;
	}
	
	
	
}
