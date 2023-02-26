package com.travel.app.bus.service;

import com.travel.app.bus.config.WebClientConfig;
import com.travel.app.bus.dto.BookingsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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

//    public void deleteById(Long id) {
//        config.webClientBuilder()
//                .build()
//                .delete()
//                .uri("http://bookings-service/booking/delete-bookings/"+ id)
//                .retrieve();
//
//    }
}
