package com.github.rodis00.astrosales.config;

import org.springframework.stereotype.Component;

import java.util.Base64;
@Component
public class PasswordEncoder {
    public String encodePassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String decodePassword(String password) {
        return new String(Base64.getDecoder().decode(password));
    }
}
