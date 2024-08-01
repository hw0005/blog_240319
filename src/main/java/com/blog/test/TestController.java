package com.blog.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.post.mapper.PostMapper;
@Controller
public class TestController {
	@Autowired
	private PostMapper postMapper;
	
	
	// 테스트 1번
	@ResponseBody
	@GetMapping("/test1")
	public String test1() {
		return "hello World";
	}
	
	// 테스트 2번
	@ResponseBody
	@GetMapping("/test2")
	public Map<String, Object> test2() {
		Map<String, Object> result = new HashMap<>(); 
		result.put("code1", 100);
		result.put("code2", 200);
		return result;
	}
	
	// 테스트 3번
	@GetMapping("/test3")
	public String test3() {
		return "/test/test3";
	}
	
	// 테스트 4번
	@ResponseBody
	@GetMapping("/test4")
	public List<Map<String, Object>> test4() {
		return postMapper.selectPostListTest();
	}
	
	
}
