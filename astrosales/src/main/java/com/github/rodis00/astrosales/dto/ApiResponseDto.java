package com.github.rodis00.astrosales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {
    private String responseCode;
    private String responseMessage;
}
