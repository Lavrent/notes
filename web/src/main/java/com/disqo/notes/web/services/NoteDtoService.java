package com.disqo.notes.web.services;

import com.disqo.notes.web.dtos.NoteDto;

import java.util.List;
import java.util.UUID;

public interface NoteDtoService {
    List<NoteDto> createNotes(String userEmail, List<NoteDto> noteDtos);

    List<NoteDto> getNotes(String userEmail);

    List<NoteDto> updateNotes(String userEmail, List<NoteDto> noteDtos);

    void deleteNotes(String userEmail, List<UUID> noteIds);
}