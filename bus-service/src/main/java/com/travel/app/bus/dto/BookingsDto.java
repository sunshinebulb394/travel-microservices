package com.travel.app.bus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingsDto {
    private Long id;
    private String bookingNumber;
    private LocalTime pickupTime;
    private LocalDate travelDate;
    private LocalDate createdAt;
    private String pickupLocation;
    private String destination;
    private String passengerName;
    private String busType;
    private String phoneNumber;

}
