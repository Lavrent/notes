package com.disqo.notes.web.mappers;

import com.disqo.notes.web.dtos.NoteDto;
import com.disqo.notes.web.repository.entities.NoteEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteDtoToEntityMapper {

    List<NoteEntity> toNoteEntities(List<NoteDto> noteDtos);

    List<NoteDto> toNoteDtos(List<NoteEntity> noteEntities);
}