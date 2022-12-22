package com.travel.app.route.service;

import java.util.List;

import com.travel.app.route.dto.RouteDto;
import com.travel.app.route.exceptions.RouteNotFoundException;

public interface RouteServiceInt {
	
	 void addRoute(RouteDto routeDto) throws RouteNotFoundException;
	 List<RouteDto> getAllRoutes();
	 String updateRoute(Long id,RouteDto routeDto);
	 String deleteRoute(Long id);

}
