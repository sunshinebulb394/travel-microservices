package com.travel.app.route.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.app.route.pojo.Route;

public interface RouteRepository extends JpaRepository<Route,Long> {
    
}
