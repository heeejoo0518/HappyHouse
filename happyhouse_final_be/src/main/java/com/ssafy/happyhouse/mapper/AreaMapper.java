package com.ssafy.happyhouse.mapper;

import java.util.List;

import com.ssafy.happyhouse.dto.AreaDto;

public interface AreaMapper {
	int addArea(String userId, String dongCode);
	int deleteArea(String areaNo);
	List<AreaDto> listArea(String userId);
}
