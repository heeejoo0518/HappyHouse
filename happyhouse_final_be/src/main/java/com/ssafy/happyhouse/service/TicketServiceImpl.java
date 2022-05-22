package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.Ticket;
import com.ssafy.happyhouse.mapper.TicketMapper;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	private TicketMapper ticketMapper;

	@Override
	public List<Ticket> retrieveTicket() {
		return ticketMapper.retrieveTicket();
	}

	@Override
	public int getTotalCount() {
		return ticketMapper.getTotalCount();
	}

}