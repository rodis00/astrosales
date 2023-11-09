package com.github.rodis00.astrosales.exception;

import java.util.HashMap;
import java.util.Map;

public class ReservationNotFoundException extends RuntimeException {
    private final Map<String, String> errorDetails = new HashMap<>();
    public ReservationNotFoundException(Integer id) {
        this.errorDetails.put(
                "message", "Reservation with id: " + id + " not found."
        );
    }
    public Map<String, String> getErrorDetails() {
        return this.errorDetails;
    }
}
