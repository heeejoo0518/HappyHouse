package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.AreaDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.mapper.LikeMapper;

@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
	private LikeMapper likeMapper;

	@Override
	public int addArea(String userId, String dongcode) {
		return likeMapper.addArea(userId, dongcode);
	}

	@Override
	public int deleteArea(String areaNo) {
		return likeMapper.deleteArea(areaNo);
	}

	@Override
	public List<AreaDto> listArea(String userId) {
		return likeMapper.listArea(userId);
	}

	@Override
	public int addApt(String userId, long aptCode) {
		return likeMapper.addApt(userId, aptCode);
	}

	@Override
	public int deleteApt(String userId, long aptCode) {
		return likeMapper.deleteApt(userId, aptCode);
	}

	@Override
	public List<HouseInfoDto> listApt(String userId) {
		return likeMapper.listApt(userId);
	}

}
