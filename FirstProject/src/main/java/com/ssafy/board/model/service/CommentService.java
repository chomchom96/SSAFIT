package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Comment;
import com.ssafy.board.model.dto.SearchCondition;

public interface CommentService {
	
		// 게시글 전체 조회
		public List<Comment> getList();
		
		// 한 유저의 전체 댓글 조회
//		public List<Comment>
		
		// 댓글 등록
		public void writeComment(Comment comment);

		// 댓글 상세 조회
		public Comment getComment(int commentSeq);

		// 댓글 수정
		public void modifyComment(Comment comment);

		// 댓글 삭제
		public void removeComment(int commentSeq);

		//댓글 검색 
		public List<Comment> search(SearchCondition condition);


}