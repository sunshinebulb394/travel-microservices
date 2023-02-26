package com.travel.app.bus.controller;

import com.travel.app.bus.dto.BookingsDto;
import com.travel.app.bus.dto.BusDto;
import com.travel.app.bus.pojo.Bus;
import com.travel.app.bus.service.BookingsService;
import com.travel.app.bus.service.BusServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/bus")
@RequiredArgsConstructor
@Log4j2
public class BusController {

    private final BusServiceImp busServiceImp;
    private final BookingsService bookingsService;

    @PostMapping("/add")
    public ResponseEntity<?> addBus(@RequestBody BusDto busDto){
       return new ResponseEntity<>(busServiceImp.add(busDto),HttpStatus.CREATED);
    }
    

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBus(@PathVariable("id") Long busId,@RequestBody BusDto busRequest){
       return new ResponseEntity<>( busServiceImp.updateBus(busId,busRequest),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus>getBusById(@PathVariable("id")Long busId){
        return new ResponseEntity<Bus>(busServiceImp.getBusById(busId),HttpStatus.OK);
    }
    @GetMapping("/get-all-buses")
    @ResponseStatus(HttpStatus.OK)
    public List<Bus> getAllBuses(){
        return busServiceImp.getAllBuses();
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            log.info("I has the role!");
        }
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

    @GetMapping("/all-bookings")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<BookingsDto>> allBookings(){
        return new ResponseEntity<List<BookingsDto>>( bookingsService.getAllBookings(),HttpStatus.OK);
    }

//    @DeleteMapping("/delete-booking/{id}")
//    public ResponseEntity<?>deleteBookingById(@PathVariable Long id){
//        bookingsService.deleteById(id);
//    }
}
