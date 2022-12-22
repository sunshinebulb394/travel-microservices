package com.travel.app.bookings.exceptions;

import java.io.Serial;

public class BookingNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public BookingNotFoundException() {
        super();
    }

    public BookingNotFoundException(String message) {
        super(message);
    }
}
