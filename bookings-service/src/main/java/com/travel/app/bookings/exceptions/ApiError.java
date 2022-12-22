package com.travel.app.bookings.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;


import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class ApiError {
    private String message;
    private List<String> details;
    private HttpStatusCode status;
    private LocalDateTime time;
}
