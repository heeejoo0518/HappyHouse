package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.Ticket;
import com.ssafy.happyhouse.service.JwtServiceImpl;
import com.ssafy.happyhouse.service.TicketService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private TicketService ticketService;

	@Autowired
	private JwtServiceImpl jwtService;

	@ApiOperation(value = "예정된 공연 목록", notes = "예정된 공연 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Ticket>> retrieveTicket(HttpServletRequest request) throws Exception {
		logger.debug("retrieveTicket - 호출");
		return new ResponseEntity<List<Ticket>>(ticketService.retrieveTicket(), HttpStatus.OK);
	}

	@ApiOperation(value = "예정된 공연 개수", notes = "예정된 공연 개수를 반환한다.", response = List.class)
	@GetMapping("/count")
	public ResponseEntity<Integer> getTotalCount() throws Exception {
		return new ResponseEntity<Integer>(ticketService.getTotalCount(), HttpStatus.OK);
	}

	@ApiOperation(value = "공연 등록", notes = "새 공연정보를 등록한다.")
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Map<String, Object> map, HttpServletRequest request) {
		logger.debug("register - 호출");

		if (!map.get("userid").equals("admin")) {
			logger.error("관리자 아님");
		}
		else if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰");
			Map<String,String> ticket = (Map<String, String>) map.get("ticket");
			 if (ticketService.insertTicket(ticket)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				logger.error("티켓 등록 실패");
			}
		} else {
			logger.error("사용 불가능 토큰");
		}

		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

}