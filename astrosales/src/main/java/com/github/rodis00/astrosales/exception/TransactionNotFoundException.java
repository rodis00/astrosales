package com.github.rodis00.astrosales.exception;

import java.util.HashMap;
import java.util.Map;

public class TransactionNotFoundException extends RuntimeException {
    private final Map<String, String> errorDetails = new HashMap<>();
    public TransactionNotFoundException(Integer id) {
        this.errorDetails.put(
                "message", "Transaction with id: " + id + " not found."
        );
    }
    public Map<String, String> getErrorDetails() {
        return this.errorDetails;
    }
}
