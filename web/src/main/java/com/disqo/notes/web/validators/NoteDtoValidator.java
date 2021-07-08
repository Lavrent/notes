package com.disqo.notes.web.validators;

import com.disqo.notes.web.dtos.NoteDto;

import java.util.List;

public interface NoteDtoValidator {
    void validate(List<NoteDto> noteDtos);

    void validateIds(List<NoteDto> noteDtos);
}