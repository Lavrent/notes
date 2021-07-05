package com.disqo.notes.core.services;

import com.disqo.notes.core.exceptions.NoteValidationException;
import com.disqo.notes.core.exceptions.UserValidationException;
import com.disqo.notes.core.mappers.NoteModelToEntityMapper;
import com.disqo.notes.core.models.NoteModel;
import com.disqo.notes.repository.NoteRepository;
import com.disqo.notes.repository.UserRepository;
import com.disqo.notes.repository.entities.NoteEntity;
import com.disqo.notes.repository.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
class NoteServiceImpl implements NoteService {
    private static final String NOTE_NOT_FOUND_MESSAGE = "Note with id %s does not exist";
    private final UserRepository userRepository;
    private final NoteModelToEntityMapper noteModelToEntityMapper;
    private final NoteRepository noteRepository;

    @Override
    public List<NoteModel> createNotes(String userEmail, List<NoteModel> noteModels) {
        List<NoteEntity> noteEntities = noteModelToEntityMapper.toNoteEntities(noteModels);
        UserEntity userEntity = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserValidationException("User with given email does not exist"));

        for (NoteEntity noteEntity : noteEntities) {
            LocalTime currentTime = LocalTime.now();

            noteEntity.setUuid(UUID.randomUUID());
            noteEntity.setUser(userEntity);
            noteEntity.setCreateTime(currentTime);
            noteEntity.setLastUpdateTime(currentTime);
        }

        noteRepository.saveAll(noteEntities);

        return noteModelToEntityMapper.toNoteModels(noteEntities);
    }

    @Override
    public List<NoteModel> getNotes(String userEmail) {
        List<NoteEntity> noteEntities = noteRepository.findAllByUserEmail(userEmail);

        return noteModelToEntityMapper.toNoteModels(noteEntities);
    }

    @Override
    public List<NoteModel> updateNotes(String userEmail, List<NoteModel> noteModels) {
        List<NoteEntity> noteEntities = noteModels.stream()
                .map(noteModel -> updateNote(userEmail, noteModel))
                .collect(Collectors.toList());

        return noteModelToEntityMapper.toNoteModels(noteEntities);
    }

    private NoteEntity updateNote(String userEmail, NoteModel noteModel) {
        NoteEntity noteEntity = noteRepository.findByUuidAndUserEmail(noteModel.getUuid(), userEmail)
                .orElseThrow(() -> new UserValidationException(String.format(NOTE_NOT_FOUND_MESSAGE, noteModel.getUuid())));

        noteEntity.setTitle(noteModel.getTitle());
        noteEntity.setNote(noteModel.getNote());
        noteEntity.setLastUpdateTime(LocalTime.now());

        return noteEntity;
    }

    @Override
    public void deleteNotes(String userEmail, List<UUID> noteIds) {
        for (UUID noteId : noteIds) {
            noteRepository.findByUuidAndUserEmail(noteId, userEmail)
                    .ifPresentOrElse(noteRepository::delete, () -> {
                        throw new NoteValidationException(String.format(NOTE_NOT_FOUND_MESSAGE, noteId));
                    });
        }
    }
}