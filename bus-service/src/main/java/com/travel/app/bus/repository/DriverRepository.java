package com.travel.app.bus.repository;

import com.travel.app.bus.pojo.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
