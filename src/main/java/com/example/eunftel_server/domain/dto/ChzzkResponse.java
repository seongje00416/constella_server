package com.example.eunftel_server.domain.dto;

import lombok.Data;

@Data
public class ChzzkResponse<T> {
    private String code;
    private String message;
    private T content;
}
