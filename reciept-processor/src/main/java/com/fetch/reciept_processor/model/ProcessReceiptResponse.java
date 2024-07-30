package com.fetch.reciept_processor.model;

public class ProcessReceiptResponse {

	private String id;
	
	public ProcessReceiptResponse() {
		
	}

	public ProcessReceiptResponse(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
