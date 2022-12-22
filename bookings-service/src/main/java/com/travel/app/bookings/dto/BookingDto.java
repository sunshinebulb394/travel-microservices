package com.travel.app.bookings.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private TripsDto trip;
    private LocalTime pickupTime;
    private LocalDate dateBooked;
    private String pickupLocation;
    private Long numOfPersons;

    
}
