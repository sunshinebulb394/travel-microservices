package com.travel.app.ticket;

import com.travel.app.ticket.pojo.Ticket;
import com.travel.app.ticket.pojo.Trip;
import com.travel.app.ticket.repository.TicketRepository;
import com.travel.app.ticket.repository.TripRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketServiceApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(TripRepository tripRepository){
//		return args -> {
//			Trip ho = Trip.builder()
//					.destination("Ho")
//					.costOfTrip(100.00)
//					.build();
//			Trip bolga = Trip.builder()
//					.destination("Bolgatanga")
//					.costOfTrip(200.00)
//					.build();
//			Trip kumasi = Trip.builder()
//					.destination("Kumasi")
//					.costOfTrip(150.00)
//					.build();
//			Trip capeCoast = Trip.builder()
//					.destination("Cape Coast")
//					.costOfTrip(120.00)
//					.build();
//			Trip kof = Trip.builder()
//					.destination("Koforidua")
//					.costOfTrip(170.00)
//					.build();
//			tripRepository.saveAll(List.of(ho,bolga,kumasi,kof,capeCoast));
//		};
//	}

}
