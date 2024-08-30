package com.blog.follow.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.follow.domain.Follow;
import com.blog.follow.domain.FollowView;
import com.blog.follow.mapper.FollowMapper;
import com.blog.user.bo.UserBO;
import com.blog.user.entity.UserEntity;

@Service
public class FollowBO {
	@Autowired
	private FollowMapper followMapper;
	
	@Autowired
	private UserBO userBO;
	
	
	// follow insert -> 팔로우 요청 대입
	public void addFollower(String followerUserLoginId, String followingUserLoginId, String followStatus) {
		followMapper.insertFollower(followerUserLoginId, followingUserLoginId, followStatus);
	}
	
	// follow update -> 팔로우 승인
	public void updateFollower(String followerUserLoginId, String followingUserLoginId, String followStatus) {
		followMapper.updateFollower(followerUserLoginId, followingUserLoginId, followStatus);
	}
	
	
	//follow delete -> 팔로우 거절
	public void deleteFollower(String followerUserLoginId, String followingUserLoginId) {
		followMapper.deleteFollower(followerUserLoginId, followingUserLoginId);
	}
	
	// follow generate
	public List<Follow> generateFollowListByLoginId(String followingUserLoginId) { // 팔로우 요청한 사람으로 가져오기
		
		// follow 리스트 가져옴
		return followMapper.selectFollowListByLoginId(followingUserLoginId);
	}	
	
	// follow generate
	public List<FollowView> generateFollowViewListByLoginId(String followingUserLoginId) { // 팔로우 요청한 사람으로 가져오기
		List<FollowView> followViewList = new ArrayList<>(); 
		
		// follow 리스트 가져옴
		List<Follow> followList = followMapper.selectFollowListByLoginId(followingUserLoginId);
		
		
		for (Follow follow : followList) {
			FollowView followView = new FollowView();
			
			// follow 버튼을 누른(요청) 사람들의 리스트 
			followView.setFollow(follow);
			
			// 일단 가져오자 팔로우요청 사람이든 팔로우한 사람이든
			UserEntity user = userBO.getUserEntityByLoginId(follow.getFollowerUserLoginId()); // 팔로우 요청한 사람들의 로그인 아이디
			followView.setUser(user);
			
			//follow요청 개수
//			int followCount = followMapper.selectFollowCountByLoginId(follow.getFollowerUserLoginId());
//			followView.setFollow(followCount);
			
			
			
			followViewList.add(followView);
		}
		return followViewList;
	}

	public Follow getFollower(String follower, String following) {
		return followMapper.selectFollower(follower, following);
	}
}
