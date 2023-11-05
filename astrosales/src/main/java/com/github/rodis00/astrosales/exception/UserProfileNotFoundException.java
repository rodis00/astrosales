package com.github.rodis00.astrosales.exception;

import java.util.HashMap;
import java.util.Map;

public class UserProfileNotFoundException extends RuntimeException{
    private Map<String, String> errorDetail = new HashMap<>();
    public UserProfileNotFoundException(Integer id) {
        this.errorDetail.put(
                "message", "User profile with id ${id} not found."
        );
    }
    public Map<String, String> getErrorDetail() {
        return this.errorDetail;
    }
}
