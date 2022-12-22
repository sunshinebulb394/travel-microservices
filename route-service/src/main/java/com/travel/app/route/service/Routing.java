package com.travel.app.route.service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;

@Service
public class Routing {
    private static final String API_KEY = "AIzaSyAUmx1LuccwA7bEBie4wdxVKcyHRVZlZgs";


    public static DirectionsResult getRoute(String origin,String destination) throws IOException, InterruptedException, ApiException {


        Instant departureTime =Instant.ofEpochMilli(1671163555L);
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        return DirectionsApi.newRequest(context)
                .origin(origin)
                .destination(destination)
                .departureTime(departureTime)
                .transitMode(TransitMode.BUS)
                .trafficModel(TrafficModel.BEST_GUESS)
                .await();
    }
}
