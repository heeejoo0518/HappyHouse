package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.AreaDto;

public interface AreaService {

	public int addArea(String userId, String dongcode);
	public int deleteArea(String areaNo);
	List<AreaDto> listArea(String userId);
}
