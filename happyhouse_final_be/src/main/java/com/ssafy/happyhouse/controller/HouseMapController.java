package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.service.HouseMapService;

@RestController
@RequestMapping("/map")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService houseMapService;

	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido");
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
	}

	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		logger.debug("gugun");
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@PostMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestBody SidoGugunCodeDto sidoGugunCodeDto) throws Exception {
		logger.debug("dong");
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getDongInGugun(sidoGugunCodeDto), HttpStatus.OK);
	}

	@PostMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> getApts(@RequestBody Map<String, Object> map) throws Exception {
		logger.debug("getApt");
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getApts(map), HttpStatus.OK);

	}

	@GetMapping("/apt/{aptCode}/{userid}")
	public ResponseEntity<HouseInfoDto> getAptWithDeal(@PathVariable("aptCode") long aptCode,
			@PathVariable(value = "userid", required = false) String userid) throws Exception {
		logger.debug("getAptWithDeal");
		return new ResponseEntity<HouseInfoDto>(houseMapService.getAptWithDeal(aptCode,userid), HttpStatus.OK);
	}

}
