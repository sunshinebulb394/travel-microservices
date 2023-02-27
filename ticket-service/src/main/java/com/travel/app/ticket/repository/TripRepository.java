package com.travel.app.ticket.repository;


import com.travel.app.ticket.pojo.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip,Integer> {
    Optional<Trip> findByDestination(String destination);
}
