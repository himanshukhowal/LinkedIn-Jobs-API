package com.linkedin.jobs.responsehandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.linkedin.jobs.model.ResponseDetails;

import jakarta.servlet.http.HttpServletRequest;

public class ResponseHandler {

	public static ResponseEntity<ResponseDetails> generateResponse(String message, HttpServletRequest request,
			HttpStatus status, Object responseObj) {
		return new ResponseEntity<ResponseDetails>(ResponseDetails.builder().date(new Date().toString())
				.message(message).path(request.getRequestURI()).status(status).data(responseObj).build(), status);
	}

}