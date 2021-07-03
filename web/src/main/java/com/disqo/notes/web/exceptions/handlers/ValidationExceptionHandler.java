package com.disqo.notes.web.exceptions.handlers;

import com.disqo.notes.core.exceptions.NoteValidationException;
import com.disqo.notes.core.exceptions.UserValidationException;
import com.disqo.notes.web.dtos.ExceptionResponseDto;
import com.disqo.notes.web.exceptions.JsonValidationExcepton;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handleJsonValidationException(JsonValidationExcepton ex) {
        return handleValidationException(BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handleNoteValidationException(NoteValidationException ex) {
        return handleNotFoundException(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> handleUserValidationException(UserValidationException ex) {
        return handleNotFoundException(ex.getMessage());
    }

    private ResponseEntity<ExceptionResponseDto> handleNotFoundException(String message) {
        return handleValidationException(NOT_FOUND, message);
    }

    private ResponseEntity<ExceptionResponseDto> handleValidationException(HttpStatus httpStatus, String message) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.setMessage(message);
        exceptionResponseDto.setCode(httpStatus.value());
        exceptionResponseDto.setStatus(httpStatus.name());
        exceptionResponseDto.setCreatedAt(LocalDateTime.now());

        return new ResponseEntity<>(exceptionResponseDto, httpStatus);
    }
}