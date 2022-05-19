package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.AreaDto;
import com.ssafy.happyhouse.service.AreaService;
import com.ssafy.happyhouse.service.JwtServiceImpl;

import io.swagger.annotations.Api;

@Api("AreaController V1")
@RestController
@RequestMapping("/area")
public class AreaController {//resultmap에 추가해야됨~~~

	public static final Logger logger = LoggerFactory.getLogger(AreaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private AreaService areaService;

	// 관심지역 등록
	@PostMapping("/{userid}/{dongcode}")
	public ResponseEntity<Map<String, Object>>  addArea(@PathVariable("userid") String userId, @PathVariable("dongcode") String dongcode) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		//토큰이랑 비교하는 부분 추가해야됨
		areaService.addArea(userId, dongcode);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 관심지역 삭제
	@DeleteMapping("/{userid}/{no}")
	public ResponseEntity<Map<String, Object>>  deleteArea(@PathVariable("userid") String userId, @PathVariable("no") String areaNo) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		//토큰이랑 비교하는 부분 추가해야됨
		areaService.deleteArea(areaNo);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/{userid}/list")
	public ResponseEntity<Map<String, Object>>  getAreas(@PathVariable("userid") String userId) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		//토큰이랑 비교하고 map 추가해야됨
		List<AreaDto> list = areaService.listArea(userId);
		resultMap.put("list", list);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
