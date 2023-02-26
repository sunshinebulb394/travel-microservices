package com.travel.app.bus.service;

import com.travel.app.bus.dto.DriverDto;
import com.travel.app.bus.dto.ResponseMessage;
import com.travel.app.bus.pojo.Driver;

import java.util.List;

public interface DriverServiceInt {
    ResponseMessage createDriver(DriverDto driver);
    Driver getDriverById(Long id);
    List<Driver> getAllDrivers();
    void deleteDriver(Long id);
    ResponseMessage updateDriver(Long id, DriverDto updatedDriver);
}
