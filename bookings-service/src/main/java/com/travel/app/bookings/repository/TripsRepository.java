package com.travel.app.bookings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.app.bookings.model.Trips;


public interface TripsRepository extends JpaRepository<Trips, Long> {
	 List<Trips> findByIdIn(List<Long> id);
}
