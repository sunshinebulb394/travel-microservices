package com.travel.app.route.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.app.route.dto.RouteDto;
import com.travel.app.route.service.RouteServiceImp;

@RestController
@RequestMapping("/route")
public class RouteController {
	
	@Autowired
	private RouteServiceImp routeServiceImp;
	
	@PostMapping("/add-route")
	public String addRoute(@RequestBody RouteDto routeDto) {
		routeServiceImp.addRoute(routeDto);
		return "Route added";
	}

	@GetMapping("/getAll-route")
	public List<RouteDto> getAllRoutes(){
		return routeServiceImp.getAllRoutes();
	}
}
