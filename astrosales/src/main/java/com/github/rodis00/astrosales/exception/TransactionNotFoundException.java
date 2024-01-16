package com.github.rodis00.astrosales.exception;

import java.util.HashMap;
import java.util.Map;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
