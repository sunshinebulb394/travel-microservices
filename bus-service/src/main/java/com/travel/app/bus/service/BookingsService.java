package com.travel.app.bus.service;

import com.travel.app.bus.config.WebClientConfig;
import com.travel.app.bus.dto.BookingsDto;
import com.travel.app.bus.dto.ResponseMessage;
import com.travel.app.bus.dto.TicketDto;
import com.travel.app.bus.dto.TicketResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingsService {

    private final WebClientConfig config;


    public List<BookingsDto> getAllBookings(){
        List<BookingsDto> allBookings = config.webClientBuilder()
                            .build()
                            .get()
                            .uri("http://booking-service/booking/all-bookings")
                            .retrieve()
                            .bodyToFlux(BookingsDto.class)
                            .collect(Collectors.toList())
                            .block();
        assert allBookings != null;
        for ( BookingsDto book: allBookings) {
            log.info(book.toString());
        }
        return allBookings;
    }

    public TicketResponse createTicket(Long id) {
    BookingsDto book = getAllBookings().stream()
                .filter(booking-> booking.getId() == id)
                .findFirst().
                orElseThrow(()->new EntityNotFoundException("Booking not found"));
    TicketDto ticket = TicketDto.builder()
            .bookingNumber(book.getBookingNumber())
            .passengerName(book.getPassengerName())
            .busType(book.getBusType())
            .destination(book.getDestination())
            .pickupTime(book.getPickupTime())
            .pickupLocation(book.getPickupLocation())
            .build();

    return config.webClientBuilder()
                .build()
                .post()
                .uri("http://ticket-service/api/ticket/generate-ticket")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(ticket),TicketDto.class)
                .retrieve()
                .bodyToMono(TicketResponse.class)
                .block();


    }

//    public void deleteById(Long id) {
//        config.webClientBuilder()
//                .build()
//                .delete()
//                .uri("http://bookings-service/booking/delete-bookings/"+ id)
//                .retrieve();
//
//    }
}
