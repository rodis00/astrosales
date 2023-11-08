package com.github.rodis00.astrosales.exception;

import java.util.HashMap;
import java.util.Map;

public class FlightNotFoundException extends RuntimeException {

    private final Map<String, String> errorDetail = new HashMap<>();
    public FlightNotFoundException(Integer id) {
        this.errorDetail.put(
                "message", "Flight with id: " + id + " not found."
        );
    }
    public Map<String, String> getErrorDetail() {
        return this.errorDetail;
    }
}
