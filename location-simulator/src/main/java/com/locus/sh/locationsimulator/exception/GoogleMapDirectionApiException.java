package com.locus.sh.locationsimulator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class GoogleMapDirectionApiException extends RuntimeException {

	public GoogleMapDirectionApiException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
