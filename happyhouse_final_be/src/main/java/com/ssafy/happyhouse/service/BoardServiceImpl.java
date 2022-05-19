package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.Comment;
import com.ssafy.happyhouse.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<Board> retrieveBoard() {
		return boardMapper.retrieveBoard();
	}

	@Override
	public boolean insertBoard(Board board) {
		return boardMapper.insertBoard(board) == 1;
	}

	@Override
	public Board detailBoard(int articleno) {
		return boardMapper.detailBoard(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(Board board) {
		return boardMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}
	
	@Override
	public int getTotalCount() {
		return boardMapper.getTotalCount();
	}

	@Override
	public boolean insertComment(Comment comment) {
		return boardMapper.insertComment(comment) == 1;
	}

	@Override
	public boolean deleteComment(int commentno) {
		return boardMapper.deleteComment(commentno) == 1;
	}
	
}