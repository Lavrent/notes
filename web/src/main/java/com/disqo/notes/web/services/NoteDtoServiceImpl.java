package com.disqo.notes.web.services;

import com.disqo.notes.web.dtos.NoteDto;
import com.disqo.notes.web.mappers.NoteDtoToEntityMapper;
import com.disqo.notes.web.repository.NoteRepository;
import com.disqo.notes.web.repository.UserRepository;
import com.disqo.notes.web.repository.entities.NoteEntity;
import com.disqo.notes.web.repository.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Service
class NoteDtoServiceImpl implements NoteDtoService {
    private final UserRepository userRepository;
    private final NoteDtoToEntityMapper noteDtoToEntityMapper;
    private final NoteRepository noteRepository;

    @Override
    public List<NoteDto> createNotes(String userEmail, List<NoteDto> noteDtos) {
        List<NoteEntity> noteEntities = noteDtoToEntityMapper.toNoteEntities(noteDtos);
        UserEntity userEntity = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User with given email does not exist"));

        noteEntities.forEach(noteEntity -> {
            noteEntity.setUser(userEntity);
            noteEntity.setCreateTime(LocalTime.now());
            noteEntity.setLastUpdateTime(LocalTime.now());
        });

        noteRepository.saveAll(noteEntities);

        return noteDtoToEntityMapper.toNoteDtos(noteEntities);
    }
}