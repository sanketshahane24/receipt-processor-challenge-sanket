package com.fetch.reciept_processor.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fetch.reciept_processor.model.ItemRequest;
import com.fetch.reciept_processor.model.ReceiptRequest;

@ExtendWith(MockitoExtension.class)
public class ReceiptProcessingServiceTest {

	@InjectMocks
	private ReceiptProcessingService service;
	
	@Test
	public void addReceiptTest() {
		var id=service.addReceipt(createRequest1());
		assertNotNull(id);
	}
	
	@Test
	public void checkIfPresentTest() {
		String id = "e1300a57-153b-4308-b1be-9cbf52acb8c4";
		service.receiptMap.put("e1300a57-153b-4308-b1be-9cbf52acb8c4", createRequest1());
		
		assertTrue(service.checkReceipt(id));
	}
	
	@Test
	public void checkIfPresentTest2() {
		String id = "e1300a57-153b-4308-b1be-9cbf52acb8c4";	
		assertFalse(service.checkReceipt(id));
	}
	
	@Test
	public void processRequestGetPoints() {
		String id = "e1300a57-153b-4308-b1be-9cbf52acb8c4";
		service.receiptMap.put("e1300a57-153b-4308-b1be-9cbf52acb8c4", createRequest1());
		
		var points=service.processRequestGetPoints(id);
		assertEquals(28, points);
	}
	
	private ReceiptRequest createRequest1() {
		ReceiptRequest request = new ReceiptRequest();
		request.setRetailer("Target");
		request.setPurchaseDate("2022-01-01");
		request.setPurchaseTime("13:01");
		request.setTotal("35.35");
		
		List<ItemRequest> items = new ArrayList<>();
		ItemRequest i1 = new ItemRequest();
		i1.setShortDescription("Mountain Dew 12PK");
		i1.setPrice("6.49");
		ItemRequest i2 = new ItemRequest();
		i2.setShortDescription("Emils Cheese Pizza");
		i2.setPrice("12.25");
		ItemRequest i3 = new ItemRequest();
		i3.setShortDescription("Knorr Creamy Chicken");
		i3.setPrice("1.26");
		ItemRequest i4 = new ItemRequest();
		i4.setShortDescription("Doritos Nacho Cheese");
		i4.setPrice("3.35");
		ItemRequest i5 = new ItemRequest();
		i5.setShortDescription("   Klarbrunn 12-PK 12 FL OZ  ");
		i5.setPrice("12.00");
		
		items.add(i1);
		items.add(i2);
		items.add(i3);
		items.add(i4);
		items.add(i5);
		
		request.setItems(items);
		
		return request;
	}
	
}
