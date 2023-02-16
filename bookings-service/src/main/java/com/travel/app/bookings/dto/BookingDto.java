package com.travel.app.bookings.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private LocalTime pickupTime;
    private LocalDate travelDate;
    private String pickupLocation;
    private String destination;
    private String passengerName;
    private String busType;
    private String phoneNumber;
}
