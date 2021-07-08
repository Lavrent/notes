package com.disqo.notes.core.exceptions;

public class NoteValidationException extends RuntimeException {
    public NoteValidationException(String message) {
        super(message);
    }
}