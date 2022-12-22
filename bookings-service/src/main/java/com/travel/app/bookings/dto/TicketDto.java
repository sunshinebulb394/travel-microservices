package com.travel.app.bookings.dto;

import java.math.BigDecimal;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TicketDto {
	private String ticketNumber;
    private BigDecimal price;
    private String busType;
    private List<Long> seatNumber ;
    private List<String> passengerName;
    
}
