package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(SidoGugunCodeDto sidoGugunCodeDto) throws SQLException;
	List<HouseInfoDto> getApts(Map<String,Object> map) throws SQLException;
	HouseInfoDto getAptWithDeal(long aptCode,String userid) throws SQLException;
	int getTotalCount(Map<String,String> map) throws SQLException;
}
