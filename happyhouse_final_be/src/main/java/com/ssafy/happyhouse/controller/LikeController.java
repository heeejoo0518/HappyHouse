package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.ssafy.happyhouse.service.JwtServiceImpl;
import com.ssafy.happyhouse.service.LikeService;

import io.swagger.annotations.Api;

@Api("LikeController V1")
@RestController
@RequestMapping("/like")
public class LikeController {

	public static final Logger logger = LoggerFactory.getLogger(LikeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private LikeService likeService;

	/**
	 * 관심지역 등록
	 * @param userId 아이디
	 * @param dongcode 동번호
	 * @param request 
	 * @return "message". 성공했다면 "success", 실패했다면 "fail"
	 * @throws Exception
	 */
	@PostMapping("/area/{userid}/{dongcode}")
	public ResponseEntity<String> addArea(@PathVariable("userid") String userId,
			@PathVariable("dongcode") String dongcode, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				likeService.addArea(userId, dongcode);
				status = HttpStatus.ACCEPTED;
				return new ResponseEntity<String>(SUCCESS, status);
			} catch (Exception e) {
				logger.error("관심지역 등록 실패 : {}", e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<String>(FAIL, status);
	}

	/**
	 * 관심지역 삭제
	 * @param userId 아이디
	 * @param areaNo 관심지역번호
	 * @param request
	 * @return "message". 성공했다면 "success", 실패했다면 "fail"
	 * @throws Exception
	 */
	@DeleteMapping("/area/{userid}/{no}")
	public ResponseEntity<Map<String, Object>> deleteArea(@PathVariable("userid") String userId,
			@PathVariable("no") String areaNo, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				likeService.deleteArea(areaNo);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("관심지역 삭제 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	/**
	 * 관심지역 목록 조회
	 * @param userId
	 * @param request
	 * @return {"message":"success" | "fail"},{"areaList":AreaDto리스트} 
	 * @throws Exception
	 */
	@GetMapping("/area/list/{userid}")
	public ResponseEntity<Map<String, Object>> getAreas(@PathVariable("userid") String userId,
			HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				resultMap.put("areaList", likeService.listArea(userId));
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("관심지역 조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	/**
	 * 관심아파트 등록
	 * 아이디+아파트번호 유니크
	 * @param userId 아이디
	 * @param aptCode 아파트번호
	 * @param request
	 * @return {"message":"success"|"fail"}
	 * @throws Exception
	 */
	@PostMapping("/apt/{userid}/{aptCode}")
	public ResponseEntity<Map<String, Object>> addApt(@PathVariable("userid") String userId,
			@PathVariable("aptCode") long aptCode, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				likeService.addApt(userId, aptCode);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("관심지역 등록 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	/**
	 * 관심아파트 삭제
	 * @param userId 아이디
	 * @param aptCode 아파트번호
	 * @param request
	 * @return {"message":"success"|"fail"}
	 * @throws Exception
	 */
	@DeleteMapping("/apt/{userid}/{aptCode}")
	public ResponseEntity<Map<String, Object>> deleteApt(@PathVariable("userid") String userId,
			@PathVariable("aptCode") long aptCode, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				likeService.deleteApt(userId,aptCode);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("관심지역 삭제 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	/**
	 * 관심아파트 목록조회
	 * @param userId 아이디
	 * @param request
	 * @return {"message":"success"|"fail", "aptList":HouseInfoDto 리스트}
	 * @throws Exception
	 */
	@GetMapping("/apt/list/{userid}")
	public ResponseEntity<Map<String, Object>> getApts(@PathVariable("userid") String userId,
			HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				resultMap.put("aptList", likeService.listApt(userId));
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("관심지역 조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
