package com.travel.app.bookings.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.app.bookings.pojo.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
	Optional<Booking> findByBookingNumber(String bookingNumber);
}
