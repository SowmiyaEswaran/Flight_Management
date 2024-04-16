package com.hsi.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.hsi.flight.constants.CommonConstants;
import com.hsi.flight.entity.request.TransactionRequest;
import com.hsi.flight.entity.response.TransactionResponse;
import com.hsi.flight.exception.PnrNotFoundException;
import com.hsi.flight.service.FlightService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/hsi/flight/")
public class FlightController {
	@Autowired
	private FlightService flightservice;

	@PostMapping("/makePayment")
	public ResponseEntity<TransactionResponse> createTransaction(@RequestBody @Valid TransactionRequest request)
			throws JsonProcessingException {

		return new ResponseEntity<TransactionResponse>(flightservice.createTransaction(request), HttpStatus.CREATED);
	}

	@GetMapping("/getTransaction")
	public ResponseEntity<?> fetchTransaction(@RequestBody @Valid JsonNode jsonNode)
			throws PnrNotFoundException, JsonMappingException, JsonProcessingException {
		String pnr = jsonNode.get(CommonConstants.PNR.getValue()).asText();
		if (!pnr.isBlank() && pnr.matches("[A-Z0-9]{6}")) {
			return new ResponseEntity<TransactionRequest>(flightservice.getTransaction(pnr), HttpStatus.FOUND);
		} else {
			throw new PnrNotFoundException(CommonConstants.PNR_BLANK.getValue());
		}

	}

	@PutMapping("/updateTransaction")
	public ResponseEntity<?> updateTransaction(@RequestBody @Valid TransactionRequest request)
			throws JsonProcessingException, PnrNotFoundException {
		return new ResponseEntity<TransactionResponse>(flightservice.updateTransaction(request), HttpStatus.OK);

	}

	@DeleteMapping("/deleteTransaction")
	public ResponseEntity<TransactionResponse> deleteTransaction(@RequestBody @Valid JsonNode jsonNode)
			throws PnrNotFoundException {
		String pnr = jsonNode.get(CommonConstants.PNR.getValue()).asText();
		if (!pnr.isBlank() && pnr.matches("[A-Z0-9]{6}")) {
			return new ResponseEntity<TransactionResponse>(flightservice.deleteTransaction(pnr), HttpStatus.CREATED);
		} else {
			throw new PnrNotFoundException(CommonConstants.PNR_BLANK.getValue());
		}

	}

}
