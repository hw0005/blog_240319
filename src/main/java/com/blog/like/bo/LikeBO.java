package com.blog.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.like.mapper.LikeMapper;

@Service
public class LikeBO {
	
	@Autowired
	private LikeMapper likeMapper;
	
	public int likeToggle(int userId, int postId) {
		// 조회
		int count = likeMapper.selectLikeCountByUserIdOrPostId(userId, postId);
		
		// 여부 => 삭제 or 추가
		if (count > 0) { // 1개일 때
			return likeMapper.deleteLikeByUserIdPostId(userId, postId);
		} else {
			return likeMapper.insertLike(userId, postId);
		}
	}
	
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByUserIdOrPostId(null, postId);
	}
	
	public boolean filledLikeByPostIdUserId(int postId, Integer userId) {
		if (userId == null) {
			return false;
		}
		
		// 로그인이면 1. 행이 있으면(1) true   2. 없으면(0) false
		return likeMapper.selectLikeCountByUserIdOrPostId(userId, postId) == 1 ? true : false;
	}
	
	
}
