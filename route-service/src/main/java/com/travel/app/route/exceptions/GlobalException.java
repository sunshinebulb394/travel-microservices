package com.travel.app.route.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



public class GlobalException extends ResponseEntityExceptionHandler{

	@Override
	@Nullable
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers,HttpStatusCode status, WebRequest request) {
			String message = ex.getMessage();
	        List<String> details = new ArrayList<>();
	        details.add("Request Method is wrong");
	        details.add("Use the correct request method");
	        ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
	        return ResponseEntity.status(status).body(errors);
	}

	@Override
	@Nullable
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
	     String message = ex.getMessage();
	        List<String> details = new ArrayList<>();
	        details.add("Media type not supported ");
	        ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
	        return ResponseEntity.status(status).body(errors);
	}

	@Override
	@Nullable
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
			HttpMediaTypeNotAcceptableException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
	}

	@Override
	@Nullable
	protected ResponseEntity<Object> handleMissingPathVariable(
			MissingPathVariableException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
			String message = ex.getMessage();
	        List<String> details = new ArrayList<>();
	        details.add("Missing path Variable");
	        ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
	        return ResponseEntity.status(status).body(errors);
	}

	@Override
	@Nullable
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Request parameter not found");
        ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
	}

	
	@Override
	@Nullable
	protected ResponseEntity<Object> handleTypeMismatch(
			TypeMismatchException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("type mismatch");
        ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
        return ResponseEntity.status(status).body(errors);
	}

	@Override
	@Nullable
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		  String message = ex.getMessage();
	        List<String> details = new ArrayList<>();
	        details.add("Http request not readable");
	        ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
	        return ResponseEntity.status(status).body(errors);
	}

	
	 @ExceptionHandler(RouteNotFoundException.class)
	    protected ResponseEntity<Object> handleBusNotFoundException(RouteNotFoundException ex) {
	        String message = ex.getMessage();
	        List<String> details = new ArrayList<>();
	        details.add("Route not found");
	        ApiError errors = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	    }
	 
	 @ExceptionHandler(Exception.class)
	    protected ResponseEntity<Object> handleException(Exception ex) {
	        String message = ex.getMessage();
	        List<String> details = new ArrayList<>();
	        details.add("Exception Occurred");
	        ApiError errors = new ApiError(message, details, HttpStatus.BAD_REQUEST, LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	    }

}
