package com.travel.app.bookings.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusResponse {
    private String busType;
    private String registrationNumber;
    private boolean isAvailable;
   
}
