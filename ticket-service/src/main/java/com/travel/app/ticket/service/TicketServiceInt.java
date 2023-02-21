package com.travel.app.ticket.service;

import com.travel.app.ticket.dto.TicketDto;
import com.travel.app.ticket.pojo.Ticket;

public interface TicketServiceInt {
	
	Ticket createTicket(String bookingNumber,TicketDto ticketDto);
	void deleteTicket(String ticketNumber);

	Ticket getTicket(String ticketNumber);

	

}
