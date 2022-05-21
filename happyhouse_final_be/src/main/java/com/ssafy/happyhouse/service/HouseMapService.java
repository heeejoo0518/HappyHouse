package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(SidoGugunCodeDto sidoGugunCodeDto) throws Exception;
	List<HouseInfoDto> getApts(Map<String,Object> map) throws Exception;
	HouseInfoDto getAptWithDeal(long aptCode) throws Exception;
	
}
