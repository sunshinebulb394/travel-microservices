package com.travel.app.bookings.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookingNumber;
    private LocalTime pickupTime;
    private LocalDate travelDate;
    @Builder.Default
    private LocalDate createdAt = LocalDate.now();
    private String pickupLocation;
    private String destination;
    private String passengerName;
    private String busType;
    private String phoneNumber;
}
