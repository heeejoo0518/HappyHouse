package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.Comment;
@Mapper
public interface BoardMapper {
	public List<Board> retrieveBoard();
	public Board detailBoard(int articleno);
	public int insertBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(int articleno);
	
	public int insertComment(Comment comment);
	public int deleteComment(int commentno);
	
	public int getTotalCount();
}