package com.ssafy.happyhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public int register(MemberDto memberDto) throws Exception {
		return memberMapper.register(memberDto);
	}

	@Override
	public int modify(MemberDto memberDto) throws Exception {
		return memberMapper.modify(memberDto);
	}

	@Override
	public int delete(String userid) throws Exception {
		return memberMapper.delete(userid);
	}

}
