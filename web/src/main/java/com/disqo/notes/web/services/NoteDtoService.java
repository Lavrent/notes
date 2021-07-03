package com.disqo.notes.web.services;

import com.disqo.notes.web.dtos.NoteDto;

import java.util.List;

public interface NoteDtoService {
    List<NoteDto> createNotes(String userEmail, List<NoteDto> noteDtos);

    List<NoteDto> getNotes(String userEmail);
}