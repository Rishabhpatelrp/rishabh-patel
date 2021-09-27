package com.locus.sh.locationsimulator.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//to apply exception to all controller/resources available
@RestControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse,
				HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;

	}

	@ExceptionHandler(value = { BadRequestException.class })
	public final ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
		return responseEntity;

	}

	@ExceptionHandler(value = { GoogleMapDirectionApiException.class })
	public final ResponseEntity<Object> handleInvalidRequestException(Exception ex, WebRequest request)
			throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(exceptionResponse,
				HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;

	}
}
