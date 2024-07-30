package com.fetch.reciept_processor.model;

import java.util.List;

public class ReceiptRequest {

	private String retailer;
	private String purchaseDate;
	private String purchaseTime;
	private String total;
	private List<ItemRequest> items;
	
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<ItemRequest> getItems() {
		return items;
	}
	public void setItems(List<ItemRequest> items) {
		this.items = items;
	}
	
	
	
}
