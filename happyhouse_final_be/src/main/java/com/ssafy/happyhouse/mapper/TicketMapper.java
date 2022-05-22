package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Ticket;
@Mapper
public interface TicketMapper {
	public List<Ticket> retrieveTicket();
	public int getTotalCount();
}