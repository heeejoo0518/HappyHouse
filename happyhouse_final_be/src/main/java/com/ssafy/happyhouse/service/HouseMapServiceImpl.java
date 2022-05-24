package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.mapper.HouseMapMapper;
import com.ssafy.util.PageNavigation;

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
		int currentPage = Integer.parseInt((String)map.get("pg") == null ? "1" : (String)map.get("pg"));
		int sizePerPage = Integer.parseInt((String)map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		map.put("start", start);
		map.put("spp", sizePerPage);
		return houseMapMapper.getApts(map);
	}
	
	@Override
	public HouseInfoDto getAptWithDeal(long aptCode,String userid) throws Exception {
		HouseInfoDto dto = houseMapMapper.getAptWithDeal(aptCode,userid); 
		if(dto.getDeals().size() > 0) {
			dto.setLowest(dto.getDeals().get(0).getDealAmount());
			dto.setHighest(dto.getDeals().get(dto.getDeals().size()-1).getDealAmount());
		}else {
			dto.setLowest("0");
			dto.setHighest("0");
		}
		return dto;
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = houseMapMapper.getTotalCount(map);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		
		return pageNavigation;
	}

}
