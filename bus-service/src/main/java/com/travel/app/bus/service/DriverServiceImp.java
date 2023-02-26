package com.travel.app.bus.service;

import com.travel.app.bus.dto.DriverDto;
import com.travel.app.bus.dto.ResponseMessage;
import com.travel.app.bus.pojo.Driver;
import com.travel.app.bus.repository.DriverRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverServiceImp implements DriverServiceInt {

    private final DriverRepository driverRepository;

    public ResponseMessage createDriver(DriverDto driver) {
      Optional<Driver> d =   driverRepository.findAll()
                .stream()
                .filter(l->l.getDriversLicense().equals(driver.getDriversLicense()))
                .findFirst();
       if(d.isEmpty()){
            Driver newDriver = Driver.builder()
                    .name(driver.getName())
                    .driversLicense(driver.getDriversLicense())
                    .contact(driver.getContact())
                    .build();
           driverRepository.save(newDriver);
           return new ResponseMessage("Driver added successfully");
       }
        return new ResponseMessage("Driver already added");

    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Driver not Found"));
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }


    public ResponseMessage updateDriver(Long id, DriverDto updatedDriver) {
        driverRepository.findById(id)
                        .map(drive -> {
                            drive.setDriversLicense(updatedDriver.getDriversLicense());
                            drive.setName(updatedDriver.getName());
                            drive.setContact(updatedDriver.getContact());
                            return driverRepository.save(drive);
                        }).orElseThrow(() -> new EntityNotFoundException("Driver not Found with id: "+id));

        return new ResponseMessage("Driver details Updated Successfully");
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
