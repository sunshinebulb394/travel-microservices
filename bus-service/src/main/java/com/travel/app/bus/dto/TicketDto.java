package com.travel.app.bus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@Builder
public class TicketDto {
    private String passengerName;
    private String busType;
    private String destination;
    private String bookingNumber;
    private LocalTime pickupTime;
    private String pickupLocation;
}
