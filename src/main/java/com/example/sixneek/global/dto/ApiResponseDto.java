package com.example.sixneek.global.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponseDto<T> {

    private HttpStatus status;
    private String message;
    private T data;

    public ApiResponseDto(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
