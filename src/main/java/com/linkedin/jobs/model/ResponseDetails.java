package com.linkedin.jobs.model;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDetails {

	private String date;
	private String message;
	private String path;
	private HttpStatus status;
	private Object data;

}