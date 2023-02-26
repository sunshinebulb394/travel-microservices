package com.travel.app.bus.controller;



import com.travel.app.bus.dto.DriverDto;
import com.travel.app.bus.dto.ResponseMessage;
import com.travel.app.bus.pojo.Driver;
import com.travel.app.bus.service.DriverServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverServiceImp driverService;

    @PostMapping
    public ResponseEntity<?> createDriver(@RequestBody DriverDto driverDto){
        ResponseMessage message = driverService.createDriver(driverDto);

       if(message != null){
           return new ResponseEntity<ResponseMessage>(message, HttpStatus.CREATED);
       }

       return new ResponseEntity<ResponseMessage>(new ResponseMessage("Creating Driver failed"),HttpStatus.BAD_REQUEST);


    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDriver(@PathVariable Long id,@RequestBody DriverDto driverDto) {
        ResponseMessage message = driverService.updateDriver(id, driverDto);

        if (message != null) {
            return new ResponseEntity<ResponseMessage>(message, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Updating Driver failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/driver-id")
    public ResponseEntity<?> getDriverById(@RequestParam("id")Long id){
        Driver driver = driverService.getDriverById(id);
        if(driver != null){
            return new ResponseEntity<Driver>(driver,HttpStatus.OK);
        }
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Driver not found"),HttpStatus.NOT_FOUND);

    }
    @GetMapping
    public ResponseEntity<?> getAllDrivers(){
        List<Driver> drivers = driverService.getAllDrivers();
        if(!drivers.isEmpty()){
            return new ResponseEntity<List<Driver>>(drivers,HttpStatus.OK);
        }
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("No drivers found"),HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteDriver(@RequestParam("id") Long id){
        driverService.deleteDriver(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Driver Deleted"),HttpStatus.NO_CONTENT);
    }

}
