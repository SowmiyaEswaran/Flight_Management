package com.hsi.flight.entity.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class TransactionRequest {
	
	@Pattern(regexp = "[A-Z0-9]{6}", message = "Invalid pnr format. Must be six uppercase letters and numbers.")
	private String pnr;

	@NotNull
	@Valid
	private Flight flightDetails;
	
	@NotEmpty
	@Valid
	private List<Passenger> passengerDetails;
	
	@NotNull
	@Valid
	private Payment PaymentDetails;
}
