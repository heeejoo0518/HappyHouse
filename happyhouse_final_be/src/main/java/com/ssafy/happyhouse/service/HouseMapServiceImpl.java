package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

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
	public List<HouseInfoDto> getApts(Map<String,Object> map) throws Exception {
		return houseMapMapper.getApts(map);
	}
	
	@Override
	public HouseInfoDto getAptWithDeal(long aptCode) throws Exception {
		HouseInfoDto dto = houseMapMapper.getAptWithDeal(aptCode); 
		if(dto.getDeals().size() > 0) {
			dto.setLowest(dto.getDeals().get(0).getDealAmount());
			dto.setHighest(dto.getDeals().get(dto.getDeals().size()-1).getDealAmount());
		}else {
			dto.setLowest("0");
			dto.setHighest("0");
		}
		return dto;
	}

}
