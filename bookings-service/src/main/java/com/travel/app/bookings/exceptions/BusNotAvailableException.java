package com.travel.app.bookings.exceptions;

import java.io.Serial;

public class BusNotAvailableException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public BusNotAvailableException() {
        super();
    }

    public BusNotAvailableException(String message) {
        super(message);
    }
}
