package com.hsi.flight.entity.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Passenger {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String age;
	
	@NotBlank
	@Email(message ="Invalid Email")
	private String mailId;
	
	@NotBlank
    @Pattern(regexp = "[A-Z]{2}[0-9]{7}", message = "Invalid passport number format. Must be two uppercase letters followed by seven digits.")
	private String passportNum;
	

}
