package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.AreaDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;

@Mapper
public interface LikeMapper {
	int addArea(String userId, String dongCode);
	int deleteArea(String areaNo);
	List<AreaDto> listArea(String userId);
	int addApt(String userId, long aptCode);
	int deleteApt(String userId, long aptCode);
	List<HouseInfoDto> listApt(String userId);
}
