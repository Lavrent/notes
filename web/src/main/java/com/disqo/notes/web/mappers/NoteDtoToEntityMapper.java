package com.disqo.notes.web.mappers;

import com.disqo.notes.web.dtos.NoteDto;
import com.disqo.notes.web.repository.entities.NoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteDtoToEntityMapper {

    @Mapping(source = "id", target = "uuid")
    List<NoteEntity> toNoteEntities(List<NoteDto> noteDtos);

    @Mapping(source = "uuid", target = "id")
    List<NoteDto> toNoteDtos(List<NoteEntity> noteEntities);
}