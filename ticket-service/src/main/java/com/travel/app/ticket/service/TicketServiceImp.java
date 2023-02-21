package com.travel.app.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.app.ticket.dto.TicketDto;
import com.travel.app.ticket.pojo.Ticket;
import com.travel.app.ticket.repository.TicketRepository;
@Service
public class TicketServiceImp implements TicketServiceInt {
	
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public Ticket createTicket(String bookingNumber ,TicketDto ticketDto) {
		Ticket ticket = new Ticket();
		ticket.setBusType(ticketDto.getBusType());
		ticket.setPassengerName(ticketDto.getPassengerName());
		ticket.setSeatNumber(ticketDto.getSeatNumber());
		ticket.setTicketNumber(bookingNumber);
		ticket.setPrice(ticketDto.getPrice());
		ticket.setDestination(ticketDto.getDestination());
		ticketRepository.save(ticket);
		
		return ticket;
	}


	@Override
	public void deleteTicket(String ticketNumber) {
		ticketRepository.deleteByTicketNumber(ticketNumber);

	}

	@Override
	public Ticket getTicket(String ticketNumber) {
		return ticketRepository.findByTicketNumber(ticketNumber).get();
	}


}
