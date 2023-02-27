package com.travel.app.ticket.dto;

import java.math.BigDecimal;
import java.time.LocalTime;
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
	private String passengerName;
	private String busType;
	private String destination;
	private String bookingNumber;
	private LocalTime pickupTime;
	private String pickupLocation;

}
