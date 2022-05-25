package com.ssafy.happyhouse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Ticket;
@Mapper
public interface TicketMapper {
	public List<Ticket> retrieveTicket();
	public int getTotalCount();
	public int insertTicket(Map<String, String> ticket);
}