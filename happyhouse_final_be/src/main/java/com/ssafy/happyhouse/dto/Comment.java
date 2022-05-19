package com.ssafy.happyhouse.dto;

public class Comment {
	private int commentno;
	private int articleno;
	private String userid;
	private String content;
	private String regtime;
	
	
	public Comment(int commentno, int articleno, String userid, String content, String regtime) {
		this.commentno = commentno;
		this.articleno = articleno;
		this.userid = userid;
		this.content = content;
		this.regtime = regtime;
	}
	
	public int getCommentno() {
		return commentno;
	}
	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [commentno=");
		builder.append(commentno);
		builder.append(", articleno=");
		builder.append(articleno);
		builder.append(", userid=");
		builder.append(userid);
		builder.append(", content=");
		builder.append(content);
		builder.append(", regtime=");
		builder.append(regtime);
		builder.append("]");
		return builder.toString();
	}
	
}
