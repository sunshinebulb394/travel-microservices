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
	private List<Long> seatNumber;
	private List<String> passengerName;
	private String ticketNumber;
	private BigDecimal price;
	private String busType;

	

}
