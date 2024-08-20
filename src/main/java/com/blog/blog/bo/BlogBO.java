package com.blog.blog.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.domain.CardView;
import com.blog.comment.bo.CommentBO;
import com.blog.comment.domain.CommentView;
import com.blog.like.bo.LikeBO;
import com.blog.post.bo.PostBO;
import com.blog.post.bo.PostImageBO;
import com.blog.post.domain.PostImage;
import com.blog.post.entity.PostEntity;
import com.blog.user.bo.UserBO;
import com.blog.user.entity.UserEntity;

@Service
public class BlogBO {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostImageBO postImageBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// input: userId, output: List<CardView>
	public List<CardView> generateCardViewList(Integer userId) {
		
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록 가져오기 List<PostEntity>
		List<PostEntity> postList = postBO.getPostEntityList();
		
		for (PostEntity post : postList) {
			
			CardView card = new CardView();
			
			//글
			card.setPost(post);
			
			//글 이미지
			List<PostImage> postImageUrlList = postImageBO.selectImageUrlListByPostId(card.getPost().getId());
			card.setPostImage(postImageUrlList);
			
			//글쓴이
			UserEntity user = userBO.getUserEntityById(card.getPost().getUserId());
			card.setUser(user);
			
			// 댓글
			
			// 댓글 갯수
			List<CommentView> commetViewList = commentBO.generateCommentViewListByPostId(post.getId());
			card.setCommentList(commetViewList);
			
			// 좋아요 갯수
			int likeCount = likeBO.getLikeCountByPostId(post.getId());
			card.setLikeCount(likeCount);
			
			// 좋아요 채움여부
			card.setFilledLike(likeBO.filledLikeByPostIdUserId(post.getId(), userId));
			
			// 리스트에 넣기
			cardViewList.add(card);
		}
		
		return cardViewList;
	}
	
	
	
}
