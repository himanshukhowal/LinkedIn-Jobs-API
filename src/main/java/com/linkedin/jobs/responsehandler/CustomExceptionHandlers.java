package com.linkedin.jobs.responsehandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.linkedin.jobs.model.ResponseDetails;

@ControllerAdvice
public class CustomExceptionHandlers extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseDetails> handleAllExceptions(Exception ex, WebRequest request) {
		ex.printStackTrace();
		return new ResponseEntity<>(ResponseDetails.builder().date(new Date().toString())
				.message("Internal Server Error").path(request.getDescription(false))
				.status(HttpStatus.INTERNAL_SERVER_ERROR).data(ex.getMessage()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AuthorizationDeniedException.class)
	public final ResponseEntity<ResponseDetails> handleAuthorizationDeniedException(AuthorizationDeniedException ex,
			WebRequest request) {
		ex.printStackTrace();
		return new ResponseEntity<>(ResponseDetails.builder().date(new Date().toString())
				.message("Authorization Failed").path(request.getDescription(false)).status(HttpStatus.UNAUTHORIZED)
				.data(ex.getMessage()).build(), HttpStatus.UNAUTHORIZED);
	}

}