package com.blog.search.domain;

import com.blog.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchUserView {
	// 유저 조회 및 검색
	private UserEntity user;
	
	//이 유저가 나를 팔로윙하고 있으면;
	private boolean followed = false;
	
	//내가 이 유저를 팔로잉하고 있으면
	private boolean following = false;
	
	//상태 표시를 여기서 해보자 =>왜냐면 너무 상태가 너무 많아서 버튼이 복수로 나온다.
	// null -> 아예 상대방 팔로우 자체도 안 했을 때 -> 팔로우 버튼 뜨게 하기
	// following true, followingApproved false-> 팔로우 요청대기 버튼, 몇 명 팔로우 요청이 들어왔는지
	// following true, followingApproved true -> 글 보기 버튼
	
	private Boolean followingApproved; //null 가능
	
	
}
