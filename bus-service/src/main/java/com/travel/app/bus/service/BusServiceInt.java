package com.travel.app.bus.service;


import com.travel.app.bus.dto.BusDto;
import com.travel.app.bus.dto.BusResponse;
import com.travel.app.bus.exceptions.BusNotFoundException;
import com.travel.app.bus.pojo.Bus;


import java.util.List;

public interface BusServiceInt {
	String add(BusDto busDto);
    List<Bus> getAllBuses() throws BusNotFoundException;
    String updateBus(Long id,BusDto busRequest)throws BusNotFoundException;
    String deleteBus(Long id)throws BusNotFoundException;

    public BusResponse isAvailable(String busType)throws BusNotFoundException;

    public Long busTypeAvailable(String busType) throws BusNotFoundException;
 
}