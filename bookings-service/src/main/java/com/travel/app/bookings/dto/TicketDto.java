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
    private Double price;
    private String ticketNumber;
    private String busType;
    private Integer seatNumber ;
    private String passengerName;
    private String destination;
}
