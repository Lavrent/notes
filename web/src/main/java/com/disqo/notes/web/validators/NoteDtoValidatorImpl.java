package com.disqo.notes.web.validators;

import com.disqo.notes.web.dtos.NoteDto;
import com.disqo.notes.web.exceptions.JsonValidationExcepton;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NoteDtoValidatorImpl implements NoteDtoValidator {
    @Override
    public void validate(List<NoteDto> noteDtos) {
        if (noteDtos == null || noteDtos.isEmpty()) {
            throw new JsonValidationExcepton("Note are not present");
        }

        noteDtos.forEach(this::validateSingleNoteDto);
    }

    @Override
    public void validateIds(List<NoteDto> noteDtos) {
        boolean noteIdIsNull = noteDtos.stream()
                .map(NoteDto::getId)
                .anyMatch(Objects::isNull);

        if (noteIdIsNull) {
            throw new JsonValidationExcepton("Note id is invalid");
        }
    }

    private void validateSingleNoteDto(NoteDto noteDto) {
        if (noteDto == null) {
            throw new JsonValidationExcepton("Provided note json is null");
        }

        if (noteDto.getTitle() == null || noteDto.getTitle().isBlank()) {
            throw new JsonValidationExcepton("Title is not present");
        }

        if (noteDto.getTitle().length() > 50) {
            throw new JsonValidationExcepton("Title max size should be 50");
        }
        if (noteDto.getNote() != null && noteDto.getNote().length() > 1000) {
            throw new JsonValidationExcepton("Note max size should be 1000");
        }
    }
}
