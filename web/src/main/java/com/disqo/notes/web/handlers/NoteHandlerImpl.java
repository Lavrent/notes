package com.disqo.notes.web.handlers;

import com.disqo.notes.core.models.NoteModel;
import com.disqo.notes.core.services.NoteService;
import com.disqo.notes.web.dtos.NoteDto;
import com.disqo.notes.web.mappers.NoteDtoModelMapper;
import com.disqo.notes.web.validators.NoteDtoValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
class NoteHandlerImpl implements NoteHandler {
    private final NoteDtoValidator noteDtoValidator;
    private final NoteService noteService;
    private final NoteDtoModelMapper noteDtoModelMapper;

    @Override
    public List<NoteDto> createNotes(String userEmail, List<NoteDto> noteDtos) {
        noteDtoValidator.validate(noteDtos);

        List<NoteModel> noteModels = noteDtoModelMapper.toNoteModels(noteDtos);
        List<NoteModel> createdNoteModels = noteService.createNotes(userEmail, noteModels);

        return noteDtoModelMapper.toNoteDtos(createdNoteModels);
    }

    @Override
    public List<NoteDto> getNotes(String userEmail) {
        List<NoteModel> noteModels = noteService.getNotes(userEmail);

        return noteDtoModelMapper.toNoteDtos(noteModels);
    }

    @Override
    public List<NoteDto> updateNotes(String userEmail, List<NoteDto> noteDtos) {
        noteDtoValidator.validate(noteDtos);
        noteDtoValidator.validateIds(noteDtos);

        List<NoteModel> noteModels = noteDtoModelMapper.toNoteModels(noteDtos);

        List<NoteModel> updatedNoteModels = noteService.updateNotes(userEmail, noteModels);

        return noteDtoModelMapper.toNoteDtos(updatedNoteModels);
    }

    @Override
    public void deleteNotes(String userEmail, List<NoteDto> noteDtos) {
        noteDtoValidator.validateIds(noteDtos);

        List<UUID> noteIds = noteDtos.stream()
                .map(NoteDto::getId)
                .collect(Collectors.toList());

        noteService.deleteNotes(userEmail, noteIds);
    }
}