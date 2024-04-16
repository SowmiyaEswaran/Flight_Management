package com.hsi.flight.entity.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Payment {
	
	@NotBlank
	private String modeOfPayment;
	
	@NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in the format YYYY-MM-DD")
	private String dateOfTransaction;
	
	@Positive
	@NotNull
	private double totalAmount;
	
	

}
