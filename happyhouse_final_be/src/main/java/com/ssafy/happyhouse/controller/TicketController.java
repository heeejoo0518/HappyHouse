package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.Ticket;
import com.ssafy.happyhouse.service.TicketService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private TicketService ticketService;

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


}