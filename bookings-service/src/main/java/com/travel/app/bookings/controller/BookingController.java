package com.travel.app.bookings.controller;




import com.travel.app.bookings.pojo.Booking;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.travel.app.bookings.dto.BookingDto;
import com.travel.app.bookings.dto.TicketDto;
import com.travel.app.bookings.service.BookingServiceImp;


import java.util.List;


@RestController
@RequestMapping("/booking")
@Log4j2
public class BookingController {

    @Autowired
    private BookingServiceImp bookingServiceImp;

    String bearerToken = "Bearer gkb0201219789";
    @PostMapping("/add")
    public ResponseEntity<?> bookTrip(@RequestBody BookingDto bookingDto,
                                      @RequestHeader("Content-Type") String contentType,
                                      @RequestHeader("Authorization")String authorization){
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        if(MediaType.APPLICATION_JSON_VALUE.equals(contentType)&& bearerToken.contentEquals(authorization)){
            Booking addBooking = bookingServiceImp.bookTrip(bookingDto);

            return new ResponseEntity<>(addBooking, headers, HttpStatus.CREATED);
        }
       return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBookingByBookingNumber(@RequestParam("number") String id,
                                      @RequestHeader("Content-Type") String contentType,
                                      @RequestHeader("Authorization")String authorization){
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        if(MediaType.TEXT_PLAIN_VALUE.equals(contentType)&& bearerToken.contentEquals(authorization)){
            List<Booking> bookings = bookingServiceImp.getBookingByBookingNumber(id);
            return new ResponseEntity<>(bookings, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/id")
    public ResponseEntity<?> getBookingById(@RequestParam("id") Long id,

                                            @RequestHeader("Authorization")String authorization){


        if( bearerToken.contentEquals(authorization)){

            List<Booking> bookings = bookingServiceImp.getBookingByBookingId(id);
            return new ResponseEntity<>(bookings,HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
    }
    
    @PutMapping("/update-booking")
    public ResponseEntity<?> updateBooking(@RequestParam("booking-id") Long bookingId,
                                           @RequestBody BookingDto bookingDto,
                                           @RequestHeader("Content-Type") String contentType,
                                           @RequestHeader("Authorization") String authorization) {


        if(MediaType.APPLICATION_JSON_VALUE.equals(contentType)&& bearerToken.contentEquals(authorization)){
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_PLAIN);
                String updateBooking = (bookingServiceImp.updateBooking(bookingId, bookingDto));
                return new ResponseEntity<>(updateBooking, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/all-bookings")
    public ResponseEntity<?> getAllBookings(){
        return new ResponseEntity<List<Booking>>(bookingServiceImp.getAllBookings(),HttpStatus.OK);

    }

    @DeleteMapping("/delete-bookings/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id){

            bookingServiceImp.deleteBooking(id);
            return new ResponseEntity<String>("Booking has been deleted",HttpStatus.NO_CONTENT);
    }



    @PostMapping("/gen-ticket")
    public ResponseEntity<?> generateTicket(@RequestParam("booking-number") String bookingNumber,
                                                    @RequestHeader("Content-Type") String contentType,
                                                    @RequestHeader("Authorization") String authorization) {

        if(MediaType.TEXT_PLAIN_VALUE.equals(contentType)&& bearerToken.contentEquals(authorization)){
            TicketDto ticket = bookingServiceImp.generateTicket(bookingNumber);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(ticket, headers, HttpStatus.CREATED);
        }
        return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/retrieve-ticket")
    public ResponseEntity<TicketDto> retrieveTicket(@RequestParam("ticket-number") String ticketNumber,
                                                    @RequestHeader("Content-Type") String contentType,
                                                    @RequestHeader("Authorization") String authorization) {

        if(MediaType.TEXT_PLAIN_VALUE.equals(contentType)&& bearerToken.contentEquals(authorization)){
            TicketDto ticket = bookingServiceImp.retrieveTicket(ticketNumber);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(ticket, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    
}
