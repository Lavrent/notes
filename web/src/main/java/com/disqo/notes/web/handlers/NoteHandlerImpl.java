package com.disqo.notes.web.handlers;

import com.disqo.notes.web.dtos.NoteDto;
import com.disqo.notes.web.services.NoteDtoService;
import com.disqo.notes.web.validators.NoteDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class NoteHandlerImpl implements NoteHandler {
    private final NoteDtoValidator noteDtoValidator;
    private final NoteDtoService noteDtoService;

    @Override
    public List<NoteDto> createNotes(String userEmail, List<NoteDto> noteDtos) {
        noteDtoValidator.validate(noteDtos);
        return noteDtoService.createNotes(userEmail, noteDtos);
    }

    @Override
    public List<NoteDto> getNotes(String userEmail) {
        return noteDtoService.getNotes(userEmail);
    }

    @Override
    public List<NoteDto> updateNotes(String userEmail, List<NoteDto> noteDtos) {
        noteDtoValidator.validate(noteDtos);

        return noteDtoService.updateNotes(userEmail, noteDtos);
    }
}