package com.github.rodis00.astrosales.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException (String message) {
        super(message);
    }
}
