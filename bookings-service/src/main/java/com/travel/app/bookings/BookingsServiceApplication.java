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

	@Bean
	CommandLineRunner commandLineRunner (BookingRepository bookingRepository,TripRepository tripRepository){
		return args -> {
			Booking booking = new Booking();
			booking.setBookingNumber("654321");
			booking.setPickupLocation("Mumbai");
			booking.setPhoneNumber("0201219789");
			bookingRepository.save(booking);

//			Trip bolgatanga = new Trip();
//			bolgatanga.setDestination("Bolgatanga");
//			bolgatanga.setCostOfTrip(200.00);
//			Trip kumasi = new Trip();
//			kumasi.setDestination("kumasi");
//			kumasi.setCostOfTrip(50.00);
//			Trip koforidua = new Trip();
//			koforidua.setDestination("Koforidua");
//			koforidua.setCostOfTrip(100.00);
//			Trip capeCoast = new Trip();
//			capeCoast.setDestination("Cape Coast");
//			capeCoast.setCostOfTrip(150.00);
//			tripRepository.saveAll(List.of(bolgatanga,kumasi,capeCoast,koforidua));

		};
	}

}
