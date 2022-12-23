package com.travel.app.bookings.model;

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
    private LocalDate dateBooked;
    @Builder.Default
    private LocalDate createdAt = LocalDate.now();
    private String pickupLocation;
    private Long numOfPersons;
    @OneToOne(cascade = CascadeType.ALL)
    private Trips trip ;

}
