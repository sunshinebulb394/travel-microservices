package com.travel.app.bus.controller;

import com.travel.app.bus.dto.BusDto;
import com.travel.app.bus.pojo.Bus;
import com.travel.app.bus.service.BusServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/bus")
public class BusController {
    @Autowired
    private BusServiceImp busServiceImp;


    @PostMapping("/add")
    public ResponseEntity<?> addBus(@RequestBody BusDto busDto){
       return new ResponseEntity<>(busServiceImp.add(busDto),HttpStatus.CREATED);
    }
    

    @PutMapping("/update-bus-details/{bus-id}")
    public ResponseEntity<?> updateBus(@PathVariable("bus-id") Long busId,@RequestBody BusDto busRequest){
       return new ResponseEntity<>( busServiceImp.updateBus(busId,busRequest),HttpStatus.OK);

    }

    @GetMapping("/get-all-buses")
    @ResponseStatus(HttpStatus.OK)
    public List<Bus> getAllBuses(){
        return busServiceImp.getAllBuses();
    }


    @GetMapping("/delete-bus/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")Long id){
        busServiceImp.deleteBus(id);
    }

    @GetMapping("/bus-order/booking-service")
    public ResponseEntity<?> isAvailable(@RequestParam("bus-type") String busType) {
        return new ResponseEntity<>(busServiceImp.isAvailable(busType),HttpStatus.OK);
    }

    @GetMapping("/buses-available")
    public ResponseEntity<Long> busesAvailable(@RequestParam("bus-type")String busType){
        Long bus = busServiceImp.busTypeAvailable(busType);
        return new ResponseEntity<>(bus,HttpStatus.OK);
    }


}
