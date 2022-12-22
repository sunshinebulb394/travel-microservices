package com.travel.app.bus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.app.bus.pojo.Bus;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {
    Optional<Bus> findByRegistrationNumber(String registrationNumber);

    List<Bus> findByBusTypeIn(List<String> busType);
    Optional<Bus> findByBusType(String busType);
}

