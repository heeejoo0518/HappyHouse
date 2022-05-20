package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.AreaDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;

public interface LikeService {

	public int addArea(String userId, String dongcode);
	public int deleteArea(String areaNo);
	List<AreaDto> listArea(String userId);
	public int addApt(String userId, long aptCode);
	public int deleteApt(String userId, long aptCode);
	public List<HouseInfoDto> listApt(String userId);
}
