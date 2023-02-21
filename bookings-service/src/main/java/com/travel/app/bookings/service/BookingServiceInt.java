package com.travel.app.bookings.service;

import java.util.List;
import com.travel.app.bookings.dto.BookingDto;
import com.travel.app.bookings.dto.TicketDto;
import com.travel.app.bookings.exceptions.BusNotAvailableException;
import com.travel.app.bookings.pojo.Booking;

import reactor.core.publisher.Mono;

public interface BookingServiceInt {
    Booking bookTrip(BookingDto bookingDto) throws BusNotAvailableException;
    String updateBooking(Long id, BookingDto bookingDto);
    List<Booking> getAllBookings();
    void deleteBooking(Long id);
    List<Booking> getBookingByBookingNumber(String bookingNum);
    List<Booking> getBookingByBookingId(Long id);

    TicketDto retrieveTicket(String ticketNumber);
   


}
