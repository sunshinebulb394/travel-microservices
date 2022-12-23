package com.travel.app.bookings.service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.travel.app.bookings.config.WebClientConfig;
import com.travel.app.bookings.dto.BookingDto;
import com.travel.app.bookings.dto.BusResponse;
import com.travel.app.bookings.dto.TicketDto;
import com.travel.app.bookings.dto.TripsDto;
import com.travel.app.bookings.exceptions.BookingNotFoundException;
import com.travel.app.bookings.exceptions.BusNotAvailableException;
import com.travel.app.bookings.model.Booking;
import com.travel.app.bookings.model.Trips;
import com.travel.app.bookings.repository.BookingRepository;
import com.travel.app.bookings.repository.TripsRepository;


import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class BookingServiceImp implements BookingServiceInt{
   
	 	@Autowired
	    private BookingRepository bookingRepository;

	    @Autowired
	    private WebClientConfig webClientConfig;
	    
	    @Autowired
	    private TripsRepository tripsRepository;


	    @Override
	    public String bookTrip(BookingDto bookingDto)  {
	        Booking booking = new Booking();
	        booking.setBookingNumber(RandomStringUtils.randomAlphanumeric(10).toUpperCase());
	        booking.setDateBooked(bookingDto.getDateBooked());
	        booking.setPickupLocation(bookingDto.getPickupLocation());
	        booking.setPickupTime(bookingDto.getPickupTime());
	        booking.setNumOfPersons(bookingDto.getNumOfPersons());

	        booking.setTrip(mapToDto(bookingDto.getTrip()));
	        
	       
	        String busType = bookingDto.getTrip().getBusType();
	                			

	       
	        // Call Bus Service, and book trip if bus is available
	        BusResponse busResponses = webClientConfig.webClientBuilder()
	                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	                .build()
	                .get()
	                .uri("http://bus-service/bus/bus-order",uriBuilder -> uriBuilder.queryParam("bus-type",busType).build())
	                .retrieve()
	                .bodyToMono(BusResponse.class)
	                .block();

	        assert busResponses != null;
	       
	        boolean busIsAvailable = busResponses.isAvailable();
	    
	        if (busIsAvailable){
	        	bookingRepository.save(booking);
				return "Trip Booked";
	       }
	        else
	        	throw new BusNotAvailableException("Bus Not Available");
	        
	    }
	    
	    private Trips mapToDto(TripsDto tripsDto) {
	        Trips trips = new Trips();
	        trips.setCostOfTrip(tripsDto.getCostOfTrip());
	        trips.setBusType(tripsDto.getBusType());
	        trips.setDestination(tripsDto.getDestination());
	        trips.setPassengerName(tripsDto.getPassengerName());
	        trips.setSeatNumber(tripsDto.getSeatNumber());
	        return trips;
	    }

	    @Override
	    public String updateBooking(Long id, BookingDto bookingDto) {
	        
	    	bookingRepository.findById(id).map(booking -> {
	    		booking.setNumOfPersons(bookingDto.getNumOfPersons());
	    		booking.setDateBooked(bookingDto.getDateBooked());
	    		booking.setPickupLocation(bookingDto.getPickupLocation());
	    		booking.setPickupTime(bookingDto.getPickupTime());
	    		
	    		Trips trip = tripsRepository.findById(id).stream()
	    							.map(updatedBooking -> {
	    								updatedBooking.setBusType(bookingDto.getTrip().getBusType());
	    								updatedBooking.setCostOfTrip(bookingDto.getTrip().getCostOfTrip());
	    								updatedBooking.setDestination(bookingDto.getTrip().getDestination());
	    								updatedBooking.setPassengerName(bookingDto.getTrip().getPassengerName());
	    								updatedBooking.setSeatNumber(bookingDto.getTrip().getSeatNumber());
	    								return tripsRepository.save(updatedBooking);
	    							}).findFirst().get();
	    		booking.setTrip(trip);
	    	
	    		return bookingRepository.save(booking);
	    	}).orElseThrow(()-> new BookingNotFoundException("Booking not found"));
	    	return "Booking updated";
	    }
      
	    

		@Override
	    public List<Booking> getAllBookings() {
	     return bookingRepository.findAll();
	    }

	    @Override
	    public void deleteBooking(Long id) {
	        bookingRepository.deleteById(id);
	    }




	public void deleteTrip(Long id) {
		bookingRepository.findById(id).map(booking->{
			booking.setTrip(null);
			return bookingRepository.save(booking);
		}).orElseThrow(()-> new BookingNotFoundException("Booking not found"));
	}


	public TicketDto generateTicket(String bookingNumber) {
		Booking booking = bookingRepository.findByBookingNumber(bookingNumber).get();

		TicketDto ticketRequest	=	TicketDto.builder()
				.ticketNumber(booking.getBookingNumber())
				.busType(booking.getTrip().getBusType())
				.price(booking.getTrip().getCostOfTrip())
				.seatNumber(booking.getTrip().getSeatNumber())
				.passengerName(booking.getTrip().getPassengerName())
				.build();

		return webClientConfig.webClientBuilder()
				.build()
				.post()
				.uri("http://ticket-service/ticket/generate-ticket",uri -> uri.queryParam("booking-number", bookingNumber).build())
				.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(ticketRequest),TicketDto.class)
				.retrieve()
				.bodyToMono(TicketDto.class)
				.block();



	}

	@Override
	public TicketDto retrieveTicket(String ticketNumber) {
		return webClientConfig.webClientBuilder()
				.build()
				.get()
				.uri("http://ticket-service/ticket/retrieve-ticket",uri -> uri.queryParam("ticket-number", ticketNumber).build())
				.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
				.header("ticket-number",ticketNumber)
				.retrieve()
				.bodyToMono(TicketDto.class)
				.block();
	}
}	
