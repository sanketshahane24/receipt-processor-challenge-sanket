package com.fetch.reciept_processor.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fetch.reciept_processor.model.ErrorResponse;
import com.fetch.reciept_processor.model.GetPointsResponse;
import com.fetch.reciept_processor.model.ProcessReceiptResponse;
import com.fetch.reciept_processor.model.ReceiptRequest;
import com.fetch.reciept_processor.service.ReceiptProcessingService;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

	@Autowired
	private ReceiptProcessingService processingService;

	@GetMapping("/{id}/points")
	public ResponseEntity<Object> getPoints(@PathVariable String id) {
		if (!processingService.checkReceipt(id)) {
			return new ResponseEntity<Object>(new ErrorResponse("No receipt found for that id"), HttpStatus.NOT_FOUND);
		}

		int points = processingService.processRequestGetPoints(id);

		GetPointsResponse response = new GetPointsResponse(points);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@PostMapping("/process")
	public ResponseEntity<Object> saveTheProcess(@RequestBody ReceiptRequest request) {

		if (!validateReceipt(request)) {
			return new ResponseEntity<Object>(new ErrorResponse("The Reciept is invalid"), HttpStatus.BAD_REQUEST);
		}

		String id = processingService.addReceipt(request);
		return new ResponseEntity<Object>(new ProcessReceiptResponse(id), HttpStatus.OK);
	}

	private boolean validateReceipt(ReceiptRequest request) {
		if (request == null) {
			return false;
		}
		if (StringUtils.isEmpty(request.getRetailer())) {
			return false;
		}
		if (StringUtils.isEmpty(request.getPurchaseDate())) {
			return false;
		}
		if (StringUtils.isEmpty(request.getPurchaseTime())) {
			return false;
		}
		if (StringUtils.isEmpty(request.getTotal())) {
			return false;
		}
		if (request.getItems() == null || request.getItems().size() == 0) {
			return false;
		}

		return true;
	}

}
