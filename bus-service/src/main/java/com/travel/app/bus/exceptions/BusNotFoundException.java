package com.travel.app.bus.exceptions;

import java.io.Serial;

public class BusNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public BusNotFoundException() {
        super();
    }

    public BusNotFoundException(String message) {
        super(message);
    }
}
