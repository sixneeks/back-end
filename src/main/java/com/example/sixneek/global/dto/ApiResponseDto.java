package com.example.sixneek.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class ApiResponseDto<T> {
    private int status;
    private String message;
    private T data;
}
