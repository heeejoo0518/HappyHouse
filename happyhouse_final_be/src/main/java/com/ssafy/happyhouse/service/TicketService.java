package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.Ticket;

public interface TicketService {
	public List<Ticket> retrieveTicket();
	public int getTotalCount();
	
}
