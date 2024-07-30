package com.fetch.reciept_processor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.fetch.reciept_processor.model.ItemRequest;
import com.fetch.reciept_processor.model.ProcessReceiptResponse;
import com.fetch.reciept_processor.model.ReceiptRequest;
import com.fetch.reciept_processor.service.ReceiptProcessingService;

@ExtendWith(MockitoExtension.class)
public class ReceiptControllerTest {

	@InjectMocks
	private ReceiptController controller;
	
	@Mock
	private ReceiptProcessingService service;
	
	@Test
	public void sampleTest() {
		assertTrue(true);
	}
	
	@Test
	public void saveTheProcess() {
		ProcessReceiptResponse id=controller.saveTheProcess(createRequest1());
		assertNotNull(id);
	}
	
	@Test
	public void getPointsTest() {
		String id = "e1300a57-153b-4308-b1be-9cbf52acb8c4";
		
		when(service.checkReceipt(anyString())).thenReturn(true);
		when(service.processRequestGetPoints(anyString())).thenReturn(28);
		
		var response = this.controller.getPoints(id);
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void getPointsTest2() {
		String id = "e1300a57-153b-4308-b1be-9cbf52acb8c4";
		
		var response = this.controller.getPoints(id);
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
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
