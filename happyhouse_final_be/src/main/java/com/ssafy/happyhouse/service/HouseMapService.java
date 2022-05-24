package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.util.PageNavigation;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(SidoGugunCodeDto sidoGugunCodeDto) throws Exception;
	List<HouseInfoDto> getApts(Map<String,Object> map) throws Exception;
	HouseInfoDto getAptWithDeal(long aptCode,String userid) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	
}
