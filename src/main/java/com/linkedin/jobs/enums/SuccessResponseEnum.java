package com.linkedin.jobs.enums;

import org.springframework.http.HttpStatus;

public enum SuccessResponseEnum {

	SUCCESS_GET("Data Successfully Fetched", HttpStatus.OK),
	SUCCESS_POST("Record Created", HttpStatus.CREATED), 
	SUCCESS_PUT("Record Updated", HttpStatus.CREATED);

	private String responseMessage;
	private HttpStatus responseCode;

	SuccessResponseEnum(String responseMessage, HttpStatus responseCode) {
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public HttpStatus getResponseCode() {
		return responseCode;
	}

}