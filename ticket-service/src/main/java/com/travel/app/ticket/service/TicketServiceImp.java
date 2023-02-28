package com.travel.app.ticket.service;

import com.travel.app.ticket.repository.TripRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.app.ticket.dto.TicketDto;
import com.travel.app.ticket.pojo.Ticket;
import com.travel.app.ticket.repository.TicketRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImp implements TicketServiceInt {
	

	private final TicketRepository ticketRepository;
	private final TripRepository tripRepository;


	@Override
	public Ticket createTicket(TicketDto ticketDto) {
		if (ticketRepository.findByTicketNumber(ticketDto.getBookingNumber()).isPresent()){
			throw new EntityExistsException("Ticket already exists");
		}
		Ticket ticket = new Ticket();
		ticket.setBusType(ticketDto.getBusType());
		ticket.setPassengerName(ticketDto.getPassengerName());
		ticket.setTicketNumber(ticketDto.getBookingNumber());
		ticket.setPickupTime(ticketDto.getPickupTime());
		ticket.setPickupLocation(ticketDto.getPickupLocation());
		Double costOfTrip = tripRepository.findByDestination(ticketDto.getDestination()).get().getCostOfTrip();
		if(ticketDto.getBusType().equals("VVIP")){
			costOfTrip += 100;
		}
		ticket.setPrice(costOfTrip);
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
		Optional<Ticket> ticket = ticketRepository.findByTicketNumber(ticketNumber);
		return ticket.orElse(null);
	}



}
