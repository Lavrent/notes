package com.disqo.notes.web.handlers;

import com.disqo.notes.web.dtos.NoteDto;

import java.util.List;

public interface NoteHandler {
    List<NoteDto> createNotes(String userEmail,List<NoteDto> noteDto);
}