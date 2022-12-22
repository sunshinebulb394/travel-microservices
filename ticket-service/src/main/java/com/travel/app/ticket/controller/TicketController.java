package com.travel.app.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.app.ticket.dto.TicketDto;
import com.travel.app.ticket.pojo.Ticket;
import com.travel.app.ticket.service.TicketServiceImp;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketServiceImp ticketServiceImp;
	
	@PostMapping("/generate-ticket")
	public void createTicket(@RequestParam("booking-number") String bookingNumber,
											   @RequestBody TicketDto ticketDto){
		ticketServiceImp.createTicket(ticketDto);

	}

	@GetMapping("/retrieve-ticket")
	public ResponseEntity<Ticket> getTicket(@RequestParam("ticket-number") String ticketNumber ,
											@RequestHeader("ticket-number") String responseTicketNumber){
		if (ticketNumber.equals(responseTicketNumber)) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("ticket-number",ticketNumber);
			return new ResponseEntity<>(ticketServiceImp.getTicket(ticketNumber),headers,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@DeleteMapping("/delete-ticket")
	public  void deleteTicket(@RequestParam("ticket-number") String ticketNumber) {


		ticketServiceImp.deleteTicket(ticketNumber);


	}

}
