package com.travel.app.bookings.service;

import com.travel.app.bookings.pojo.Trip;
import com.travel.app.bookings.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.travel.app.bookings.config.WebClientConfig;
import com.travel.app.bookings.dto.BookingDto;
import com.travel.app.bookings.dto.BusResponse;
import com.travel.app.bookings.dto.TicketDto;
import com.travel.app.bookings.exceptions.BookingNotFoundException;
import com.travel.app.bookings.exceptions.BusNotAvailableException;
import com.travel.app.bookings.pojo.Booking;
import com.travel.app.bookings.repository.BookingRepository;



import reactor.core.publisher.Mono;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImp implements BookingServiceInt{
   
	 	@Autowired
	    private BookingRepository bookingRepository;

	    @Autowired
	    private WebClientConfig webClientConfig;
	    
		@Autowired
		private TripRepository tripRepository;


	    @Override
	    public Booking bookTrip(BookingDto bookingDto)  {
	        Booking booking = new Booking();
			String shortUuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
			booking.setBookingNumber(shortUuid);
			booking.setPickupLocation(bookingDto.getPickupLocation());
	        booking.setPickupTime(bookingDto.getPickupTime());
			booking.setPassengerName(bookingDto.getPassengerName());
			booking.setTravelDate(bookingDto.getTravelDate());
			booking.setBusType(bookingDto.getBusType());
			booking.setDestination(bookingDto.getDestination());
	        booking.setPhoneNumber(bookingDto.getPhoneNumber());

	       
	        // Call Bus Service, and book trip if bus is available
	        BusResponse busResponses = webClientConfig.webClientBuilder()
	                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	                .build()
	                .get()
	                .uri("http://bus-service/api/bus/bus-order/booking-service",uriBuilder -> uriBuilder.queryParam("bus-type",bookingDto.getBusType()).build())
	                .retrieve()
	                .bodyToMono(BusResponse.class)
	                .block();

	        assert busResponses != null;
	       
	        boolean busIsAvailable = busResponses.isAvailable();
	    
	        if (busIsAvailable){
	        	bookingRepository.save(booking);
				return booking;
	       }
	        else
	        	throw new BusNotAvailableException("Bus Not Available");
	        
	    }
	    


	    @Override
	    public String updateBooking(Long id, BookingDto bookingDto) {
	        
	    	bookingRepository.findById(id).map(booking -> {

	    		booking.setTravelDate(bookingDto.getTravelDate());
	    		booking.setPickupLocation(bookingDto.getPickupLocation());
	    		booking.setPickupTime(bookingDto.getPickupTime());
				booking.setBusType(bookingDto.getBusType());
				booking.setDestination(bookingDto.getDestination());
				booking.setPassengerName(bookingDto.getPassengerName());
	    		return bookingRepository.save(booking);
	    	}).orElseThrow(()-> new BookingNotFoundException("Booking not found"));
	    	return "Booking updated";
	    }


	@Override
	public List<Booking> getBookingByBookingNumber(String bookingNum) {
		return bookingRepository.findAll()
				.stream()
				.filter(num -> {
					String bookingNumber = num.getBookingNumber();
					return bookingNumber != null && bookingNumber.equals(bookingNum);
				})
				.collect(Collectors.toList());
	}
	@Override
	public List<Booking> getBookingByBookingId(Long id) {
		return bookingRepository.findAll()
				.stream()
				.filter(num -> {
					Long bookingId = num.getId();
					return bookingId != null && bookingId.equals(id);
				})
				.collect(Collectors.toList());
	}



	@Override
	    public List<Booking> getAllBookings() {
	     return bookingRepository.findAll();
	    }

	    @Override
	    public void deleteBooking(Long id) {
	        bookingRepository.deleteById(id);
	    }







	public TicketDto generateTicket(String bookingNumber) {
		Booking booking = bookingRepository.findByBookingNumber(bookingNumber).stream().findFirst().get();
		String busType = booking.getBusType();
		String destination = booking.getDestination();
		Optional<Trip> costOfTrip = tripRepository.findByDestination(destination);
		double cost = costOfTrip.get().getCostOfTrip();
		if(booking.getBusType().equals("VVIP")){
			cost += 100;
		}
		int seatNum = (int)(Math.random() * 30) + 1;
		TicketDto ticketRequest	=	TicketDto.builder()
				.price(cost)
				.destination(booking.getDestination())
				.seatNumber(seatNum)
				.busType(booking.getBusType())
				.passengerName(booking.getPassengerName())
				.build();

		return webClientConfig.webClientBuilder()
				.build()
				.post()
				.uri("http://ticket-service/ticket/generate-ticket",uri -> uri.queryParam("booking-number", booking.getBookingNumber()).build())
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
