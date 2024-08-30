package com.blog.search.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
	public List<SearchUserView> generateSearchUserView (String keyword, String loginId) {
		
		List<SearchUserView> searchUserList = new ArrayList<>();
		
		// 검색한 유저 가져오기
		// -> like 문법 string 시작하게 하는 걸로 aa면 aabb도 검색
		List<UserEntity> userList = userBO.getUserEntityListByLoginId(keyword);
		
		// 팔로우 리스트 유저 가져오기(followinUserLoginId)로
		
		// 반복문 돌리기
		for (UserEntity user : userList) {
			
			SearchUserView search = new SearchUserView();
			
			// 유저들 가져오기
			search.setUser(user);
			// 유저 가져온 것 중에서(돌고 있는 거에서) followerUserLoginId가 있냐? => 가져올까?
			// 팔로우 요청한 거 담기
			
			//상대방이 날 팔로우 하고 있는지
			String userFound = user.getLoginId(); //내가 검색한 사람의 로그인 아이디
			Follow follow = followBO.getFollower(userFound, loginId);
			if(ObjectUtils.isEmpty(follow) == false) {
				search.setFollowed(true);
			}
			
			//내가 팔로잉을 하고 있는지
			Follow followingUserFound = followBO.getFollower(loginId, userFound);
			if(ObjectUtils.isEmpty(followingUserFound) == false) {
				search.setFollowing(true);
				
				String statusFollowing = followingUserFound.getFollowStatus();			
				search.setFollowingApproved(statusFollowing.equals("팔로우 승인") ? true : false);
			}
			
			// 리스트에 넣기
			searchUserList.add(search);
		}
		return searchUserList;
	}
}
