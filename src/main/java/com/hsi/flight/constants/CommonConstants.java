package com.hsi.flight.constants;

public enum CommonConstants {
	PNR("pnr"),
	PNR_NOT_FOUND("PNR NOT FOUND"),
	PNR_NOT_AVAILABLE("NO SUCH PNR AVAILABLE"),
	PNR_BLANK("PNR is blank/Invalid"),
	
	CREATED("CREATED"),
	UPDATED("UPDATED"),
	DELETED("DELETED");
	
	private final String value;

	CommonConstants(String value) {
		this.value =  value;
	}

	public String getValue() {
		return value;
	}
	

}
