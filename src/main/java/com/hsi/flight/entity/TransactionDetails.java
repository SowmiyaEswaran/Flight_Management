package com.hsi.flight.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="flightTransaction")
public class TransactionDetails {
	
	@Id
	private String pnr;
	
	private String flightNum;
	
	private String modeOfPayment;
	
	private String passengerDetails;
	
	private double totalAmount;
	
	private String dateTime;
	
	private String status;

	public TransactionDetails(String pnr, String flightNum, String modeOfPayment, String passengerDetails,
			double totalAmount, String dateTime, String status) {
		super();
		this.pnr = pnr;
		this.flightNum = flightNum;
		this.modeOfPayment = modeOfPayment;
		this.passengerDetails = passengerDetails;
		this.totalAmount = totalAmount;
		this.dateTime = dateTime;
		this.status = status;
	}
	

}
