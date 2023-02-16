package com.travel.app.ticket.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketDto {
	private Integer seatNumber;
	private String passengerName;
	private String ticketNumber;
	private Double price;
	private String busType;

	

}
