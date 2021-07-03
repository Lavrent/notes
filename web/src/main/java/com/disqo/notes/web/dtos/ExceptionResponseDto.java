package com.disqo.notes.web.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponseDto {
    private String message;
    private String status;
    private int code;
    private LocalDateTime createdAt;
}