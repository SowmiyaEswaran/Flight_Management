package com.hsi.flight.exception;

public class PnrNotFoundException  extends Exception{
    public PnrNotFoundException(String message) {
        super(message);
    }
}