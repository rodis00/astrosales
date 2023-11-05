package com.github.rodis00.astrosales.exception;

import java.util.HashMap;
import java.util.Map;

public class UserNotFoundException extends RuntimeException{
    private Map<String, String> errorDetail = new HashMap<>();
    public UserNotFoundException(Integer id) {
        this.errorDetail.put(
                "message", "User with id ${id} not found."
        );
    }
    public Map<String, String> getErrorDetail() {
        return this.errorDetail;
    }
}
