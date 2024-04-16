package com.hsi.flight.entity.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Flight {
	
	@NotBlank
	@NotNull
	@NotEmpty
	private String flightNumber;
	
	@NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in the format YYYY-MM-DD")
	private String dateOfJourney;
	@NotBlank
	@Size(min=3, max=3)
	private String seatNum;
	
	@NotBlank
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]", message = "Invalid time format. Time should be in HH:MM format")
	private String journeyDuration;

}
