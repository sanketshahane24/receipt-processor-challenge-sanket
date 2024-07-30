package com.fetch.reciept_processor.model;

public class ErrorResponse {

	private String description;

	public ErrorResponse(String description) {
		
		this.description = description;
	}

	public ErrorResponse() {
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
