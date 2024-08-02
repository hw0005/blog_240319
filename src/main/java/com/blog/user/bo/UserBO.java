package com.blog.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.user.entity.UserEntity;
import com.blog.user.repository.UserRepository;

@Service
public class UserBO {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
	
}
