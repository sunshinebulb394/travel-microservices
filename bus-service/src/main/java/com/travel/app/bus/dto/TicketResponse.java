package com.travel.app.bus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    private Long id;
    private String passengerName;
    private String ticketNumber;
    private Double price;
    private String busType;
    private String destination;
    private LocalTime pickupTime;
    private String pickupLocation;
}
