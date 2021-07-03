package com.disqo.notes.web.validators;

import com.disqo.notes.web.dtos.NoteDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NoteDtoValidatorImpl implements NoteDtoValidator {
    @Override
    public void validate(List<NoteDto> noteDtos) {
        noteDtos.forEach(this::validateSingleNoteDto);
    }

    private void validateSingleNoteDto(NoteDto noteDto) {
        if (noteDto.getTitle() == null || noteDto.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is not present");
        }

        if (noteDto.getTitle().length() > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title max size should be 50");
        }
        if (noteDto.getNote() != null && noteDto.getNote().length() > 1000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Note max size should be 1000");
        }
    }
}
