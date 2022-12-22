package com.travel.app.route.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.app.route.dto.RouteDto;
import com.travel.app.route.exceptions.RouteNotFoundException;
import com.travel.app.route.pojo.Route;
import com.travel.app.route.repository.RouteRepository;

@Service
public class RouteServiceImp implements RouteServiceInt {
	
	@Autowired
	private RouteRepository routeRepository;
	

	@Override
	public void addRoute(RouteDto routeDto) {
		Route route = new Route();
		route.setCheckPoint(routeDto.getCheckPoint());
		route.setEndPoint(routeDto.getEndPoint());
		route.setStartPoint(routeDto.getStartPoint());
		routeRepository.save(route);
	}

	@Override
	public List<RouteDto> getAllRoutes() {
	  
	  List<RouteDto> allRoutes = routeRepository.findAll().stream()
			  			.map(route->{
			  					RouteDto routes = new RouteDto();
			  					routes.setCheckPoint(route.getCheckPoint());
			  					routes.setStartPoint(route.getStartPoint());
			  					routes.setEndPoint(route.getEndPoint());
			  					return routes;
			  					}).toList();
	  return allRoutes;
	}

	@Override
	public String updateRoute(Long id,RouteDto routeDto) {
		 routeRepository.findById(id).map(updateRoute->{
					updateRoute.setCheckPoint(routeDto.getCheckPoint());
					updateRoute.setStartPoint(routeDto.getStartPoint());
					updateRoute.setEndPoint(routeDto.getEndPoint());
					return routeRepository.save(updateRoute);
				}).orElseThrow(() -> new RouteNotFoundException("Route Not Found"));
		 
		 return "Route updated";
	}

	@Override
	public String deleteRoute(Long id) {
		routeRepository.deleteById(id);
		return "route deleted";
	}
    
}
