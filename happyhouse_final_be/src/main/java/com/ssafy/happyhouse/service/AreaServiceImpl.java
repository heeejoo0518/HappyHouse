package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.happyhouse.dto.AreaDto;
import com.ssafy.happyhouse.mapper.AreaMapper;

public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaMapper areaMapper;

	@Override
	public int addArea(String userId, String dongcode) {
		return areaMapper.addArea(userId, dongcode);
	}

	@Override
	public int deleteArea(String areaNo) {
		return areaMapper.deleteArea(areaNo);
	}

	@Override
	public List<AreaDto> listArea(String userId) {
		return areaMapper.listArea(userId);
	}

}
