package com.blog.search.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.user.bo.UserBO;
import com.blog.user.entity.UserEntity;

@Service
public class SearchBO {
	@Autowired
	private UserBO userBO;
	
	// 유저 검색
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userBO.getUserEntityByLoginId(loginId);
	}
	
	
}
