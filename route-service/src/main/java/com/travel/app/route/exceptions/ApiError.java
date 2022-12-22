package com.travel.app.route.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ApiError {
    private String message;
    private List<String> details;
    private HttpStatusCode status;
    private LocalDateTime time;
}
