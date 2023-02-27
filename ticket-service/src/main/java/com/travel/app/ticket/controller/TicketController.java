package com.travel.app.ticket.controller;

import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.app.ticket.dto.TicketDto;
import com.travel.app.ticket.pojo.Ticket;
import com.travel.app.ticket.service.TicketServiceImp;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	
	@Autowired
	private TicketServiceImp ticketServiceImp;
	
	@PostMapping("/generate-ticket")
	public Ticket createTicket(@RequestBody TicketDto ticketDto){
		return ticketServiceImp.createTicket(ticketDto);

	}




	@GetMapping("/retrieve-ticket")
	public ResponseEntity<Ticket> getTicket(@RequestParam("ticket-number") String ticketNumber){

			return new ResponseEntity<>(ticketServiceImp.getTicket(ticketNumber),HttpStatus.OK);
	}


	@DeleteMapping("/delete-ticket")
	public  void deleteTicket(@RequestParam("ticket-number") String ticketNumber) {


		ticketServiceImp.deleteTicket(ticketNumber);


	}

}
