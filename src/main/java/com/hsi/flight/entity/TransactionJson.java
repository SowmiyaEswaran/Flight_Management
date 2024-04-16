package com.hsi.flight.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="flightJson")
public class TransactionJson {
	
	@Id
	private String pnr;
	
	private String jsonRequest;
	
	private String dateTime;
	
	private String status;

}
