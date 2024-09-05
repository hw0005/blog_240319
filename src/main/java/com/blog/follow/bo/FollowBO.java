package com.blog.follow.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.comment.bo.CommentBO;
import com.blog.comment.domain.CommentView;
import com.blog.follow.domain.Follow;
import com.blog.follow.domain.FollowView;
import com.blog.follow.mapper.FollowMapper;
import com.blog.like.bo.LikeBO;
import com.blog.post.bo.PostBO;
import com.blog.post.bo.PostImageBO;
import com.blog.post.domain.PostImage;
import com.blog.post.entity.PostEntity;
import com.blog.user.bo.UserBO;
import com.blog.user.entity.UserEntity;

@Service
public class FollowBO {
	@Autowired
	private FollowMapper followMapper;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private PostImageBO postImageBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	
	
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
			
			// 일단 가져오자 팔로우요청 사람이든 팔로우한 사람이든
			UserEntity user = userBO.getUserEntityByLoginId(follow.getFollowingUserLoginId()); // 팔로우 받은 상대방
			
			UserEntity follower = userBO.getUserEntityByLoginId(follow.getFollowerUserLoginId()); // 나
			
			
			
			
			//following한 그 사람의 글 가져오는 로직, 여기서 구분해야 함
			int getUser = user.getId();
			List<PostEntity> postList = postBO.getPostEntityListByUserId(getUser);
			
			
			// following 한 그 사람의 이미지 가져오는 로직
			int getImage = 0;
			List<PostImage> postImageList = null;
			
			for (int i = 0; i < postList.size(); i++) {
				FollowView followView = new FollowView();
				
				followView.setUser(user);
				
				// follow 버튼을 누른(요청) 사람들의 리스트 
				followView.setFollow(follow);
				
				followView.setPost(postList.get(i));
				
				// 포스트랑 포스트이미지 저장
				getImage = postList.get(i).getId(); // 글번호 저장
				postImageList = postImageBO.selectImageUrlListById(getImage); // 글번호로 이미지 가져오기
				followView.setPostImage(postImageList);
				
				
				//follow요청 개수
				//수정
//				List<Follow> followCountList = followMapper.selectFollowerListCountById(follow.getId(), follow.getFollowStatus()); // 팔로우 요청한 사람들의 로그인 아이디
//				int followCount = 0;
//				for (Follow followC : followCountList) {
//					if (followCountList.get(i).getFollowStatus() == "팔로우 요청 대기" && follow.getFollowerUserLoginId() == followingUserLoginId) {
//							followCount += followC.getId();
//					}
//				}
//				followView.setFollowCount(followCount);
				
				// 댓글 갯수
				int commentCount = commentBO.getCommentCountByPostId(postList.get(i).getId());
				followView.setCommentCount(commentCount);
				
				// 댓글 나오기
				List<CommentView> commetViewList = commentBO.generateCommentViewListByPostId(postList.get(i).getId());
				followView.setCommentList(commetViewList);
				
				// 좋아요 갯수
				int likeCount = likeBO.getLikeCountByPostId(postList.get(i).getId());
				followView.setLikeCount(likeCount);
				
				// 좋아요 채움여부
				followView.setFilledLike(likeBO.filledLikeByPostIdUserId(postList.get(i).getId(), follower.getId()));
				followViewList.add(followView);
			}
			
		}
		
		
		return followViewList;
	}

	public Follow getFollower(String follower, String following) {
		return followMapper.selectFollower(follower, following);
	}
}
