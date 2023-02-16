package com.travel.app.ticket;

import com.travel.app.ticket.pojo.Ticket;
import com.travel.app.ticket.repository.TicketRepository;
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
//	CommandLineRunner commandLineRunner(TicketRepository ticketRepository){
//		return args -> {
//			Ticket ticket = new Ticket();
//			ticket.setTicketNumber("123456");
//			List<String> names = List.of("John","Smith");
//			ticket.setPassengerName(names);
//
//
//			ticketRepository.save(ticket);
//		};
//	}

}
