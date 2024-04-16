package com.hsi.flight.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsi.flight.constants.CommonConstants;
import com.hsi.flight.entity.TransactionDetails;
import com.hsi.flight.entity.TransactionJson;
import com.hsi.flight.entity.request.Passenger;
import com.hsi.flight.entity.request.TransactionRequest;
import com.hsi.flight.entity.response.TransactionResponse;
import com.hsi.flight.exception.PnrNotFoundException;
import com.hsi.flight.repository.FlightRepository;
import com.hsi.flight.repository.JsonDetailsRepository;
import com.hsi.flight.util.FlightUtil;

@Service
public class FlightService {

	@Autowired
	private FlightRepository flightrepo;

	@Autowired
	private JsonDetailsRepository jsonRepo;

	@Autowired
	private FlightUtil flightUtil;

	public TransactionResponse createTransaction(TransactionRequest request) throws JsonProcessingException {
		String pnr = flightUtil.generatePnr();

		TransactionDetails details = requestMapping(request, pnr, CommonConstants.CREATED.getValue());
		flightrepo.save(details);
		jsonRepo.save(jsonRequestMapping(request, pnr, CommonConstants.CREATED.getValue()));
		return new TransactionResponse(pnr, request.getPaymentDetails().getTotalAmount(), CommonConstants.CREATED.getValue());

	}

	public TransactionRequest getTransaction(String pnr)
			throws PnrNotFoundException, JsonMappingException, JsonProcessingException {
		Optional<TransactionDetails> transDeatils = flightrepo.findById(pnr);
		Optional<TransactionJson> transJson = jsonRepo.findById(pnr);
		if (!transDeatils.isEmpty() && transDeatils.get() != null && !transJson.isEmpty() && transJson.get() != null
				&& transDeatils.get().getPnr().equals(transJson.get().getPnr())) {
			return jsonResponseMapping(transJson.get().getJsonRequest());
		} else {
			throw new PnrNotFoundException(CommonConstants.PNR_NOT_FOUND.getValue());
		}
	}

	public TransactionResponse updateTransaction(TransactionRequest request)
			throws JsonProcessingException, PnrNotFoundException {
		if (request.getPnr() == null || request.getPnr().isBlank()) {
			throw new PnrNotFoundException(CommonConstants.PNR_NOT_FOUND.getValue());
		}
		TransactionDetails details = requestMapping(request, request.getPnr(), CommonConstants.UPDATED.getValue());
		flightrepo.save(details);
		jsonRepo.save(jsonRequestMapping(request, request.getPnr(), CommonConstants.UPDATED.getValue()));
		return new TransactionResponse(request.getPnr(), request.getPaymentDetails().getTotalAmount(), CommonConstants.UPDATED.getValue());
	}

	public TransactionResponse deleteTransaction(String pnr) throws PnrNotFoundException {
		if (flightrepo.existsById(pnr) && jsonRepo.existsById(pnr)) {
			flightrepo.deleteById(pnr);
			jsonRepo.deleteById(pnr);
		} else {
			throw new PnrNotFoundException(CommonConstants.PNR_NOT_AVAILABLE.getValue());
		}
		return new TransactionResponse(pnr, CommonConstants.DELETED.getValue());

	}

	private TransactionRequest jsonResponseMapping(String string) throws JsonMappingException, JsonProcessingException {
		ObjectMapper obj = new ObjectMapper();
		return obj.readValue(string, TransactionRequest.class);
	}

	private TransactionJson jsonRequestMapping(TransactionRequest request, String pnr, String status)
			throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString;
		try {
			jsonString = objectMapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			throw e;
		}
		return new TransactionJson(pnr, jsonString, LocalDateTime.now().toString(), status);
	}

	private TransactionDetails requestMapping(TransactionRequest request, String pnr, String status) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		TransactionDetails details = new TransactionDetails(pnr, request.getFlightDetails().getFlightNumber(),
				request.getPaymentDetails().getModeOfPayment(), passengerDetailMapping(request.getPassengerDetails()),
				request.getPaymentDetails().getTotalAmount(), currentDateTime.toString(), status);
		return details;
	}

	private String passengerDetailMapping(List<Passenger> passengerDetails) {

		return passengerDetails.stream().map(passenger -> passenger.getName() + ":" + passenger.getPassportNum())
				.collect(Collectors.joining(";"));

	}

}
