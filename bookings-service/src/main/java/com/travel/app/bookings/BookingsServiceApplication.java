package com.travel.app.bookings;

import com.travel.app.bookings.pojo.Booking;
import com.travel.app.bookings.pojo.Trip;
import com.travel.app.bookings.repository.BookingRepository;
import com.travel.app.bookings.repository.TripRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication

public class BookingsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingsServiceApplication.class, args);
	}



}
