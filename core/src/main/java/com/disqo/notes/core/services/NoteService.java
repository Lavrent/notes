package com.disqo.notes.core.services;


import com.disqo.notes.core.models.NoteModel;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    List<NoteModel> createNotes(String userEmail, List<NoteModel> noteDtos);

    List<NoteModel> getNotes(String userEmail);

    List<NoteModel> updateNotes(String userEmail, List<NoteModel> noteDtos);

    void deleteNotes(String userEmail, List<UUID> noteIds);
}