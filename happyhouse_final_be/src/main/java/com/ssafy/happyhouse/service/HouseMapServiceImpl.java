package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(SidoGugunCodeDto sidoGugunCodeDto) throws Exception {
		return houseMapMapper.getDongInGugun(sidoGugunCodeDto);
	}

	@Override
	public List<HouseInfoDto> getApts(SidoGugunCodeDto sidoGugunCodeDto) throws Exception {
		return houseMapMapper.getApts(sidoGugunCodeDto);
	}
	

	@Override
	public HouseInfoDto getAptWithDeal(long aptCode) throws Exception {
		return houseMapMapper.getAptWithDeal(aptCode);
	}

}
