package com.travel.app.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;



@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean
	public CorsWebFilter corsFilter() {
	  CorsConfiguration config = new CorsConfiguration();
	  config.setAllowCredentials(true);
	  config.addAllowedOrigin("http://localhost:3000");
	  config.addAllowedHeader("*");
	  config.addAllowedMethod("*");

	  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	  source.registerCorsConfiguration("/**", config);

	  return new CorsWebFilter(source);
	}


}
