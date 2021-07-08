package com.keyGenerator.dto;

import lombok.Data;

@Data
public class ResMessage {
	
	String status;
	String message;
	String errorMessage;
	String errorCode;

	public ResMessage(String status, String message, String errorMessage, String errorCode) {
		this.status = status;
		this.message = message;
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
}
