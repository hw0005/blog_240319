package com.blog.search.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.follow.bo.FollowBO;
import com.blog.follow.domain.Follow;
import com.blog.search.domain.SearchUserView;
import com.blog.user.bo.UserBO;
import com.blog.user.entity.UserEntity;

@Service
public class SearchBO {
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private FollowBO followBO;
	
	// HTML에 뿌리기 위해 SearchUserView에 저장 후 유저 조회 및 가져오는 거
	public List<SearchUserView> generateSearchUserView (String keyword) {
		
		List<SearchUserView> searchUserList = new ArrayList<>();
		
		
		// 검색한 유저 가져오기
		// -> like 문법 string 시작하게 하는 걸로 aa면 aabb도 검색
		List<UserEntity> userList = userBO.getUserEntityListByLoginId(keyword);
		
		// 반복문 돌리기
		for (UserEntity user : userList) {
			
			SearchUserView search = new SearchUserView();
			
			// 유저들 가져오기
			search.setUser(user);
			
			// 팔로우 요청한 거 담기
			List<Follow> followList = followBO.generateFollowListByLoginId(user.getLoginId());
			search.setFollowList(followList);
			
			
			// 리스트에 넣기
			searchUserList.add(search);
		}
		return searchUserList;
	}
}
