package com.travel.app.route.exceptions;

import java.io.Serial;

public class RouteNotFoundException extends RuntimeException{

	 @Serial
	    private static final long serialVersionUID = 1L;

	    public RouteNotFoundException() {
	        super();
	    }

	    public RouteNotFoundException(String message) {
	        super(message);
	    }
}
