package com.travel.app.bookings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
public class TripsDto {
    private Long id;
    private BigDecimal costOfTrip;
    private String destination;
    private List<Long> seatNumber;
    private List<String> passengerName;
    private String busType;
}
