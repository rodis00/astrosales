package com.github.rodis00.astrosales.exception;

import java.util.HashMap;
import java.util.Map;

public class UserWithThisEmailExistException extends RuntimeException{
    private Map<String, String> errorDetail = new HashMap<>();
    public UserWithThisEmailExistException() {
        this.errorDetail.put(
                "message", "User with this email already exist."
        );
    }
    public Map<String, String> getErrorDetail() {
        return this.errorDetail;
    }
}
