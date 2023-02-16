package com.travel.app.bookings.repository;

import com.travel.app.bookings.pojo.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip,Integer> {
    Optional<Trip> findByDestination(String destination);
}
