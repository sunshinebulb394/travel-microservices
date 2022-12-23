package com.travel.app.bookings;

import com.travel.app.bookings.model.Booking;
import com.travel.app.bookings.repository.BookingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class BookingsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingsServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (BookingRepository bookingRepository){
		return args -> {
			Booking booking = new Booking();
			booking.setBookingNumber("123456");
			booking.setPickupLocation("Mumbai");
			bookingRepository.save(booking);
		};
	}

}
