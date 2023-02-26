package com.travel.app.bus.service;


import com.travel.app.bus.pojo.users.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;


import com.travel.app.bus.dto.BusDto;
import com.travel.app.bus.dto.BusResponse;
import com.travel.app.bus.dto.BusesAvailableDto;
import com.travel.app.bus.exceptions.BusNotFoundException;
import com.travel.app.bus.pojo.Bus;
import com.travel.app.bus.pojo.BusesAvailable;
import com.travel.app.bus.repository.BusRepository;
import com.travel.app.bus.repository.BusesAvailableRepository;

import java.util.List;
import java.util.Optional;




@Service
public class BusServiceImp implements BusServiceInt {


	private final BusRepository busRepository;
	private final BusesAvailableRepository busesAvailableRepository;



	public BusServiceImp(BusRepository busRepository, BusesAvailableRepository busesAvailableRepository) {
		this.busRepository = busRepository;
		this.busesAvailableRepository = busesAvailableRepository;
	}

	public String add(BusDto busDto) {
		Bus bus = new Bus();
		bus.setBusType(busDto.getBusType());
		bus.setInsuranceNumber(busDto.getInsuranceNumber());
		bus.setRegistrationNumber(busDto.getRegistrationNumber());
		bus.setRoadWorthyExpiryDate(busDto.getRoadWorthyExpiryDate());
		bus.setInsuranceCompany(busDto.getInsuranceCompany());
		bus.setInsuranceExpiryDate(busDto.getInsuranceExpiryDate());
		bus.setPurchaseDate(busDto.getPurchaseDate());
		bus.setCapacity(busDto.getCapacity());
		bus.setIsAvailable(busDto.getIsAvailable());


		if (busRepository.findByRegistrationNumber(busDto.getRegistrationNumber()).isEmpty()) {

			busRepository.save(bus);
			return "Bus added";
		}
		return "Bus already added";
	}

	private BusesAvailable busesAvailable(BusesAvailableDto busesAvailableDto) {
		BusesAvailable busesAvailable = new BusesAvailable();
		Optional<BusesAvailable> bAvailable = busesAvailableRepository.findByRegistrationNumber(busesAvailableDto.getRegistrationNumber());
		if (bAvailable.isEmpty()) {
			busesAvailable.setBusType(busesAvailableDto.getBusType());
			busesAvailable.setRegistrationNumber(busesAvailableDto.getRegistrationNumber());
			return busesAvailable;
		}
		return null;

	}


	@Override
	public List<Bus> getAllBuses() {
		return busRepository.findAll();
	}


	@Override
	public String updateBus(Long id, BusDto busRequest) {
		busRepository.findById(id).map(bus1 -> {
			bus1.setBusType(busRequest.getBusType());
			bus1.setInsuranceNumber(busRequest.getInsuranceNumber());
			bus1.setCapacity(busRequest.getCapacity());
			bus1.setIsAvailable(busRequest.getIsAvailable());
			bus1.setPurchaseDate(busRequest.getPurchaseDate());
			bus1.setInsuranceCompany(busRequest.getInsuranceCompany());
			bus1.setInsuranceNumber(busRequest.getInsuranceNumber());
			bus1.setRegistrationNumber(busRequest.getRegistrationNumber());
			bus1.setRoadWorthyExpiryDate(busRequest.getRoadWorthyExpiryDate());
			return busRepository.save(bus1);
		}).orElseThrow(() -> new BusNotFoundException("Bus id not found"));

		return "Bus updated";
	}

	@Override

	public String deleteBus(Long id) {
		busRepository.deleteById(id);
		return "Bus has been deleted";
	}


	@Override
	public BusResponse isAvailable(String busType) throws BusNotFoundException {
		Long busTypeAvailable = busesAvailableRepository.findByBusType(busType).stream()
				.filter(bus -> bus.getBusType().equals(busType))
				.count();
		return BusResponse.builder()
				.isAvailable(busTypeAvailable > 0)
				.build();
	}

	public Long busTypeAvailable(String busType) {
		return busesAvailableRepository.findByBusType(busType).stream()
				.filter(bus -> bus.getBusType().equals(busType))
				.count();

	}


	public Bus getBusById(Long busId) {

		Optional<Bus> bus = busRepository.findById(busId);
		if (bus.isPresent()) {
			return bus.get();
		} else
			throw new BusNotFoundException("Bus not found");
	}

}

	
	


