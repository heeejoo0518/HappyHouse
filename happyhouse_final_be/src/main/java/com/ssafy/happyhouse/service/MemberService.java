package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;

	public MemberDto userInfo(String userid) throws Exception;

	public int register(MemberDto memberDto) throws Exception;

	public int modify(MemberDto memberDto) throws Exception;

	public int delete(String userid) throws Exception;

}
