package com.hsi.flight.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {

	private String pnr;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double totalAmount;
	
	private String status;

	public TransactionResponse(String pnr, String status) {
		super();
		this.pnr = pnr;
		this.status = status;
	}
	
}
