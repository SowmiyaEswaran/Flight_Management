package com.hsi.flight.util;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class FlightUtil {
	
	public String generatePnr() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder pnr = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            pnr.append(characters.charAt(index));
        }

        return pnr.toString();
    
	}

}
