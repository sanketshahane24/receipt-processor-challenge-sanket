package com.fetch.reciept_processor.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fetch.reciept_processor.model.ItemRequest;
import com.fetch.reciept_processor.model.ReceiptRequest;

@Service
public class ReceiptProcessingService {

	Map<String, ReceiptRequest> receiptMap = new HashMap<>();

	public String addReceipt(ReceiptRequest request) {
		UUID uuid = UUID.randomUUID();

		String uuidString = uuid.toString();
		receiptMap.put(uuidString, request);

		return uuidString;
	}

	public boolean checkReceipt(String id) {
		if (receiptMap.containsKey(id))
			return true;

		return false;
	}

	public int processRequestGetPoints(String id) {
		int points = 0;
		ReceiptRequest reciept = receiptMap.get(id);

		points += findAllAlphanumericCharacters(reciept.getRetailer());
		points += processRoundTotal(reciept.getTotal());
		points += pairPoints(reciept.getItems().size());
		points += itemsDescriptionPoints(reciept.getItems());
		points += isDayOfPurchaseOdd(reciept.getPurchaseDate());
		points += isTimeinBound(reciept.getPurchaseTime());
		return points;
	}

	private int findAllAlphanumericCharacters(String retailerName) {
		int count = 0;

		for (char c : retailerName.toCharArray()) {
			if (Character.isLetterOrDigit(c)) {
				count++;
			}
		}
		return count;
	}

	private int processRoundTotal(String total) {
		int to = (int) (Double.valueOf(total) * 100);

		if (to != 0 && to % 100 == 0) {
			return 75;
		} else if (to != 0 && to % 25 == 0) {
			return 25;
		}
		return 0;
	}

	private int pairPoints(int size) {
		int pairs = size / 2;
		return pairs * 5;
	}

	private int itemsDescriptionPoints(List<ItemRequest> items) {
		int points = 0;

		for (ItemRequest item : items) {
			int len = item.getShortDescription().trim().length();
			if (len % 3 == 0) {
				float cPrice = (float) (Double.valueOf(item.getPrice()) * 0.2);
				int price = (int) Math.ceil(cPrice);
				points += price;
			}
		}
		return points;
	}

	private int isDayOfPurchaseOdd(String purchaseDate) {
		try {
			LocalDate date = LocalDate.parse(purchaseDate, DateTimeFormatter.ISO_LOCAL_DATE);
			int day = date.getDayOfMonth();
			if (!(day % 2 == 0)) {
				return 6;
			} else
				return 0;
		} catch (DateTimeParseException e) {
			throw e;
		}
	}

	private int isTimeinBound(String purchaseTime) {
		try {
			LocalTime time = LocalTime.parse(purchaseTime, DateTimeFormatter.ofPattern("HH:mm"));

			LocalTime startTime = LocalTime.of(14, 0);
			LocalTime endTime = LocalTime.of(16, 0);

			if (time.isAfter(startTime) && time.isBefore(endTime)) {
				return 10;
			} else
				return 0;
		} catch (DateTimeParseException e) {
			throw e;
		}
	}

}
